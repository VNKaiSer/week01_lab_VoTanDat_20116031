<%@ page import="vn.edu.iuh.fit.lab_week01.models.Account" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% Account account = (Account) request.getAttribute("account"); %>
<form method="post">
    <div class="container mx-auto mt-10 p-4">
        <div class="max-w-md mx-auto bg-white p-4 rounded shadow-lg">
            <div class="user-info space-y-2">
                <label for="name" class="font-bold">Tên:</label>
                <input type="text" id="name" name="name" value="<%=account.getFullName()%>" class="block" readonly>

                <label for="email" class="font-bold">Email:</label>
                <input type="email" id="email" name="email" value="<%=account.getEmail()%>" class="block" readonly>

                <label for="phone" class="font-bold">Điện thoại:</label>
                <input type="tel" id="phone" name="phone" value="<%=account.getPhone()%>" class="block" readonly>
            </div>

            <button id="edit-button" type="button" class="mt-4 bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded">
                Sửa
            </button>
        </div>
    </div>
</form>

<script>
    const editButton = document.getElementById("edit-button");
    const nameElement = document.getElementById("name");
    const emailElement = document.getElementById("email");
    const phoneElement = document.getElementById("phone");

    editButton.addEventListener("click", () => {
            // Chuyển nút "Sửa" thành nút "Lưu"
            if (editButton.textContent === "Lưu") {
                editButton.type = "submit";
            }
            editButton.textContent = "Lưu";


            // Bật trạng thái chỉnh sửa
        nameElement.removeAttribute("readonly");
        emailElement.removeAttribute("readonly");
        phoneElement.removeAttribute("readonly");

        // Đặt con trỏ vào ô nhập liệu đầu tiên (tên)
        nameElement.focus();

            // Đặt con trỏ vào ô nhập liệu đầu tiên (tên)
            nameElement.focus();

    });
</script>
