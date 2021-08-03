<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>反省</title>
</head>
<body>
<p>反省一覧</p>
<c:forEach var="dayReflection" items="${dayReflectionList}">
	<h2> ${dayReflection.startDate} - ${dayReflection.endDate} のTodo </h2><br>
	<c:set var="notDoneList" value="${dayReflection.notDoneList}"/>
	<c:set var="notDoneListLength" value ="${fn:length(notDoneList)}"/>
	<c:if test ="${notDoneListLength != 0}">
		<h3>締切未達成</h3><br>
		<c:forEach var="notDoneReflection" items="${dayReflection.notDoneList}">			
			<h4>${notDoneReflection.todo}</h4><br>
			<h5>進捗：</h5><br>
			<p>${notDoneReflection.progress}</p><br>
			<h5>なぜ締切内にできなかったか：</h5><br>
			<h5>${notDoneReflection.stuck}</h5><br>
			<h5>改善策：</h5><br>		
			<p>${notDoneReflection.bad}</p><br>
			<h5>その他コメント：</h5><br>		
			<p>${notDoneReflection.comment}</p><br>
		</c:forEach>
	</c:if>
	<c:set var="doneList" value="${dayReflection.doneList}"/>
	<c:set var="doneListLength" value ="${fn:length(doneList)}"/>
	<c:if test ="${doneListLength != 0}">
		<h3>今日達成</h3><br>
	 	<c:forEach var="doneReflection" items="${dayReflection.doneList}">	
			<h4>${doneReflection.todo}</h4><br>
			<h5>良かった点：</h5><br>
			<p>${doneReflection.good}</p>
			<h5>悪かった点：</h5><br>
			<h5>${doneReflection.bad}</h5><br>
			<h5>その他コメント：</h5><br>		
			<p>${doneReflection.comment}</p><br>
		</c:forEach>
	</c:if>
	<c:set var="progressList" value="${dayReflection.progressList}"/>
	<c:set var="progressListLength" value ="${fn:length(progressList)}"/>
	<c:if test ="${progressListLength != 0}">		
	<h3>進行中</h3><br>
		<c:forEach var="progressReflection" items="${dayReflection.progressList}">	
			<c:if test = "${progressReflection.todayDo = true}">	
				<h4>${progressReflection.todo}</h4><br>
				<h5>進捗</h5><br>
				<p>${progressReflection.progress}</p><br>
				<h5>詰まっている点：</h5><br>
				<h5>${progressReflection.stuck}</h5><br>
				<h5>良かった点：</h5><br>		
				<p>${progressReflection.good}</p><br>
				<h5>改善策：</h5><br>		
				<p>${progressReflection.bad}</p><br>
				<h5>その他コメント：</h5><br>		
				<p>${progressReflection.comment}</p><br>
			</c:if>	
		</c:forEach>
	</c:if>
	<c:set var="tommorowList" value="${dayReflection.tommorowList}"/>
	<c:set var="tommmorowListLength" value ="${fn:length(tommorowList)}"/>
	<c:if test ="${tommorowListLength != 0}">		
		<h3>明日</h3><br>
		<c:forEach var="tommorowReflection" items="${dayReflection.tommorowList}">	
			<h4>${tommorowReflection.todo}</h4><br>
			<h5>コメント：</h5><br>		
			<p>${tommorowReflection.comment}</p><br>
		</c:forEach>
	</c:if>	
	<c:forEach var="comment" items="${dayReflection.comment}">		
			<h3>コメント：</h3><br>	
			<p>${comment.comment}</p>
	</c:forEach>
</c:forEach> 
<a href="/todo/ReflectionChange">編集</a>
<a href="/todo/ReflectionDayCheck">新規作成</a>
</body>
</html>



