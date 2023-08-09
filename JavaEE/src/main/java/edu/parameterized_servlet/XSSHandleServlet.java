package edu.parameterized_servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/xss")
public class XSSHandleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var area = clearRequestParameters(req.getParameter("area"));

        resp.getWriter().write(
            "<html>" +
                "<body>\n" +
                "area -> " + "<span>" + area + "</span>" +
                "<form action='xss' method='post'>\n" +
                " <textarea name='area' rows='5' cols='10'></textarea>\n" +

                " <input type='submit' value='Submit'>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private static String clearRequestParameters(String param) {
        if (param == null) return "";

        return param.replaceAll("<", "&lt;")
            .replaceAll(">", "&gt;")
            .replaceAll("\"", "&quot;")
            .replaceAll("&", "&amp;");
    }
}
