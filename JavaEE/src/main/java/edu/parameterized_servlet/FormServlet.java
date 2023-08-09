package edu.parameterized_servlet;

import edu.simple_servlet.HelloServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/form")
public class FormServlet extends HelloServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("<html>" +
            "<form action='form' method='post'\n" +
            "  <label for='name'>Name: </label>\n" +
            "  <input type='text' id='name' name='name'><br><br>\n" +

            "  <label for='email'>Email: </label>\n" +
            "  <input type='email' id='email' name='email'><br><br>\n" +

            "  <input type='submit' value='Submit'>\n" +
            "</form>\n" +
           "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        var email = req.getParameter("email");

        System.out.println(name + " " + email);
    }
}
