<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワードの確認</title>
</head>
<p>
</p>
<body>
<p>現在のパスワードを入力してください</p>
<form action="/todo/User/PassCheckServlet" method="post" >
<input type="password" name="pass" required><br>
<input type="hidden" name="move" value="${move}">
<input type="submit" value="送信">
<p>${errorMsg}</p>
</form>
<a href="/todo/Todo">トップページへ戻る</a>
<a href="/todo/User/ChangeServlet">アカウントの変更ページへ戻る</a>
</body>
</html>