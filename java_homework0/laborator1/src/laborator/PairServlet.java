package laborator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;


@WebServlet(name = "PairServlet",urlPatterns = {"/pairs"})
public class PairServlet extends HttpServlet {
    Map<String, String> storedPairs = new ConcurrentHashMap<>();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        storedPairs.put(key, value);
        this.renderPage(request, response);
        getServletContext().log(request.getMethod() + " : " + request.getRemoteAddr() +" "+request.getHeader("User-Agent")+ ' '+request.getHeader("Accept-Language"));
        synchronized (this){
            ServletContext context = getServletContext();
            String filePath = context.getRealPath("WEB-INF/pairs.proprieties");
            File f = new File(filePath);
            if(!f.exists())
                f.createNewFile();
            PrintWriter writer = new PrintWriter(
                    new FileOutputStream(new File(filePath),true));
            writer.println(key + " : " + value );
            writer.close();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.renderPage(request, response);
    }

    private void renderPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        String type = request.getHeader("application_type");
//        System.out.println(type);
        PrintWriter out = new PrintWriter(response.getWriter());
        Map<String, String> treeMap = new TreeMap<String, String>(storedPairs);

        if(type == null) {
            out.println("<html><head><title>Hello</title></head>");
            out.println("<body>Pairs are: ");
            out.println("<br>");

            out.println("<ul>");
            for (Map.Entry<String, String> pair : treeMap.entrySet()) {
                String key = pair.getKey();
                String value = pair.getValue();
                out.println("<li>" + key + "--> " + value + "</li>");
            }
            out.println("</ul>");
            out.println("</body></html>");

        }else{
            if(type.equals("desktop")){
                for (Map.Entry<String, String> pair : treeMap.entrySet()) {
                    String key = pair.getKey();
                    String value = pair.getValue();
                    out.println(key + "--> " + value + '\n');
                }

            }
        }
        out.close();

    }
}
