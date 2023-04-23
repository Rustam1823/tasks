package ibsappline.Servlets;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ibsappline.logic.Functional;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calculator")
public class ServletCalculator extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

        double num1 = jObj.get("a").getAsDouble();
        double num2 = jObj.get("b").getAsDouble();
        String opp = jObj.get("opp").getAsString();
        Functional function = new Functional();

        if("+".equals(opp))
        {
            function.oppresult = num1 + num2;
            pw.print(gson.toJson(function));
        }
        else if("-".equals(opp))
        {
            function.oppresult = num1 - num2;
            pw.print(gson.toJson(function));
        }
        else if("*".equals(opp))
        {
            function.oppresult = num1 * num2;
            pw.print(gson.toJson(function));
        }
        else if ("/".equals(opp))
        {
            function.oppresult = num1 / num2;
            pw.print(gson.toJson(function));
        } else{
                function.response = "неверная операция";
                pw.print(gson.toJson(function));
        }
    }
}