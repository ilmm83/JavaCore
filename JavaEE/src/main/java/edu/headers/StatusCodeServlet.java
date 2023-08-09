package edu.headers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/status")
public class StatusCodeServlet extends HeadersServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Success -> 2**
//        Redirect -> 3**
//        Client Error -> 4**
//        Internal Server Error -> 5**

//      redirect status 302
        resp.sendRedirect("/hello");
    }
}
