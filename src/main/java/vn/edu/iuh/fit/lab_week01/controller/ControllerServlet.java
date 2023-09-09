package vn.edu.iuh.fit.lab_week01.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null){
            switch (action){
            case "":
                resp.setContentType("text/html");

                // Hello
                PrintWriter out = resp.getWriter();
                out.println("<html><body>");
                out.println("<h1>" + "Hello" + "</h1>");
                out.println("</body></html>");
                break;

            case "login":
                break;
            }
        }else {
            resp.setContentType("text/html");

            // Forward the request to the "index.jsp" view
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}



//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//
//    }
