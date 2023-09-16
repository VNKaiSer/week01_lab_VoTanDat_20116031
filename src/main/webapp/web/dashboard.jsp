<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.lab_week01.models.Account" %>
<%@ page import="vn.edu.iuh.fit.lab_week01.models.STATUS" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" rel="stylesheet"> <!-- Thêm Font Awesome -->
</head>
<body class="bg-gray-100">
<div class="flex">
    <!-- Menu bên trái (sidebar) -->
    <aside class="bg-gray-800 text-white h-screen w-1/5 p-4">
        <h1 class="text-2xl font-semibold mb-4">Dashboard</h1>
        <ul>
            <li><a href="${pageContext.request.contextPath}/web?action=list-account" class="block py-2">Danh sách tài khoản</a></li>
            <li><a href="${pageContext.request.contextPath}/web?action=create-account" class="block py-2">Thêm tài khoản</a></li>
            <li><a href="${pageContext.request.contextPath}/web?action=list-role" class="block py-2">Danh sách quyền</a></li>
            <li><a href="${pageContext.request.contextPath}/web?action=add-role" class="block py-2">Cấp quyền</a></li>
        </ul>
    </aside>

    <!-- Nội dung chính -->
    <main class="bg-white w-4/5 p-4">
        <h1 class="text-2xl font-semibold mb-4">Danh sách tài khoản</h1>
        <table class="w-full">
            <thead>
            <tr>
                <th class="border p-2">ID</th>
                <th class="border p-2">Tên</th>
                <th class="border p-2">Email</th>
                <th class="border p-2">Số điện thoại</th>
                <th class="border p-2">Trạng thái</th>
                <th class="border p-2">Sửa</th>
                <th class="border p-2">Xóa</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Account> accountList = (List<Account>) request.getAttribute("accounts");
                for (Account acc : accountList) {
                    STATUS status;
                    if (acc.getStatus() == 0){
                        status = STATUS.DEACTIVE;
                    } else if (acc.getStatus() == 1) {
                        status = STATUS.ACTIVE;
                    } else {
                        status = STATUS.DELETED;
                    }
            %>
                <tr>
                    <td class="border p-2"><%= acc.getAccountId() %></td>
                    <td class="border p-2"><%= acc.getFullName() %></td>
                    <td class="border p-2"><%= acc.getEmail() %></td>
                    <td class="border p-2"><%= acc.getPhone() %></td>
                    <td class="border p-2"><%= status %></td>
                    <td class="border p-2"><a href="${pageContext.request.contextPath}/web?action=edit-account&accountId=<%= acc.getAccountId() %>"><i class="fas fa-edit"></i></a></td>
                    <td class="border p-2"><a href="javascript:void(0);" data-account-id="<%=acc.getAccountId()%>" class="delete-button"><i class="fas fa-trash-alt"></i></a></td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </main>
</div>
</body>
<script>
    const deleteButtons = document.querySelectorAll(".delete-button");

    deleteButtons.forEach(button => {
        button.addEventListener("click", () => {
            const accountId = button.getAttribute("data-account-id");
            const confirmation = confirm("Bạn có chắc chắn muốn xoá tài khoản này?");

            if (confirmation) {
                window.location.href = "web?action=delete-account&accountId=" + accountId;
            }
        });
    });
</script>
</html>
