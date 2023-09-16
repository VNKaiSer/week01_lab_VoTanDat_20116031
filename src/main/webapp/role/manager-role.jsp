<%@ page import="vn.edu.iuh.fit.lab_week01.models.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.lab_week01.models.Account" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body class="bg-gray-100 p-4">
<div class="max-w-md mx-auto bg-white p-4 rounded shadow-lg">
    <h1 class="text-2xl font-semibold mb-4">Phân Quyền</h1>
    <form method="POST">
        <!-- Dropdown cho việc chọn vai trò -->
        <div class="mb-4">
            <label for="role" class="block font-medium">Chọn Vai Trò:</label>
            <select name="role" id="role" class="w-full p-2 border rounded">
                <%
                    List<Role> roleList = (List<Role>) request.getAttribute("roles");
                    for (Role role : roleList) {
                %>
                <option value="<%= role.getRoleId() %>"><%= role.getRoleName() %></option>
                <%
                    }
                %>

            </select>
        </div>

        <!-- Dropdown cho việc chọn tài khoản -->
        <div class="mb-4">
            <label for="account" class="block font-medium">Chọn Tài Khoản:</label>
            <select name="account" id="account" class="w-full p-2 border rounded">
                <%
                    List<Account> accountList = (List<Account>) request.getAttribute("accounts");
                    for (Account account : accountList) {

                %>

                <option value="<%= account.getAccountId() %>"> <%= account.getAccountId() %> - <%= account.getFullName() %></option>

                <% } %>
            </select>
        </div>
        <div class="mb-4">
            <label for="status" class="block font-medium">Chọn Trạng Thái:</label>
            <select name="status" id="status" class="w-full p-2 border rounded">
                <option value="1">ENABLE</option>
                <option value="0">DISABLE</option>
            </select>
        </div>
        <div class="mb-4">
            <label for="note" class="block font-medium">Ghi chú:</label>
            <input type="text" name="note" id="note" class="w-full p-2 border rounded">
        </div>
        <div class="text-center">
            <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Cấp Quyền</button>
        </div>
    </form>
</div>
</body>
