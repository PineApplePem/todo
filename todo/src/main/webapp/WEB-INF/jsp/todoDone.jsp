<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.Todo,java.util.List"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <% List<Todo> doneTodoList = (List<Todo>)request.getAttribute("doneTodoList"); %>
  <link rel="stylesheet" href="/todo/css/todo.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todoリスト</title>
</head>
<body>
<jsp:include page = "/WEB-INF/jsp/todo.jsp"/>
<p><a href ="/todo/Todo">完了済みのTodoを閉じる</a></p>

<% if(doneTodoList != null) { %>
 <% for(Todo todo : doneTodoList) { %>
    <p>
    <%= todo.getTodo() %><br>
    <% if(todo.getStart() != null) { %>
    	取り掛かる日：<%= todo.getDead() %><br>
    <% } %>
    <% if(todo.getDead() != null) { %>
    	締切：<%= todo.getDead() %><br>
    <% } %>
    <% if(todo.getDetail() != "") { %>	   
    	詳細：<%=todo.getDetail() %>
    <% } %>
    </p>
	<form action ="/todo/ChangeServlet" method ="post" style="display:inline;">
	<input type="hidden" name="id" value="done">
	<input type="hidden" name="number" value=<%=todo.getNumber()%>>
	<input type ="hidden" name="done" value=<%=todo.getDone() %>>
	<input type ="submit" value="未完了">
	</form>
	<form action ="/todo/ChangeServlet" method ="post">
	<input type="hidden" name="id" value="delete" >
	<input type="hidden" name="number" value=<%=todo.getNumber()%>>
	<input type="submit" value="削除">
	</form>
	
	<a href ="/todo/Todo?viewAddition=${todo.number}">コメントを見る</a>
				
	<c:if test="${viewAddition == todo.number}">
		<c:forEach var="addition" items="${additionList}">
			<br>
			<c:out value ="${addition.comment}" />
			(<c:out value ="${addition.createTime}" />)
			<a href ="/todo/AdditionDelete?delete=${addition.id}">削除</a><br>
		</c:forEach>
		<a href ="/todo/Todo">コメントを閉じる</a>
	</c:if>
	
  <% } %>
<% } %>
</body>
</html>