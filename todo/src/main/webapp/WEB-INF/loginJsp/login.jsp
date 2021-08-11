<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/todo/css/login.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ログイン画面</title>
</head>
<body>
<div class = main>
<h3>ログイン</h3>
<form action="/todo/Login" method="post" class="form">
IDまたはメールアドレス<br>
<input type="text" name="idMail" required class="text"><br>
パスワード<br>
<input type ="password" name="pass" required class="text"><br>
<input type="submit" value="ログイン" class="login-btn">
</form>
<c:if test="${not empty errorMsg}">
	<p class="error">${errorMsg}</p>
</c:if>
<a href ="/todo/Create" class="user-create">ユーザー登録</a>
</div>
</body>
</html>