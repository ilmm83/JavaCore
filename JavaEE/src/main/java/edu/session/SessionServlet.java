package edu.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession(); // get a user session id. only from the request
        var attributesNames = session.getAttributeNames();
        var writer = resp.getWriter();

        while (attributesNames.hasMoreElements()) {
            var name = attributesNames.nextElement();
            writer.write(name + " --> " + session.getAttribute(name));
        }

        session.setAttribute("product", "$1000");
        session.setMaxInactiveInterval(2400); // expiration time
    }
}
