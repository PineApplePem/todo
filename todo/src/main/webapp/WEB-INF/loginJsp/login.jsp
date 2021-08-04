<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<form action="/todo/Login" method="post">
IDまたはメールアドレス<input type="text" name="idMail" required><br>
パスワード<input type ="password" name="pass" required>
<input type="submit" value="ログイン">
</form>
<a href ="/todo/Create">ユーザー登録</a>
<c:if test="${not empty errorMsg}">
	<p>${errorMsg}</p>
</c:if>
</body>
</html>