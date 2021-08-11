<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/todo/css/login.css">      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>アカウントの削除</title>
</head>
<body>
<div class="main">
<h5>本当に削除してもいいですか？</h5>
<form action="/todo/User/DeleteServlet" method="post">
<label><input type="radio" name="move" value="yes" checked>はい</label><br>
<label><input type="radio" name="move" value="no">いいえ</label> <br>
<input type ="submit" value="送信">
</form>
</div>
</body>
</html>