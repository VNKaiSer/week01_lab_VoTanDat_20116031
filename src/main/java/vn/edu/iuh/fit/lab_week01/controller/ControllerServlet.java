package vn.edu.iuh.fit.lab_week01.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();
        System.out.println(action);

        if (action != null){
            switch (action){
            case "":
                resp.setContentType("text/html");
                out.println("<html><body>");
                out.println("<h1>" + "Hello" + "</h1>");
                out.println("</body></html>");
                break;
            case "login":
              RequestDispatcher requestDispatcher = req.getRequestDispatcher("/web/login.jsp");
              requestDispatcher.include(req,resp);
              break;
            }
        }else {
            resp.setContentType("text/html");
            out.println("<html><body>");
            out.println("<h1>" + "message" + "</h1>");
            out.println("</body></html>");

        }
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();
        if (action != null){
            switch (action){
                case "login":
                    String name = req.getParameter("username");
                    String pass = req.getParameter("password");
                    resp.setContentType("text/html");
                    out.println("<html><body>");
                    out.println("<h1>" + name + " " + pass + "</h1>");
                    out.println("</body></html>");
                    break;
            }
        }else {
            resp.setContentType("text/html");
            out.println("<html><body>");
            out.println("<h1>" + "message" + "</h1>");
            out.println("</body></html>");

        }

    }
}