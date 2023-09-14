<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.16/dist/tailwind.min.css">

</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
<div class="bg-white p-8 shadow-md rounded-lg">
    <h1 class="text-2xl font-semibold mb-4">Login</h1>
    <form method="post">
        <div class="mb-4">
            <label for="username" class="block font-medium text-gray-700">Username</label>
            <input type="text" id="username" name="username" class="border p-2 rounded w-full px-5" required>
        </div>
        <div class="mb-4">
            <label for="password" class="block font-medium text-gray-700">Password</label>
            <input type="password" id="password" name="password" class="border p-2 rounded w-full px-5" required>
        </div>
        <button type="submit" class="bg-blue-500 text-white p-2 rounded hover:bg-blue-600">Login</button>
        <button type="submit" class="bg-green-500 text-white p-2 rounded hover:bg-green-600">Register</button>
    </form>
</div>
</body>
</html>
