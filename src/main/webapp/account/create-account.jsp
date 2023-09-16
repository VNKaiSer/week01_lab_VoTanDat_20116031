<%@ page import="vn.edu.iuh.fit.lab_week01.models.Account" %>
<%@ page import="vn.edu.iuh.fit.lab_week01.models.STATUS" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account</title>
    <!-- Thêm liên kết đến Tailwind CSS -->
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="min-h-screen flex items-center justify-center">
    <div class="bg-white p-8 rounded-lg shadow-md w-96">
        <h1 class="text-2xl font-semibold mb-6">Tạo Tài Khoản</h1>
        <form method="post">
            <div class="mb-4">
                <label for="accountId"  class="block text-sm font-medium text-gray-700">ID:</label>
                <input id="accountId" name="accountId" >
            </div>
            <div class="mb-4">
                <label for="fullname" class="block text-sm font-medium text-gray-700">Tên:</label>
                <input type="text" id="fullname" name="fullname"  required
                       class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md">
            </div>
            <div class="mb-4">
                <label for="email" class="block text-sm font-medium text-gray-700">Email:</label>
                <input type="text" id="email" name="email"  required
                       class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md">
            </div>
            <div class="mb-4">
                <label for="phone" class="block text-sm font-medium text-gray-700">Số điện thoại:</label>
                <input type="text" id="phone" name="phone"  required
                       class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md">
            </div>
            <div class="mb-4">
                <label for="status" class="block text-sm font-medium text-gray-700">Trạng thái:</label>
                <select id="status" name="status" required
                        class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md">
                    <option value="1">ACTIVE</option>
                    <option value="-1" >DEACTIVE</option>
                    <option value="0" >DELETE</option>
                </select>
            </div>
            <div class="mb-4">
                <label for="password" class="block text-sm font-medium text-gray-700">Mật khẩu:</label>
                <input type="password" id="password" name="password"  required
                       class="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md">
            </div>
            <div class="flex items-center space-x-4">
                <button type="submit"
                        class="px-4 py-2 bg-indigo-600 border border-transparent rounded-md font-semibold text-white hover:bg-indigo-700 focus:outline-none focus:ring focus:ring-indigo-200 focus:ring-opacity-50">
                    Tạo
                </button>
                <a href="${pageContext.request.contextPath}/web?action=list-account"
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
