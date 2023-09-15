<%@ page import="vn.edu.iuh.fit.lab_week01.models.Account" %>
<%@ page import="static vn.edu.iuh.fit.lab_week01.constant.Constant.MAPSTATUS" %>
<%@ page import="vn.edu.iuh.fit.lab_week01.models.STATUS" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Account</title>
    <!-- Thêm liên kết đến Tailwind CSS -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<% Account account = (Account) request.getAttribute("account"); %>
<div class="min-h-screen flex items-center justify-center">
    <div class="bg-white p-8 rounded-lg shadow-md w-96">
        <h1 class="text-2xl font-semibold mb-6">Chỉnh Sửa Tài Khoản</h1>
        <form action="updateAccount" method="post">
            <div class="mb-4">
                <label for="accountId"  class="block text-sm font-medium text-gray-700">Tên:</label>
                <input disabled id="accountId" name="accountId" value="<%=account.getAccountId()%>">
            </div>
            <div class="mb-4">
                <label for="fullname" class="block text-sm font-medium text-gray-700">Tên:</label>
                <input type="text" id="fullname" name="fullname" value="<%=account.getFullName()%>" required
                       class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md">
            </div>
            <div class="mb-4">
                <label for="phone" class="block text-sm font-medium text-gray-700">Số điện thoại:</label>
                <input type="text" id="phone" name="phone" value="<%=account.getPhone()%>" required
                       class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md">
            </div>
            <div class="mb-4">
                <label for="status" class="block text-sm font-medium text-gray-700">Trạng thái:</label>
                <select id="status" name="status" required
                        class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md">
                    <option value="option1" <%= STATUS.values()[account.getStatus()].equals(STATUS.ACTIVE) ? "selected" : "" %>>ACTIVE</option>
                    <option value="option1" <%= STATUS.values()[account.getStatus()].equals(STATUS.DEACTIVE) ? "selected" : "" %>>DEACTIVE</option>
                    <option value="option2" <%= STATUS.values()[account.getStatus()].equals(STATUS.DELETED) ? "selected" : "" %>>DELETE</option>
                </select>
            </div>
            <div class="mb-4">
                <label for="status" class="block text-sm font-medium text-gray-700">Mật khẩu:</label>
                <input type="text" id="password" name="status" value="<%=account.getPassword()%>" required
                       class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md">
            </div>
            <div class="flex items-center space-x-4">
                <button type="submit"
                        class="px-4 py-2 bg-indigo-600 border border-transparent rounded-md font-semibold text-white hover:bg-indigo-700 focus:outline-none focus:ring focus:ring-indigo-200 focus:ring-opacity-50">
                    Lưu
                </button>
                <a href="${pageContext.request.contextPath}/web/dashboard.jsp"
                   class="px-4 py-2 bg-gray-300 border border-transparent rounded-md font-semibold text-gray-600 hover:bg-gray-400 focus:outline-none focus:ring focus:ring-gray-200 focus:ring-opacity-50">
                    Quay lại
                </a>
            </div>
        </form>
    </div>
</div>
<% %>
</body>
</html>
