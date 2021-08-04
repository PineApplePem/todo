<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報</title>
</head>
<body>
<c:if test ="${empty user}" >
	<c:redirect url ="/Login"/>
</c:if>
ユーザー情報<br>
ID:${user.id}<br>
メールアドレス:${user.mail}<br>
パスワード:******<br>
<a href ="/todo/User/ChangeServlet">ユーザー情報の変更</a><br>
<a href ="/todo/Todo">トップページへ戻る</a><br>
</body>
</html>