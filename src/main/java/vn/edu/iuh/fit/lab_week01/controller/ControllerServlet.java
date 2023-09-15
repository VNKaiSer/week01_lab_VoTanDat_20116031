package vn.edu.iuh.fit.lab_week01.controller;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import vn.edu.iuh.fit.lab_week01.constant.UIClass;
import vn.edu.iuh.fit.lab_week01.constant.env;
import vn.edu.iuh.fit.lab_week01.models.Account;
import vn.edu.iuh.fit.lab_week01.services.AccountService;
import vn.edu.iuh.fit.lab_week01.services.impl.AccountServiceImpl;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

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
              req.getRequestDispatcher("/web/login.jsp").include(req,resp);
              break;
            case "register":
                req.getRequestDispatcher("/web/register.jsp").include(req,resp);
                break;
            case "edit-account":
                try {
                    AccountService accountService = new AccountServiceImpl();
                    Account account = accountService.getAccountById(req.getParameter("accountId"));
                    if (account != null){
                        req.setAttribute("account", account);
                        req.getRequestDispatcher("/account/edit-account.jsp").include(req,resp);
                    } else {
                        resp.sendRedirect(env.appName+"/web/dashboard.jsp");
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Not found account id = "+ req.getParameter("accountId")+"!');");
                        out.println("</script>");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
                case  "list-account":
                    AccountService accountService = null;
                    try {
                        accountService = new AccountServiceImpl();
                        List<Account> accounts = accountService.getAllAccount();
                        req.setAttribute("accounts", accounts);
                        req.getRequestDispatcher("/web/dashboard.jsp").forward(req, resp);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


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
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("timeLogin", System.currentTimeMillis());

            int status = accountService.login(username, password);
            System.out.println(status);
            switch (status){
                case 1:
                    List<Account> accounts = accountService.getAllAccount();
                    req.setAttribute("accounts", accounts);
//                    resp.sendRedirect(env.appName+"/web/dashboard.jsp");
                    req.getRequestDispatcher("/web/dashboard.jsp").forward(req, resp);

                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Welcome admin!');");
                    out.println("</script>");

                    break;
                case 0:
                    resp.sendRedirect(env.appName+"/web/home.jsp");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Welcome user!');");
                    out.println("</script>");
                    break;
                case -1:
                case -2:
                    resp.sendRedirect(env.appName+"/web/login.jsp");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Username or password is incorrect!');");
                    out.println("</script>");
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
                case "register":
                    register(req,resp,out);
                    break;
            }
        }else {
            resp.setContentType("text/html");
            out.println("<html><body>");
            out.println("<h1>" + "message" + "</h1>");
            out.println("</body></html>");

        }

    }

    private void register(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
    }
}