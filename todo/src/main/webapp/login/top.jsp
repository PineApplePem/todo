<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ画面</title>
</head>
<body>
<c:if test ="${sessionScope.user} == null" >
	<c:redirect url ="/todo/Login"/>
</c:if>
<a href ="/todo/User/LogoutServlet">ログアウト</a>
<a href ="/todo/login/changeTop.jsp">${user.id}</a>
</body>
</html>