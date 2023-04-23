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

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        if (id > 0) {
            if (id > model.getFromList().size()) {
                Functional function = new Functional();
                function.response = "ошибка,ID такого пользователя нет";
                pw.print(gson.toJson(function));
            } else {
                Functional function = new Functional();
                function.response = id + "успешно удален";
                pw.print(gson.toJson(function));
                model.delete(id);
            }
        } else {
            Functional function = new Functional();
            function.response = "ошибка,ID меньше нуля";
            pw.print(gson.toJson(function));
        }
    }
}
