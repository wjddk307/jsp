<%@page import="co.micol.prj.emp.EmpVO"%>
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
<title>empInsert.jsp</title>
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

사원등록
<%
   EmpVO vo = (EmpVO)request.getAttribute("emp");
%>

	<form name="frm" action="empInsert" method="post" onsubmit="return validateForm()">
		<label for="empid">사원번호</label> 
		<input type="number" name="employeeId" id="empid" value="<%= vo.getEmployeeId()%>"><br>
		<label for="lastname">이름</label> 
		<input type="text" name="lastName" id="lastname" value="<%=vo.getLastName()%>"><br>
		<label>부서번호</label>
		
	<c:forEach items="depts" var="dept">
		<input type="radio" name="departmentId" value="${dept.getDepartmentId() }" 
		<c:if test="dept.getDepartmentId() == vo.getDepartmentId()"> checked="checked" </c:if> >
		            ${dept.getDepartmentName()}
		</c:forEach> 
		<br>		
		<label for="jobid">업무번호</label>
		<select name="jobId">
	<% ArrayList<JobsVO> list = (ArrayList<JobsVO>)request.getAttribute("jobs"); 
		for( JobsVO jobs : list) { %> 
		<option value = "<%=jobs.getJobId()%>" >	<%=jobs.getJobTitle()%>
	
	<% }  %>
		</select>
		<br>
		<label for="email">이메일</label>
		<input type="text" name="email" id="email" value="<%=vo.getEmail()%>"><br>	 
		<label for="hiredate">입사일</label> 
		<input type="date" name="hireDate" id="hiredate" value="<%=vo.getHireDate().substring(0,10)%>"><br> 
		<input type="submit" value="입력">
		<button type="button" onclick="empDelete()">삭제</button>
	</form>
       <script>
          function empDelete(){
	      location.href="empDelete?employeeId=<%=vo.getEmployeeId()%>";
          }
      document.querySelector("[name=departmentId][value='<%=vo.getDepartmentId()%>']").checked = true;
      document.getElementsByName("jobId")[0].value = "<%=vo.getJobId()%>";
      </script>
</body>
</html>