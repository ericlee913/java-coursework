package uk.ac.ucl.servlets;

import uk.ac.ucl.model.DataFrame;
import uk.ac.ucl.model.JsonWriter;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/JsonSave.html")
public class JsonSaveServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        JsonWriter jsonwriter = new JsonWriter();
        PrintWriter writer = response.getWriter();
        DataFrame df = model.getDataframe();

        String json = jsonwriter.outPutJSON(df);

        response.setContentType(("application/json"));
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition","attachment;  filename=PatientsJSON.json");
        writer.print(json);
        writer.flush();
    }
}
