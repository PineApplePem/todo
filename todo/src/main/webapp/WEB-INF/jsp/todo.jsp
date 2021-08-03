<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.Todo,java.util.List,java.sql.Date"%>
 <% List<Todo> todoList = (List<Todo>)request.getAttribute("todoList"); %>
 <% Date today = (Date)request.getAttribute("today"); %>
 <html>
 <body>
 <% if (todoList != null) { %>
	 <% for(Todo todo : todoList) { %>
    	<p>
    	<%= todo.getTodo() %><br>
    	<% if(todo.getStart() != null) { %>
    		取り掛かる日：<%= todo.getStart() %><br>
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
		<input type ="submit" value="完了">
		</form>
		<form action ="/todo/ChangeServlet" method ="post">
		<input type="hidden" name="id" value="delete" >
		<input type="hidden" name="number" value=<%=todo.getNumber()%>>
		<input type="submit" value="削除">
		</form>	
	<% } %>
	
<% } %>



<form action ="/todo/ChangeServlet" method ="post">
新規作成：<br>
<input type ="text" name="todo" required><br>
締切：<input type ="date" name="dead"><br>
取り掛かる日：<input type ="date" name="start" value=<%=today%>><br>
<textarea name="detail" rows="2" cols="40" placeholder="Todoの詳細を入力してください"></textarea><br>
<input type ="hidden" name ="id" value="newTodo">
<input type ="submit" value="保存">
</form>
</body>
</html>

