package ibsappline.Servlets;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ibsappline.logic.Model;
import ibsappline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {
    private AtomicInteger counter = new AtomicInteger(4);
    Model model = Model.getInstance();
    Gson gson =  new GsonBuilder().setPrettyPrinting().create();

    /*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        double salary = Double.parseDouble(request.getParameter("salary"));

        User user = new User(name,surname,salary);
        model.add(user,counter.getAndIncrement());

        pw.print("<html>" + "<h3>User" + name + " " + surname + "with salary =" + salary + "sucsesfully created! </h3>"
                + "<a href=\"addUser.html\">Create a new user</a>br/>" +"<a href=\"index.jsp\"Home</a>" + "</html>");
    }
     */
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
        StringBuffer sb = new StringBuffer();
        String line;
        try{
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (Exception E){
            System.out.println("error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(sb),JsonObject.class);
        request.setCharacterEncoding("UTF-8");

        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        double salary = jobj.get("salary").getAsDouble();

        User user = new User("pozhiloi","zhmih",20);
        model.add(user,counter.getAndIncrement());

        response.setContentType("application/json;utf-8");
        PrintWriter pw = response.getWriter();

        pw.print(gson.toJson(model.getFromList()));
    }
    }

