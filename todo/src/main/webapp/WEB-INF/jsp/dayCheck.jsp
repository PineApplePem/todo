<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規反省の作成</title>
</head>
<body>
<form action ="/todo/ReflectionDayCheck" method ="post">
始まりの日：<input type="date" name="startDate" value="${startDate}" required><br>
終わりの日：<input type="date" name="endDate" value="${endDate}" required><br>
<input type="submit" value="作成">
</form>	
${errorMsg}
</body>
</html>