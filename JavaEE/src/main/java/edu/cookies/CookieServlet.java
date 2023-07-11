package edu.cookies;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var writer = resp.getWriter();
        var cookies = req.getCookies();
        var customCookie = new Cookie("CustomCookie", "Value_of_the_Custom_Cookie");
        customCookie.setMaxAge(5);
        customCookie.setPath("/cookies");

        resp.addCookie(customCookie);

        Arrays.stream(cookies)
            .forEach(cookie -> writer.write(cookie.getName() + " --> " + cookie.getValue() + "\n"));
    }
}
