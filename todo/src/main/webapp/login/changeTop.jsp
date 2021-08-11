<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/todo/css/login.css">      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ユーザー情報</title>
</head>
<body>
<div class="main">
<c:if test ="${empty user}" >
	<c:redirect url ="/Login"/>
</c:if>
<h3>ユーザー情報</h3>
<h5>ID</h5>
${user.id}<br>
<h5>メールアドレス</h5>
${user.mail}<br>
<h5>パスワード</h5>
******<br>
<a href ="/todo/User/ChangeServlet">ユーザー情報の変更</a><br>
<a href ="/todo/Todo">トップページへ戻る</a><br>
</div>
</body>
</html>