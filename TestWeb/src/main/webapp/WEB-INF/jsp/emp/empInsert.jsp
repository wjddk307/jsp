<%@page import="co.micol.prj.dept.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.micol.prj.emp.JobsVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl</title>
<script>
function validateForm() {
	if(frm.employeeId.value == "" ) {
		alert("사번입력");
		frm.employeeId.focus();
		return false;
	}
	if(frm.lastName.value == "" ) {
		alert("이름입력");
		frm.lastName.focus();
		return false;
	}
	if(frm.hireDate.value == "" ) {
		alert("입사일입력");
		frm.hireDate.focus();
		return false;
	}
	if(frm.jobId.value == "" ) {
		alert("직무입력");
		frm.jobId.focus();
		return false;
	}
	if(frm.email.value == "" ) {
		alert("이메일입력");
		frm.email.focus();
		return false;
	}
	var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if(regExp.test(frm.email.vlaue) == false) {
		alert("이메일형식");
		frm.email.focus();
		return false;
	}
	return true;
}
</script>
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
<jsp:include page="/WEB-INF/jsp/header.jsp" />

	<form name="frm" action="empInsert" method="post" onsubmit="return validateForm()">
		<label for="empid">사원번호</label> 
		<input type="number" name="employeeId" id="empid"><br>
		<label for="lastname">이름</label> 
		<input type="text" name="lastName" id="lastname"><br>
		<label>부서번호</label>
		
	<% ArrayList<DeptVO> depts = (ArrayList<DeptVO>)request.getAttribute("depts"); 
		for( DeptVO dept : depts) { 
		%> 
		<input type="radio" name="departmentId" value="<%=dept.getDepartmentId()%>">
		            <%=dept.getDepartmentName()%>
	<% }  %>
		<br>		
		<label for="jobid">업무번호</label>
		<select name="jobId">
	<c:forEach items="jobs" var="job">
		<option value = "${job.getJobId()}">${job.getJobTitle()}
	</c:forEach>	      
	
		</select>
		<br>
		<label for="email">이메일</label>
		<input type="text" name="email" id="email"><br>	 
		<label for="hiredate">입사일</label> 
		<input type="date" name="hireDate" id="hiredate"><br> 
		<input type="submit" value="입력">
	</form>

</body>
</html>