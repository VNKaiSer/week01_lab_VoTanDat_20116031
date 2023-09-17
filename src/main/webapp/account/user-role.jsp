<%@ page import="vn.edu.iuh.fit.lab_week01.models.Role" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17/9/2023
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="max-w-xl mx-auto bg-white p-6 rounded-lg shadow">
    <h1 class="text-2xl font-semibold mb-4">Danh sách vai (roles) của người dùng</h1>
    <table class="min-w-full table-auto">
        <thead>
        <tr>
            <th class="py-2 px-4 bg-gray-300 font-semibold text-left">STT</th>
            <th class="py-2 px-4 bg-gray-300 font-semibold text-left">Tên vai</th>
            <th class="py-2 px-4 bg-gray-300 font-semibold text-left">Mô tả</th>
            <th class="py-2 px-4 bg-gray-300 font-semibold text-left">Trạng thái</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Role> roleList = (List<Role>) request.getAttribute("roles");
            for (Role role : roleList) {
        %>
        <tr>
            <td class="border px-4 py-2"><%= role.getRoleId() %></td>
            <td class="border px-4 py-2"><%= role.getRoleName() %></td>
            <td class="border px-4 py-2"><%= role.getDescription() %></td>
            <td class="border px-4 py-2"><%= role.getStatus() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
