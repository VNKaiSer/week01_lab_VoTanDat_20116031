<%@ page import="vn.edu.iuh.fit.lab_week01.models.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.lab_week01.models.STATUS" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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