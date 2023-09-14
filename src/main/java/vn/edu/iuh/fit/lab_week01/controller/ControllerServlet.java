package vn.edu.iuh.fit.lab_week01.controller;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.lab_week01.constant.UIClass;
import vn.edu.iuh.fit.lab_week01.constant.env;
import vn.edu.iuh.fit.lab_week01.services.AccountService;
import vn.edu.iuh.fit.lab_week01.services.impl.AccountServiceImpl;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();


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

    private void login(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
        try {
            AccountService accountService = new AccountServiceImpl();
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            int status = accountService.login(username, password);
            System.out.println(status);
            switch (status){
                case 1:
                    resp.sendRedirect("/"+ env.appName+"/web/home.jsp");
                    out.println(UIClass.alerSuccess("Login success with admin account"));

                    break;
                case 0:
                    resp.sendRedirect("/"+ env.appName+"/web/home.jsp");
                    out.println(UIClass.alerWarning("Login success with user account"));

                case -1:
                case -2:
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/web/login.jsp");
                    requestDispatcher.include(req,resp);

                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();
        if (action != null){
            switch (action){
                case "login":
                    login(req,resp,out);
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