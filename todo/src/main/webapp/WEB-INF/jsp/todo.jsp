<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.Todo,java.util.List,java.sql.Date"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <link rel="stylesheet" href="/todo/css/todo.css">
 <html>
 <head>
<meta charset="UTF-8">
<title>Todoリスト</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
</head>
  <body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class = main-contents>
 <div class = todo-area>
<c:set var="todoList" value="${todoList}"/>
<c:set var="todoListLength" value ="${fn:length(todoList)}"/>
<c:choose>
<c:when test ="${todoListLength != 0}">
	<c:forEach var="todo" items="${todoList}">	
		<c:choose>			
			<c:when test = "${edit != todo.number}">
			<div class = white>
				<form action ="/todo/ChangeServlet" method ="post" style="display: inline">
				<input type="hidden" name="id" value="done">
				<input type="hidden" name="number" value="${todo.number}">
				<input type ="hidden" name="done" value="${todo.done}">
				<input type ="submit" value="完了" class="done-btn">
				</form>
				
    			<p class = todo style ="display: inline">
    			<c:out value ="${todo.todo}" /><br>
    			</p>
    			<p class = date>
    			<c:if test ="${todo.start != null}">
    				<c:out value = "${todo.start}" />
    				-
    			</c:if>
    			<c:if test ="${todo.dead != null}">
    				<c:out value = "${todo.dead}" /><br>
    			</c:if>
    			</p>
    			<p class = detail>
    			<c:if test ="${!empty todo.detail }">
    				詳細：<c:out value = "${todo.detail}" /><br>
    			</c:if>	
    			</p>		

				<form action ="/todo/ChangeServlet" method ="post" style="display: inline">
				<input type="hidden" name="id" value="delete" >
				<input type="hidden" name="number" value="${todo.number}">
				<input type="submit" value="削除" class="delete-btn">
				</form>	
				<a href ="/todo/Todo?edit=${todo.number}"><img src="/todo/css/img/edit.png" alt="編集"class="edit-btn"></a>
				<a href ="/todo/Todo?viewAddition=${todo.number}" ><img src="/todo/css/img/comment.png" class="comment-btn" alt="コメントを表示"></a><br>
			
			

				<c:forEach var="todoNumber" items="${overDeadList}">	
					<c:if test="${todo.number == todoNumber}">
					  <c:set var = "overDead" value ="true"/>
					</c:if>
				</c:forEach>
				
			
				<c:if test ="${overDead == true }">
				<div class = caution>
					＊締切が超過しています。
					<form action ="/todo/Todo" method="post">
					新しい締め切りを設定してください<br>
					<input type ="date" name="dead" value="${today}"><br>
					なぜ締切を超過したかと、どのようにして新しい締切に間に合わせるかの施策を入力してください。<br>
					<textarea name="comment" placeholder="コメント" class="normal-textarea"></textarea>
					<input type ="hidden" name ="oldNumber" value="${todo.number}"><br>
					<input type ="hidden" name ="oldTodo" value="${todo.todo}">
					<input type ="hidden" name ="oldDead" value="${todo.dead}">
					<input type ="hidden" name ="oldStart" value="${todo.start}">
					<input type ="hidden" name ="oldDetail" value="${todo.detail}">
					<input type ="hidden" name ="oldNumber" value="${todo.number}"><br>
					<input type ="submit" value="保存" class="normal-btn"><br>
					</form>
					<c:set var = "overDead" value ="false"/>
				</div>	
				</c:if>
			</div>
			</c:when>
			
			<c:when test ="${edit == todo.number}">
			<div class = white>
				<form action ="/todo/Todo" method="post">
				<input type ="text" name="todo" value="${todo.todo}" required><br>
				取り掛かる日：<input type ="date" name="start" value="${todo.start}"><br>
				締切：<input type ="date" name="dead" value="${todo.dead}"><br>
				<textarea name="detail" class="normal-textarea"><c:out value ="${todo.detail}"/></textarea><br>
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
				<input type ="submit" value="保存" class="normal-btn">
				<a href ="/todo/Todo">編集をやめる</a>
				</form>
			</div>
			</c:when>
		</c:choose>			
	</c:forEach>	
</c:when>	
<c:otherwise>
Todoがありません。
</c:otherwise>
</c:choose>

<div class = white>
<form action ="/todo/ChangeServlet" method ="post">
新規作成：<br>
<input type ="text" name="todo" required><br>
取り掛かる日：<input type ="date" name="start" value="${today}"><br>
締切：<input type ="date" name="dead"><br>
<textarea name="detail" placeholder="Todoの詳細を入力してください" class="normal-textarea"></textarea><br>
<input type ="hidden" name ="id" value="newTodo">
<input type ="submit" value="保存" class = "normal-btn">
</form>
</div>


