package ibsappline.Servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ibsappline.logic.Functional;
import ibsappline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

   /* protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter pw = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        if(id == 0 ) {
            pw.print("<html>" + "<h3>доступные пользователи:</h3><br/>" + "user id" +"<ul>");


            for(Map.Entry<Integer,User> entry : model.getFromList().entrySet()){
                pw.print("<li>" + entry.getKey() + "</li>" + "<ul>" + "<li>name:" + entry.getValue().getName() + "</li>"
                + "<li>surname:" + entry.getValue().getSurname() + "</li>" +
                        "<li>salary:" + entry.getValue().getSalary() + "</li>" +"</ul>");
            }
            pw.print("</ul>" + "<a href=\"index.jsp\">Home</a>" + "</html>");

        } else if(id > 0){
            if(id > model.getFromList().size()) {
                pw.print("<html>" + "<h3>not found this user</h3>" + "<a href=\"index.jsp\">Home</a>" + "</html>" );
            } else  {
                pw.print("<html>" + "<h3>this user</h3>" + "<br/>" +
                        "name:" + model.getFromList().get(id).getName() + "<br/>"+
                        "surname:" + model.getFromList().get(id).getSurname() + "<br/>"+
                        "salary:" + model.getFromList().get(id).getSalary() + "<br/>"+
                        "<a href=\"index.jsp\">Home</a>" + "</html>" );
            }
        } else {
            pw.print("<html>" + "<h3>id will be bigger than 0</h3>" + "<a href=\"index.jsp\">Home</a>" + "</html>" );
        }

    }
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

       int id = Integer.parseInt(request.getParameter("id"));
       request.setCharacterEncoding("UTF-8");
       response.setContentType("application/json;charset=utf-8");
       PrintWriter pw = response.getWriter();

       if (id == 0) {
           pw.print(gson.toJson(model.getFromList()));
       } else if (id > 0) {
           if (id > model.getFromList().size()) {
               Functional function = new Functional();
               function.response = "ошибка,ID такого пользователя нет";
               pw.print(gson.toJson(function));
           } else {
               pw.print(gson.toJson(model.getFromList().get(id)));
           }
       } else {
           Functional function = new Functional();
           function.response = "ошибка,ID меньше нуля";
           pw.print(gson.toJson(function));
       }
   }
}

