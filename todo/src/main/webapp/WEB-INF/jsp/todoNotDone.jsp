<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link rel="stylesheet" href="/todo/css/todo.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todoリスト</title>
</head>
<body>
<jsp:include page = "/WEB-INF/jsp/todo.jsp"/>
<p><a href ="/todo/Todo?move=done">完了済みのTodoを表示</a></p>
</body>
</html>