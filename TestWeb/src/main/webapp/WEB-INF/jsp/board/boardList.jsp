<%@page import="co.micol.prj.board.BoardVO"%>
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
<table>
    <tr><td>id</td><td>title</td><td>content</td><td>writer</td><td>rdt</td><td>hit</td></tr>
    <%
    ArrayList<BoardVO>list = (ArrayList<BoardVO>) request.getAttribute("list");
    for(BoardVO board : list) {
    	%>
    	<tr>
    	<td><%=board.getId() %></td>
    	<td><a href="BoardUpdate? </a></td>
    	</tr>
<% 
    }
%>
</table>
</body>
</html>