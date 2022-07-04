<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl</title>
<style>
label {
width:20%;
display: inline-block;
text-align: center;
background-color:lemonchiffon;
}
</style>
</head>
<body>
	<form action="empList.jsp" method="post">
		<label for="empid">사원번호</label> 
		<input type="number" name="empid" id="empid"><br>
		<label for="lastname">이름</label> 
		<input type="text" name="lastname" id="lastname"><br>		
		<label for="jobid">업무번호</label> 
		<input type="text" name="jobid" id="jobid"><br>
		<label for="email">이메일</label>
		<input type="text" name="email" id="email"><br> 
		<label for="hiredate">입사일</label> 
		<input type="date" name="hiredate" id="hiredate"><br> 
		<input type="submit" value="입력">
	</form>

</body>
</html>