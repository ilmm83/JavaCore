package edu.expression_language;

import edu.mvc.MyModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/el")
public class MyController extends HttpServlet {

    private MyModel model = new MyModel();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var student = model.getStudent();
        var courses = List.of("Math", "French", "Biology");

        // EL can get any attribute from the request, session, application scope.
        req.setAttribute("student", student); // bean scope: request
        req.setAttribute("courses", courses);

        req.getRequestDispatcher("pages/el/el_example.jsp").forward(req, resp);
    }
}
