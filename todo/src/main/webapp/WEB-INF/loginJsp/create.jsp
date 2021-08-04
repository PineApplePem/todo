<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<form action="/login/Create" method="post">
メールアドレス:<input type="text" name="mail" required><br>
ID:<input type ="text" name=id required><br>
パスワード:<input type="password" name=pass required><br>
<input type="submit" value="ユーザー登録"> 
<c:if test="${errorMsg != null}" >
	${errorMsg}
</c:if>
<c:if test="${uniqueErrorMsg != null}" >
	${uniqueErrorMsg}
</c:if>
<c:if test="${mailErrorMsg != null}" >
	${mailErrorMsg}
</c:if>
</form>
<a href ="/login/Login">ログイン画面に戻る</a> 
</body>
</html>