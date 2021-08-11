<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<link rel="stylesheet" href="/todo/css/login.css">  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ユーザー情報の変更</title>
</head>
<body>
<div class = "main">
<h3>ユーザー情報の変更</h3>
<form action ="/todo/User/ChangeServlet" method="post" class="form">
ID<br>
<input type ="text" name="id" value="${user.id}" required class="change-text"><br>
メールアドレス<br>
<input type ="text" name="mail" value="${user.mail}" required class="change-text"><br>
<input type ="submit" value="変更" class="normal-btn">
</form>

<c:if test="${errorMsg != null}" >
	<p class="error">${errorMsg}</p>
</c:if>
<c:if test="${uniqueErrorMsg != null}" >
	<p class="error">${uniqueErrorMsg}</p>
</c:if>
<c:if test="${mailErrorMsg != null}" >
	<p class="error">${mailErrorMsg}<br></p>
</c:if>
<c:if test="${deleteErrorMsg != null}" >
	<p class="error">${deleteErrorMsg}<br></p>
</c:if>
<a href="/todo/User/PassCheckServlet?move=pass">パスワードの変更</a><br>
<a href="/todo/User/PassCheckServlet?move=delete">アカウントの削除</a><br>
<a href="/todo/login/changeTop.jsp">アカウント情報に戻る</a><br>
</div>
</body>
</html>