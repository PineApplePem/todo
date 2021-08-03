<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規反省の作成</title>
</head>
<body>
 ${dayReflection.startDate} - ${dayReflection.endDate}の反省
<form action ="/todo/ReflectionChange" method ="post"> 
	<c:set var="notDoneList" value="${dayReflection.notDoneList}"/>
	<c:set var="notDoneListLength" value ="${fn:length(notDoneList)}"/>
	<c:if test ="${notDoneListLength != 0}">
		<h3>締切未達成</h3><br>
		<c:forEach var="notDoneReflection" items="${dayReflection.notDoneList}">			
			<h4>${notDoneReflection.todo}</h4><br>
			<h5>進捗：</h5><br>
			<c:set var="name">progress,${notDoneReflection.id}}</c:set>
			<textarea name="${name}"></textarea>
			<h5>なぜ締切内にできなかったか。今後の改善策：</h5><br>		
			<c:set var="name">comment,${notDoneReflection.id}}</c:set>
			<textarea name="${name}"></textarea>
			新しい締め切り
			<input type = "date" name ="dead">
		</c:forEach>
		<input type="submit" value="一度保存">
	</c:if>
	
	<c:set var="doneList" value="${dayReflection.doneList}"/>
	<c:set var="doneListLength" value ="${fn:length(doneList)}"/>
	<c:if test ="${doneListLength != 0}">
		<h3>今日達成</h3><br>
	 	<c:forEach var="doneReflection" items="${dayReflection.doneList}">	
			<h4>${doneReflection.todo}</h4><br>
			<h5>良かった点、改善点：</h5><br>		
			<p>${doneReflection.comment}</p><br>
			<c:set var="name">comment,${doneReflection.id}}</c:set>
			<textarea name="${name}"></textarea>			
		</c:forEach>
		<input type="submit" value="一度保存">
	</c:if>
	
	<c:set var="progressList" value="${dayReflection.progressList}"/>
	<c:set var="progressListLength" value ="${fn:length(progressList)}"/>
	<c:if test ="${progressListLength != 0}">		
	<h3>進行中</h3><br>
		<c:forEach var="progressReflection" items="${dayReflection.progressList}">	
			<c:if test = "${progressReflection.todayDo = true}">	
				<h4>${progressReflection.todo}</h4><br>
				<input type= "checkbox" name="todayDo" value="false"> 今日はやっていない
				<h5>進捗</h5><br>
				<c:set var="name">progress,${progressReflection.id}}</c:set>
				<textarea name="${name}"></textarea>
				<h5>詰まっている点、今後の改善点：</h5><br>		
				<p>${progressReflection.comment}</p><br>
				<c:set var="name">comment,${progressReflection.id}}</c:set>
				<textarea name="${name}"></textarea>
			</c:if>	
		</c:forEach>
		<input type="submit" value="一度保存">
	</c:if>
	
	<c:set var="tommorowList" value="${dayReflection.tommorowList}"/>
	<c:set var="tommmorowListLength" value ="${fn:length(tommorowList)}"/>
	<c:if test ="${tommorowListLength != 0}">		
		<h3>明日</h3><br>
		<c:forEach var="tommorowReflection" items="${dayReflection.tommorowList}">	
			<h4>${tommorowReflection.todo}</h4><br>
			<h5>コメント：</h5><br>		
				<c:set var="name">comment,${tommorowReflection.id}}</c:set>
				<textarea name="${name}"></textarea>
		</c:forEach>
		<input type="submit" value="一度保存">
	</c:if>	
	
	<c:forEach var="comment" items="${dayReflection.comment}">		
		<h3>コメント：</h3><br>	
			<c:set var="name">comment,${comment.id}}</c:set>
			<textarea name="${name}"></textarea>
	</c:forEach>

<input type="hidden" value="${dayReflection}">
<input type="submit" value="一度保存">
<input type="submit" value="保存してトップページへ">
</form>
</body>
</html>