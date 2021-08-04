<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウントの削除</title>
</head>
<body>
本当に削除してもいいですか？
<form action="/todo/User/DeleteServlet" method="post">
<label><input type="radio" name="move" value="yes" checked>はい</label>
<label><input type="radio" name="move" value="no">いいえ</label> <br>
<input type ="submit" value="送信">
</form>
</body>
</html>