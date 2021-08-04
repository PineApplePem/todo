<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワードの変更</title>
</head>
<body>
新しいパスワードを入力してください。
<form action ="/todo/User/PassChangeServlet" method="post">
<input type ="password" name="pass"><br>
<input type ="submit" value="送信">
<c:if test="${errorMsg != null}" >
	${errorMsg}
</c:if>
</form>
</body>
</html>