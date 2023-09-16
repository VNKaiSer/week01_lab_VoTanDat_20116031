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
    <!-- Menu bên trái (sidebar) -->
    <aside class="bg-gray-800 text-white h-screen w-1/5 p-4">
        <h1 class="text-2xl font-semibold mb-4">Dashboard</h1>
        <ul>
            <li><a href="${pageContext.request.contextPath}/web?action=list-account" class="block py-2">Danh sách tài khoản</a></li>
            <li><a href="${pageContext.request.contextPath}/web?action=create-account" class="block py-2">Thêm tài khoản</a></li>
            <li><a href="${pageContext.request.contextPath}/web?action=list-role" class="block py-2">Danh sách quyền</a></li>
            <li><a href="${pageContext.request.contextPath}/web?action=manager-role" class="block py-2">Cấp quyền</a></li>
        </ul>
    </aside>

    <!-- Nội dung chính -->
    <main class="bg-white w-4/5 p-4">
        <%
                String action = request.getParameter("action");
                if (action != null) {
                    if (action.equals("list-account")) {
                    %><jsp:include page="/account/list-account.jsp" /><%
                    } else if (action.equals("edit-account")) {
                        %><jsp:include page="/account/edit-account.jsp" /><%
                    } else if (action.equals("create-account")) {
                        %><jsp:include page="/account/create-account.jsp" /><%
                    } else if (action.equals("list-role")) {
                        %><jsp:include page="/role/list-role.jsp" /><%
                    } else if (action.equals("manager-role")) {
                        %><jsp:include page="/role/manager-role.jsp" /><%
                    }
        }
        %>
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
