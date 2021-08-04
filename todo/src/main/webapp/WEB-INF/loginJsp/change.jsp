<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報の変更</title>
</head>
<body>
<form action ="/todo/User/ChangeServlet" method="post">
ID:<input type ="text" name="id" value="${user.id}" required><br>
メールアドレス:<input type ="text" name="mail" value="${user.mail}" required><br>
<input type ="submit" value="変更">
</form>

<c:if test="${errorMsg != null}" >
	${errorMsg}<br>
</c:if>
<c:if test="${uniqueErrorMsg != null}" >
	${uniqueErrorMsg}<br>
</c:if>
<c:if test="${mailErrorMsg != null}" >
	${mailErrorMsg}<br>
</c:if>
<c:if test="${deleteErrorMsg != null}" >
	${deleteErrorMsg}<br>
</c:if>
<a href="/todo/User/PassCheckServlet?move=pass">パスワードの変更</a>
<a href="/todo/User/PassCheckServlet?move=delete">アカウントの削除</a>
<a href="/todo/login/changeTop.jsp">アカウント情報に戻る</a>
</body>
</html>