<c:choose>
	<c:when test = "${move == null}">
		<p><a href ="/todo/Todo?move=done">完了済みのTodoを表示</a></p>
	</c:when>
	<c:otherwise>
		<p><a href ="/todo/Todo">完了済みのTodoを閉じる</a></p>

		 <c:forEach var="todo" items="${doneTodoList}">
		 <div class = white>
		 	<form action ="/todo/ChangeServlet" method ="post" style="display:inline">
			<input type="hidden" name="id" value="done">
			<input type="hidden" name="number" value="${todo.number}">
			<input type ="hidden" name="done" value="${todo.done}">
			<input type ="submit" value="未完了" class="done-btn">
			</form>
    		<p class = todo>
    		<c:out value="${todo.todo}"/> <br>
    		</p>
    		<p class = date>
    		<c:if test ="${todo.start != null}" >
    			${todo.start}-
    		</c:if>
    		<c:if test="${todo.dead != null}">
    			締切：${todo.dead}<br>
    		</c:if>
    		</p>
    		<p class = detail>
    		<c:if test ="{! empty todo.detail}">	   
    			詳細：<c:out value="${todo.detail}"/>
    		</c:if>
    		</p>  

			<form action ="/todo/ChangeServlet" method ="post" style="display:inline">
			<input type="hidden" name="id" value="delete" >
			<input type="hidden" name="number" value="${todo.number}">
			<input type="submit" value="削除" class="delete-btn">
			</form>
			<a href ="/todo/Todo?viewAddition=${todo.number}&move=done"><img src ="/todo/css/img/comment.png" alt="コメントを表示" class="comment-btn"></a>
		</div>	
		 </c:forEach>		
	</c:otherwise>
</c:choose>

</div>



<c:forEach var="todo" items="${todoList}">
		<c:if test="${viewAddition == todo.number}">
		<div class = comment-area>
		<h3><c:out value="${todo.todo}"/></h3>
			<c:forEach var="addition" items="${additionList}">
			<br>			
			<p class = comment>
				<c:out value ="${addition.type}"/>：
				<c:out value ="${addition.comment}" />
				<fmt:formatDate value="${addition.createTime}" pattern="yyyy/MM/dd HH:mm:ss" />
				<a href ="/todo/AdditionDelete?delete=${addition.id}"><img src ="/todo/css/img/delete.png" alt="削除" class="comment-delete"></a><br>
			</p>
			</c:forEach>
				<div class = white>
				<p class = new-comment>新しいコメント：</p>
				<form action ="/todo/Todo" method="post" class="new-comment">
				<select name ="commentType">
				<option value ="その他">その他</option>
				<option value ="進捗">進捗</option>
				<option value ="反省">反省</option>
				<option value ="気づいたこと">気づいたこと</option>
				</select>
				<textarea name="comment" placeholder="コメント" class="normal-textarea"></textarea>
				<input type ="hidden" name ="onlyComment" value="onlyComment">
				<input type ="hidden" name ="oldNumber" value="${todo.number}">
				<input type ="submit" value="保存" class="normal-btn"><br>
				</form>
				<a href ="/todo/Todo">コメントを閉じる</a><br>
				 </div>
		</div>		 
		</c:if>	
</c:forEach>				

<c:forEach var="todo" items="${doneTodoList}">	
		<c:if test="${viewAddition == todo.number}">		
		<div class = comment-area>	
		<h3><c:out value="${todo.todo}"/></h3>
			<c:forEach var="addition" items="${additionList}">
			<br>
				<p class = comment>
				<c:out value ="${addition.type}"/>：
				<c:out value ="${addition.comment}" />
				<fmt:formatDate value="${addition.createTime}" pattern="yyyy/MM/dd HH:mm:ss" />			
				<a href ="/todo/AdditionDelete?delete=${addition.id}"><img src ="/todo/css/img/delete.png" alt="削除" class="comment-delete"></a><br>
				</p>
			</c:forEach>
				新しいコメント：<br>
				<form action ="/todo/Todo" method="post">
				<select name ="commentType">
				<option value ="その他">その他</option>
				<option value ="進捗">進捗</option>
				<option value ="反省">反省</option>
				<option value ="気づいたこと">気づいたこと</option>
				</select>
				<textarea name="comment" placeholder="コメント" class="normal-textarea" ></textarea>
				<input type ="hidden" name ="onlyComment" value="onlyComment">
				<input type ="hidden" name ="oldNumber" value="${todo.number}">
				<input type ="submit" value="保存" class ="normal-btn"><br>
				</form>
				<a href ="/todo/Todo">コメントを閉じる</a><br>
		</div>		
		</c:if>	
</c:forEach>
</div>
</body>
</html>

