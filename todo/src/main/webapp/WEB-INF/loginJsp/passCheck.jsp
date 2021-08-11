<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link rel="stylesheet" href="/todo/css/login.css">      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>パスワードの確認</title>
</head>
<p>
</p>
<body>
<div class="main">
<h5>現在のパスワードを入力してください</h5>
<form action="/todo/User/PassCheckServlet" method="post" class="form">
<input type="password" name="pass" class="change-text" required><br>
<input type="hidden" name="move" value="${move}">
<input type="submit" value="送信" class="normal-btn">
<p class="error">${errorMsg}</p>
</form>
<a href="/todo/Todo">トップページへ戻る</a><br>
<a href="/todo/User/ChangeServlet">アカウントの変更ページへ戻る</a><br>
</div>
</body>
</html>