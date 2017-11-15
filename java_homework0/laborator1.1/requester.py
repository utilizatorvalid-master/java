import urllib.request
import urllib.parse
import concurrent.futures

# SERVER_ENDPOINT="http://192.168.0.107:8080"
SERVER_ENDPOINT="http://localhost:8080"


def do_requests(number_of_executors):
    with concurrent.futures.ThreadPoolExecutor(max_workers=number_of_executors)as executor:
        future_to_url = {executor.submit(load_url, index, 60) : index for index in range(10)}
        for future in concurrent.futures.as_completed(future_to_url):
            data = future.result()
            print(data)
    pass

# Retrieve a single page and report the URL and contents
def load_url(index, timeout):
    values = {'key': 'key_'+str(index),
              'value': 'value'+ str(index)}
    data = urllib.parse.urlencode(values).encode("utf-8")
    req = urllib.request.Request(SERVER_ENDPOINT + '/pairs', data)
    req.add_header('application_type','desktop')

    with urllib.request.urlopen(req, timeout=timeout) as conn:
        return conn.read()


if __name__=="__main__":
    do_requests(100)