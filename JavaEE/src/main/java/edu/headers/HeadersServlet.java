package edu.headers;

import edu.simple_servlet.HelloServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/headers")
public class HeadersServlet extends HelloServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var headers = req.getHeaderNames();

        while (headers.hasMoreElements()) {
            var header = headers.nextElement();
            System.out.println(header + " --> " + req.getHeader(header));
        }
    }
}
