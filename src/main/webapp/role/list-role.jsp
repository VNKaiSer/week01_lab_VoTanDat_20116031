<%@ page import="vn.edu.iuh.fit.lab_week01.models.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.lab_week01.models.STATUS" %>
<%@ page import="vn.edu.iuh.fit.lab_week01.models.Role" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h1 class="text-2xl font-semibold mb-4">Danh sách quyền</h1>
<table class="w-full">
    <thead>
    <tr>
        <th class="border p-2">ID</th>
        <th class="border p-2">Tên role</th>
        <th class="border p-2">Mô tả</th>
        <th class="border p-2">Trạng thái</th>
        <th class="border p-2">Sửa</th>
        <th class="border p-2">Xóa</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Role> roleList = (List<Role>) request.getAttribute("roles");
        for (Role acc : roleList) {
    %>
    <tr>
        <td class="border p-2"><%= acc.getRoleId() %></td>
        <td class="border p-2"><%= acc.getRoleName() %></td>
        <td class="border p-2"><%= acc.getDescription() %></td>
        <td class="border p-2"><%= acc.getStatus() %></td>
        <td class="border p-2"><a href="${pageContext.request.contextPath}/web?action=edit-role&roleId=<%= acc.getRoleId() %>"><i class="fas fa-edit"></i></a></td>
        <td class="border p-2"><a href="javascript:void(0);" data-account-id="<%=acc.getRoleId()%>" class="delete-button"><i class="fas fa-trash-alt"></i></a></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>