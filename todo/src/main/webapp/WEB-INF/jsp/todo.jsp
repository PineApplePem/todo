<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.Todo,java.util.List,java.sql.Date"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    			<c:if test ="${!empty todo.detail }">
    				詳細：<c:out value = "${todo.detail}" /><br>
    			</c:if>

    			</p>
				<form action ="/todo/ChangeServlet" method ="post">
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
				<a href ="/todo/Todo?edit=${todo.number}">編集・コメントの作成</a>
				<a href ="/todo/Todo?viewAddition=${todo.number}">コメントを見る</a><br>
				
				
				<c:if test="${viewAddition == todo.number}">
					<c:forEach var="addition" items="${additionList}">
						<br>
						<c:out value ="${addition.type}"/>：
						<c:out value ="${addition.comment}" />
						<fmt:formatDate value="${addition.createTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						
						<a href ="/todo/AdditionDelete?delete=${addition.id}">削除</a><br>
					</c:forEach>
					<a href ="/todo/Todo">コメントを閉じる</a><br>
				</c:if>
				
				
				<c:forEach var="todoNumber" items="${overDeadList}">	
					<c:if test="${todo.number == todoNumber}">
					  <c:set var = "overDead" value ="true"/>
					</c:if>
				</c:forEach>
				
				<c:if test ="${overDead == true }">
					＊締切が超過しています。<br>
					<form action ="/todo/Todo" method="post">
					新しい締め切りを設定してください<br>
					<input type ="date" name="dead" value="${today}"><br>
					なぜ締切を超過したかと、どのようにして新しい締切に間に合わせるかの施策を入力してください。<br>
					<textarea name="comment" placeholder="コメント"></textarea>
					<input type ="hidden" name ="oldNumber" value="${todo.number}"><br>
					<input type ="hidden" name ="oldTodo" value="${todo.todo}">
					<input type ="hidden" name ="oldDead" value="${todo.dead}">
					<input type ="hidden" name ="oldStart" value="${todo.start}">
					<input type ="hidden" name ="oldDetail" value="${todo.detail}">
					<input type ="hidden" name ="oldNumber" value="${todo.number}"><br>
					<input type ="submit" value="保存"><br>
					</form>
					<c:set var = "overDead" value ="false"/>
				</c:if>
			</c:when>
			
			<c:when test ="${edit == todo.number}">
				<form action ="/todo/Todo" method="post">
				<input type ="text" name="todo" value="${todo.todo}" required><br>
				取り掛かる日：<input type ="date" name="start" value="${todo.start}"><br>
				締切：<input type ="date" name="dead" value="${todo.dead}"><br>
				<textarea name="detail" rows="2" cols="40"><c:out value ="${todo.detail}"/></textarea><br>
				<select name ="commentType">
				<option value ="その他">その他</option>
				<option value ="進捗">進捗</option>
				<option value ="反省">反省</option>
				<option value ="気づいたこと">気づいたこと</option>
				</select>
				<textarea name="comment" placeholder="コメント"></textarea>
				<input type ="hidden" name ="oldTodo" value="${todo.todo}">
				<input type ="hidden" name ="oldDead" value="${todo.dead}">
				<input type ="hidden" name ="oldStart" value="${todo.start}">
				<input type ="hidden" name ="oldDetail" value="${todo.detail}">
				<input type ="hidden" name ="oldNumber" value="${todo.number}">
				<input type ="submit" value="保存">
				<a href ="/todo/Todo">編集をやめる</a>
				</form>
			</c:when>
		</c:choose>			
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

