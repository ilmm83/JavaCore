package edu.mvc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/mvc")
public class MyController extends HttpServlet {

    private MyModel model = new MyModel();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var student = model.getStudent();

        req.setAttribute("student", student); // bean scope: request
        req.getSession().setAttribute("student", student); // bean scope: session
        req.getServletContext().setAttribute("student", student); // bean scope: application

        req.getRequestDispatcher("pages/mvc/view_model.jsp").forward(req, resp);
    }
}
