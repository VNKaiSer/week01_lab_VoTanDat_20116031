package vn.edu.iuh.fit.lab_week01.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.lab_week01.constant.env;
import vn.edu.iuh.fit.lab_week01.models.*;
import vn.edu.iuh.fit.lab_week01.services.AccountService;
import vn.edu.iuh.fit.lab_week01.services.GrandAccessService;
import vn.edu.iuh.fit.lab_week01.services.LogService;
import vn.edu.iuh.fit.lab_week01.services.RoleService;
import vn.edu.iuh.fit.lab_week01.services.impl.AccountServiceImpl;
import vn.edu.iuh.fit.lab_week01.services.impl.GrandAccessServiceImpl;
import vn.edu.iuh.fit.lab_week01.services.impl.LogServiceImpl;
import vn.edu.iuh.fit.lab_week01.services.impl.RoleServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
    HttpSession session;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        session = req.getSession();
        Boolean isLogin = (Boolean) session.getAttribute("isLogin");
        try {
            if (action != null) {
                switch (action) {
                    case "":
//                        session.setAttribute("isLogin", false);
                        sendHelloResponse(resp);
                        break;
                    case "login":
                        session.setAttribute("isLogin", false);
                        forwardToPage("/web/login.jsp", req, resp);
                        break;
                    case "register":
                        forwardToPage("/web/register.jsp", req, resp);
                        break;
                    case "edit-account":
                        if (!isLogin) {
                            forwardToPage("/web/login.jsp", req, resp);
                        } else {
                            handleEditAccount(req, resp);
                        }
                        break;
                    case "list-account":
                        if (!isLogin) {
                            forwardToPage("/web/login.jsp", req, resp);
                        } else {
                            handleListAccount(req, resp);
                        }
                        break;
                    case "create-account":
                        if (!isLogin) {
                            forwardToPage("/web/login.jsp", req, resp);
                        } else {
                            forwardToPage("/web/dashboard.jsp", req, resp);
                        }
                        break;
                    case "delete-account":
                        if (!isLogin) {
                            forwardToPage("/web/login.jsp", req, resp);
                        } else {
                            handleDeleteAccount(req, resp);
                        }
                        break;
                    case "list-role":
                        if (!isLogin) {
                            forwardToPage("/web/login.jsp", req, resp);
                        } else {
                            handleListRole(req, resp);
                        }
                        break;
                    case "manager-role":
                        if (!isLogin) {
                            forwardToPage("/web/login.jsp", req, resp);
                        } else {
                            handleManagerRole(req, resp);
                        }
                        break;
                    case "user-information":
                        if (!isLogin) {
                            forwardToPage("/web/login.jsp", req, resp);
                        } else {
                            handleUserInformation(req, resp);
                        }
                        break;
                    case "user-role":
                        if (!isLogin) {
                            forwardToPage("/web/login.jsp", req, resp);
                        } else {
                            handleUserRoles(req, resp);
                        }
                        break;
                    case "logout":
                        if (!isLogin) {
                            forwardToPage("/web/login.jsp", req, resp);
                        } else {
                            handleLogout(req, resp);
                        }
                        break;

                }
            } else {
                sendHelloResponse(resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleLogout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        writeLogLogout("");
        resp.sendRedirect(env.appName + "/web?action=login");
    }

    private void handleUserRoles(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        RoleService roleService = new RoleServiceImpl();
        Account account = (Account) session.getAttribute("account");
        System.out.println("account = " + account);
        List<Role> roles = roleService.getRolesFromAccount(account.getAccountId());
        req.setAttribute("roles", roles);
        forwardToPage("/web/home.jsp", req, resp);
    }

    private void handleUserInformation(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Account account = (Account) session.getAttribute("account");
        req.setAttribute("account", account);
        forwardToPage("/web/home.jsp", req, resp);
    }

    private void handleManagerRole(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        RoleService roleService = new RoleServiceImpl();
        AccountService accountService = new AccountServiceImpl();
        List<Role> roles = roleService.getAllRole();
        List<Account> accounts = accountService.getAllAccount();
        req.setAttribute("accounts", accounts);
        req.setAttribute("roles", roles);
        forwardToPage("/web/dashboard.jsp", req, resp);
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
                forwardToPage("/web/dashboard.jsp", req, resp);
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
            GrandAccessService grandAccessService = new GrandAccessServiceImpl();

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
                    case "manager-role":
                        handleManagerRolePost(req, resp, grandAccessService);
                        break;
                    case "user-information":
                        handleEditUserInformation(req, resp, accountService);
                    default:
                        sendHelloResponse(resp);
                        break;
                }
            } else {
                sendHelloResponse(resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleEditUserInformation(HttpServletRequest req, HttpServletResponse resp, AccountService accountService) throws Exception {
        Account account = (Account) session.getAttribute("account");
        req.getParameterMap().forEach(
                (key, value) -> {
                    System.out.println(key + " : " + Arrays.toString(value));
                }
        );
        account.setFullName(req.getParameter("name"));
        account.setEmail(req.getParameter("email"));
        account.setPhone(req.getParameter("phone"));
        accountService.editAccount(account);
        session.removeAttribute("account");
        session.setAttribute("account", account);
        resp.sendRedirect(env.appName + "/web?action=user-information");

    }

    private void handleManagerRolePost(HttpServletRequest req, HttpServletResponse resp, GrandAccessService grandAccessService) throws Exception {
        String accountId = req.getParameter("account");
        String roleId = req.getParameter("role");
        String note = req.getParameter("note");
        String status = req.getParameter("status");
        if (status.equals("1")) {
            grandAccessService.insertGrandAccess(new GrantAccess(roleId, accountId, ISGRANT.ENABLED, note));
        } else {
            grandAccessService.insertGrandAccess(new GrantAccess(roleId, accountId, ISGRANT.DISABLED, note));
        }
        sendErrorMessageAndRedirect(resp, "Phân quyền không thành công");
    }

    private void handleListRole(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        RoleService roleService = new RoleServiceImpl();
        List<Role> roles = roleService.getAllRole();
        req.setAttribute("roles", roles);
        forwardToPage("/web/dashboard.jsp", req, resp);
    }


    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        session.setAttribute("username", username);
        AccountService accountService = new AccountServiceImpl();

        try {
            int status = accountService.login(username, password);
            System.out.println(status);
            switch (status) {
                case 1:
                    handleAdminLogin(req, resp);
                    session.setAttribute("isLogin", true);
                    break;
                case 0:
                    handleUserLogin(req, resp);
                    session.setAttribute("isLogin", true);
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

    /***
     * function to write log write login
     * @param message
     * @throws Exception
     */
    private void writeLogLogin(String message) throws Exception {
        Account account = (Account) session.getAttribute("account");
        LocalDateTime timeLogin = LocalDateTime.now();
        Logs logs = new Logs(account.getAccountId(), timeLogin, timeLogin, message);
        session.setAttribute("loginLog", logs);
    }

    private void writeLogLogout(String message) throws Exception {
        LogService logService = new LogServiceImpl();
        Logs logs = (Logs) session.getAttribute("loginLog");
        LocalDateTime timeLogout = LocalDateTime.now();
        logs.setLogOutDate(timeLogout);
        logs.setNotes(message);
        logService.insertLogs(logs);
        // remote session
        session.invalidate();
    }

    private void handleAdminLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AccountService accountService = new AccountServiceImpl();
        List<Account> accounts = accountService.getAllAccount();
        Account account = accountService.getAccountById(req.getParameter("username"));
        session = req.getSession();
        session.setAttribute("account", account);
        writeLogLogin("");
        req.setAttribute("accounts", accounts);
        forwardToPage("/web/dashboard.jsp", req, resp);
        sendSuccessMessage(resp, "Welcome admin!");
    }

    private void handleUserLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AccountService accountService = new AccountServiceImpl();
        Account account = accountService.getAccountById(req.getParameter("username"));
        session = req.getSession();
        session.setAttribute("account", account);
        writeLogLogin("");
        forwardToPage("/web/home.jsp", req, resp);
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