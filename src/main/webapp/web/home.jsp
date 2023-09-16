<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" rel="stylesheet"> <!-- Thêm Font Awesome -->
</head>
<body class="bg-gray-100">
<div class="flex">
    <aside class="bg-gray-800 text-white h-screen w-1/5 p-4">
        <h1 class="text-2xl font-semibold mb-4">Home</h1>
        <ul>
            <li><a href="${pageContext.request.contextPath}/web?action=user-information" class="block py-2">Thông tin cá nhân</a></li>
            <li><a href="${pageContext.request.contextPath}/web?action=create-account" class="block py-2">Danh sách quyền</a></li>
        </ul>
    </aside>

    <!-- Nội dung chính -->
    <main class="bg-white w-4/5 p-4">
        <%
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equals("home")) {
            %><jsp:include page="/web/home.jsp" /><%
            } else if (action.equals("user-information")) {
                %><jsp:include page="/account/user-information.jsp" /><%
            }
        }
    %>
    </main>
</div>
</body>
</html>
