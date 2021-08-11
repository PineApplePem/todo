<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <link rel="stylesheet" href="/todo/css/login.css">  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>パスワードの変更</title>
</head>
<body>
<div class="main">
<h5>新しいパスワードを入力してください。</h5>
<form action ="/todo/User/PassChangeServlet" method="post" class="form">
<input type ="password" name="pass" class="change-text" required><br>
<input type ="submit" value="送信" class="normal-btn">
<c:if test="${errorMsg != null}" >
	<p class="error">${errorMsg}</p>
</c:if>
</form>
</div>
</body>
</html>