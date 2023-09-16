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
import vn.edu.iuh.fit.lab_week01.models.STATUS;
import vn.edu.iuh.fit.lab_week01.services.AccountService;
import vn.edu.iuh.fit.lab_week01.services.impl.AccountServiceImpl;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try{
        if (action != null) {
            switch (action) {
                case "":
                    sendHelloResponse(resp);
                    break;
                case "login":
                    forwardToPage("/web/login.jsp", req, resp);
                    break;
                case "register":
                    forwardToPage("/web/register.jsp", req, resp);
                    break;
                case "edit-account":
                    handleEditAccount(req, resp);
                    break;
                case "list-account":
                    handleListAccount(req, resp);
                    break;
                case "create-account":
                    forwardToPage("/account/create-account.jsp", req, resp);
                    break;
                case "delete-account":
                    handleDeleteAccount(req, resp);
                    break;
            }
        } else {
            sendHelloResponse(resp);
        }} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void sendHelloResponse(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello</h1>");
        out.println("</body></html>");
    }

    private void forwardToPage(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(page).include(req, resp);
    }

    private void handleEditAccount(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String accountId = req.getParameter("accountId");
        AccountService accountService = new AccountServiceImpl();

        try {
            Account account = accountService.getAccountById(accountId);

            if (account != null) {
                req.setAttribute("account", account);
                forwardToPage("/account/edit-account.jsp", req, resp);
            } else {
                sendErrorMessageAndRedirect(resp, "Not found account id = " + accountId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleListAccount(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AccountService accountService = new AccountServiceImpl();

        try {
            List<Account> accounts = accountService.getAllAccount();
            req.setAttribute("accounts", accounts);
            forwardToPage("/web/dashboard.jsp", req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleDeleteAccount(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AccountService accountService = new AccountServiceImpl();
        String accountId = req.getParameter("accountId");
        accountService.deleteAccount(accountId);

        resp.sendRedirect(env.appName + "/web?action=list-account");
        sendErrorMessage(resp, "Not found account id = " + req.getParameter("accountId"));
    }

    private void sendErrorMessageAndRedirect(HttpServletResponse resp, String errorMessage) throws IOException {
        resp.sendRedirect(env.appName + "/web/dashboard.jsp");
        PrintWriter out = resp.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('" + errorMessage + "');");
        out.println("</script>");
    }
    private void sendErrorMessage(HttpServletResponse resp, String errorMessage) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Error</h1>");
        out.println("<p>" + errorMessage + "</p>");
        out.println("</body></html>");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            AccountService accountService = new AccountServiceImpl();

        if (action != null) {
            switch (action) {
                case "login":
                    handleLogin(req, resp);
                    break;
                case "register":
                    handleRegister(req, resp);
                    break;
                case "edit-account":
                    handleEditAccountPost(req, resp, accountService);
                    break;
                case "create-account":
                    handleCreateAccount(req, resp, accountService);
                    break;
                default:
                    sendHelloResponse(resp);
                    break;
            }
        } else {
            sendHelloResponse(resp);
        }}catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) {
        
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setAttribute("username", username);
        session.setAttribute("timeLogin", System.currentTimeMillis());
        AccountService accountService = new AccountServiceImpl();

        try {
            int status = accountService.login(username, password);

            switch (status) {
                case 1:
                    handleAdminLogin(req, resp);
                    break;
                case 0:
                    handleUserLogin(resp);
                    break;
                case -1:
                case -2:
                    handleLoginError(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleAdminLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AccountService accountService = new AccountServiceImpl();
        List<Account> accounts = accountService.getAllAccount();
        req.setAttribute("accounts", accounts);
        forwardToPage("/web/dashboard.jsp", req, resp);
        sendSuccessMessage(resp, "Welcome admin!");
    }

    private void handleUserLogin(HttpServletResponse resp) throws IOException {
        resp.sendRedirect(env.appName + "/web/home.jsp");
        sendSuccessMessage(resp, "Welcome user!");
    }

    private void handleLoginError(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(env.appName + "/web?action=login");
        sendErrorMessage(resp, "Username or password is incorrect!");
    }

    private void sendSuccessMessage(HttpServletResponse resp, String message) throws IOException {
        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('" + message + "');");
            out.println("</script>");
            out.println("</body></html>");
        }
    }


    private void handleCreateAccount(HttpServletRequest req, HttpServletResponse resp, AccountService accountService) throws Exception {
        Map<String, String> inputData = extractInputData(req);
        Account account = createAccountFromInputData(inputData);

        if (account != null) {
            if (accountService.createAccount(account)) {
                resp.sendRedirect(env.appName + "/web?action=list-account");
                sendSuccessMessage(resp, "Create account successfully!");
            } else {
                resp.sendRedirect(env.appName + "/web?action=list-account");
                sendErrorMessage(resp, "Create account failed!");
            }
        } else {
            // Xử lý khi có lỗi trong dữ liệu đầu vào
            sendErrorMessage(resp, "Invalid input data!");
        }
    }

    private Map<String, String> extractInputData(HttpServletRequest req) {
        Map<String, String> inputData = new HashMap<>();
        inputData.put("accountId", req.getParameter("accountId"));
        inputData.put("fullName", req.getParameter("fullname"));
        inputData.put("password", req.getParameter("password"));
        inputData.put("email", req.getParameter("email"));
        inputData.put("phone", req.getParameter("phone"));
        inputData.put("status", req.getParameter("status"));
        return inputData;
    }

    private Account createAccountFromInputData(Map<String, String> inputData) {
        String accountId = inputData.get("accountId");
        String fullName = inputData.get("fullName");
        String password = inputData.get("password");
        String email = inputData.get("email");
        String phone = inputData.get("phone");
        String statusStr = inputData.get("status");

        try {
            int statusInt = Integer.parseInt(statusStr);
            STATUS status = convertToStatus(statusInt);
            return new Account(accountId, fullName, password, email, phone, status);
        } catch (NumberFormatException e) {
            return null; // Trả về null nếu có lỗi trong việc chuyển đổi status
        }
    }

    private STATUS convertToStatus(int statusInt) {
        switch (statusInt) {
            case 1:
                return STATUS.ACTIVE;
            case 0:
                return STATUS.DEACTIVE;
            default:
                return STATUS.DELETED;
        }
    }


    private void handleEditAccountPost(HttpServletRequest req, HttpServletResponse resp, AccountService accountService) throws Exception {
        Map<String, String> inputData = extractInputData(req);
        Account account = createAccountFromInputData(inputData);

        if (account != null) {
            if (accountService.editAccount(account)) {
                resp.sendRedirect(env.appName + "/web?action=list-account");
                sendSuccessMessage(resp, "Edit account successfully!");
            } else {
                resp.sendRedirect(env.appName + "/web?action=list-account");
                sendErrorMessage(resp, "Edit account failed!");
            }
        } else {
            // Xử lý khi có lỗi trong dữ liệu đầu vào
            sendErrorMessage(resp, "Invalid input data!");
        }
    }



}