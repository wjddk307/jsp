<%@page import="co.micol.prj.dept.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="DeptInsert">부서등록</a>
<table>
    <tr><td>부서번호</td><td>부서명</td></tr>
<% 
ArrayList<DeptVO> list = (ArrayList<DeptVO>) request.getAttribute("list");
for(DeptVO dept : list){
   %>
   <tr>
   <td><%=dept.getDepartmentId() %></td>
   <td><a href="DeptUpdate?departmentId=<%=dept.getDepartmentId() %>"> <%=dept.getDepartmentName() %></a></td>
   </tr>
<% } %>

</table>
</body>
</html>