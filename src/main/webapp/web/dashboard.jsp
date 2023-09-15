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
            <li><a href="#" class="block py-2">Danh sách tài khoản</a></li>
            <li><a href="#" class="block py-2">Thêm tài khoản</a></li>
            <li><a href="#" class="block py-2">Cấp quyền</a></li>
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
            %>
                <tr>
                    <td class="border p-2"><%= acc.getAccountId() %></td>
                    <td class="border p-2"><%= acc.getFullName() %></td>
                    <td class="border p-2"><%= acc.getEmail() %></td>
                    <td class="border p-2"><%= acc.getPhone() %></td>
                    <td class="border p-2"><%= STATUS.values()[acc.getStatus()] %></td>
                    <td class="border p-2"><a href="${pageContext.request.contextPath}/web?action=edit-account&accountId=<%= acc.getAccountId() %>"><i class="fas fa-edit"></i></a></td>
                    <td class="border p-2"><a href="#"><i class="fas fa-trash-alt"></i></a></td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </main>
</div>
<!-- Modal content -->
<div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
    <!-- Modal content here -->
    <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
        <div class="sm:flex sm:items-start">
            <!-- Form để chỉnh sửa tài khoản -->
            <form action="updateAccount" method="post">
                <input type="hidden" name="accountID" id="editAccountID" value="">
                <div class="w-full">
                    <label for="editFullname" class="block text-gray-700 text-sm font-bold mb-2">Tên:</label>
                    <input type="text" class="form-input rounded-md shadow-sm mt-1 block w-full" id="editFullname" name="editFullname" required>
                </div>
                <div class="w-full mt-4">
                    <label for="editPhone" class="block text-gray-700 text-sm font-bold mb-2">Số điện thoại:</label>
                    <input type="text" class="form-input rounded-md shadow-sm mt-1 block w-full" id="editPhone" name="editPhone" required>
                </div>
                <div class="w-full mt-4">
                    <label for="editStatus" class="block text-gray-700 text-sm font-bold mb-2">Trạng thái:</label>
                    <input type="text" class="form-input rounded-md shadow-sm mt-1 block w-full" id="editStatus" name="editStatus" required>
                </div>
                <div class="mt-4">
                    <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                        Lưu
                    </button>
                    <button type="button" class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded ml-2" onclick="closeEditModal()">
                        Hủy
                    </button>
                </div>
            </form>
        </div>
    </div>
    <!-- End modal content -->
</div>
</body>
<script>
    // Lấy các trường input và modal
    const editModal = document.getElementById("editModal");
    const editFullname = document.getElementById("editFullname");
    const editPhone = document.getElementById("editPhone");
    const editStatus = document.getElementById("editStatus");
    const editAccountID = document.getElementById("editAccountID");

    // Lấy nút "Sửa" khi được nhấn
    function openEditModal(accountID, fullname, phone, status) {
        editAccountID.value = accountID;
        editFullname.value = fullname;
        editPhone.value = phone;
        editStatus.value = status;

        // Hiển thị modal khi nút "Sửa" được nhấn
        editModal.classList.remove("hidden");
        editModal.classList.add("block");
    }

    // Đóng modal khi bấm vào overlay hoặc nút "Hủy"
    function closeEditModal() {
        editModal.classList.add("hidden");
        editModal.classList.remove("block");
    }
</script>
</html>
