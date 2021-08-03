<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.Todo,java.util.List,java.sql.Date"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <html>
 <body>
<c:set var="todoList" value="${todoList}"/>
<c:set var="todoListLength" value ="${fn:length(todoList)}"/>
<c:choose>
<c:when test ="${todoListLength != 0}">
	<c:forEach var="todo" items="${todoList}">	
		<c:choose>			
			<c:when test = "${edit != todo.number}">
    			<p>
    			<c:out value ="${todo.todo}" /><br>
    			<c:if test ="${todo.start != null}">
    				取り掛かる日：<c:out value = "${todo.start}" /><br>
    			</c:if>
    			<c:if test ="${todo.dead != null}">
    				締切：<c:out value = "${todo.dead}" /><br>
    			</c:if>
    			詳細：<c:out value = "${todo.detail}" /><br>

    			</p>
				<form action ="/todo/ChangeServlet" method ="post" style="display:inline;">
				<input type="hidden" name="id" value="done">
				<input type="hidden" name="number" value="${todo.number}">
				<input type ="hidden" name="done" value="${todo.done}">
				<input type ="submit" value="完了">
				</form>
				<form action ="/todo/ChangeServlet" method ="post">
				<input type="hidden" name="id" value="delete" >
				<input type="hidden" name="number" value="${todo.number}">
				<input type="submit" value="削除">
				</form>	
				<a href ="/todo/Todo?edit=${todo.number}">編集</a>
			</c:when>
			<c:when test ="${edit == todo.number}">
				<form action ="/todo/Todo" method="post">
				<input type ="text" name="todo" value="${todo.todo}" required><br>
				取り掛かる日：<input type ="date" name="start" value="${todo.start}"><br>
				締切：<input type ="date" name="dead" value="${todo.dead}"><br>
				<textarea name="detail" rows="2" cols="40"><c:out value ="${todo.detail}"/></textarea><br>
				<select name ="commentTag">
				<option value ="進捗">進捗</option>
				<option value ="その他">その他</option>
				</select>
				<textarea name="comment" placeholder="コメント"></textarea>
				<input type ="hidden" name ="oldTodo" value="${todo.todo}">
				<input type ="hidden" name ="oldDead" value="${todo.dead}">
				<input type ="hidden" name ="oldStart" value="${todo.start}">
				<input type ="hidden" name ="oldDetail" value="${todo.detail}">
				<input type ="hidden" name ="oldNumber" value="${todo.number}">
				<input type ="submit" value="保存">
				</form>
			</c:when>
		</c:choose>	
		<!--  deadが今日より前の時新しいDeadとそこまでにやるための施策をコメントさせるフォームを表示 -->
	</c:forEach>	
</c:when>	
<c:otherwise>
Todoがありません。
</c:otherwise>
</c:choose>

<form action ="/todo/ChangeServlet" method ="post">
新規作成：<br>
<input type ="text" name="todo" required><br>
取り掛かる日：<input type ="date" name="start" value="${today}"><br>
締切：<input type ="date" name="dead"><br>
<textarea name="detail" rows="2" cols="40" placeholder="Todoの詳細を入力してください"></textarea><br>
<input type ="hidden" name ="id" value="newTodo">
<input type ="submit" value="保存">
</form>
</body>
</html>

