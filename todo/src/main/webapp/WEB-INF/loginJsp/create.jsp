<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/todo/css/login.css">  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ユーザー登録</title>
</head>
<body>
<div class="main">
<h3>ユーザー登録</h3>
<form action="/todo/Create" method="post" class="form">
メールアドレス:<input type="text" name="mail" required class="text"><br>
ID:<input type ="text" name=id required class="text"><br>
パスワード:<input type="password" name=pass required class="text"><br>
<input type="submit" value="ユーザー登録" class="normal-btn"> 
<c:if test="${errorMsg != null}" >
	<p class="error">${errorMsg}</p>
</c:if>
<c:if test="${uniqueErrorMsg != null}" >
	<p class="error">${uniqueErrorMsg}</p>
</c:if>
<c:if test="${mailErrorMsg != null}" >
	<p class="error">${mailErrorMsg}</p>
</c:if>
</form>
<a href ="/todo/Login">ログイン画面に戻る</a> 
</div>
</body>
</html>