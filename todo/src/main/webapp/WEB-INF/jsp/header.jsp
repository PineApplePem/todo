<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <link rel="stylesheet" href="/todo/css/todo.css">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
<header>
<h2>Todoリスト</h2>
<a href ="/todo/User/LogoutServlet" class =user-info>ログアウト</a>
<a href ="/todo/login/changeTop.jsp" class = user-info>${user.id}</a>
</header>
</body>
</html>