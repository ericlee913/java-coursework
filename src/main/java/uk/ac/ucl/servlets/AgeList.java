package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

// The servlet invoked to display a list of patients. Note that this data is just example data,
// you replace it with your data.
// The url http://localhost:8080/patientList.html is mapped to calling doGet on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/ageList.html")
public class AgeList extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // Get the data from the model
        Model model = ModelFactory.getModel();
        List<List<String>> patientList = model.oldestToYoungest();
        List<String> ids = new ArrayList<>();
        List<String> ages = new ArrayList<>();
        for(int index = 0; index < patientList.size(); index++){
            ids.add(patientList.get(index).getFirst());
            ages.add(patientList.get(index).get(1));
        }

        request.setAttribute("ids", ids);
        request.setAttribute("ages", ages);


        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/ageList.jsp");
        dispatch.forward(request, response);
    }
}
