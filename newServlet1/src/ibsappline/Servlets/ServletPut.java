package ibsappline.Servlets;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ibsappline.logic.Functional;
import ibsappline.logic.Model;
import ibsappline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        StringBuffer sb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("ошибка");
        }

        JsonObject jObj = gson.fromJson(String.valueOf(sb), JsonObject.class);

        int id = jObj.get("id").getAsInt();
        String name = jObj.get("name").getAsString();
        String surname = jObj.get("surname").getAsString();
        double salary = jObj.get("salary").getAsDouble();

        if (id > 0) {
            if (id > model.getFromList().size()) {
                Functional function = new Functional();
                function.response = "ошибка,ID такого пользователя нет";
                pw.print(gson.toJson(function));
            } else {
                model.delete(id);
                User user = new User(name, surname, salary);
                model.add(user, id);
                Functional function = new Functional();
                function.response = id + "успешно изменен";
                pw.print(gson.toJson(function));
            }
        } else {
            Functional function = new Functional();
            function.response = "ошибка,ID меньше нуля";
            pw.print(gson.toJson(function));
            }
        }
    }
