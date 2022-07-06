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
<table border>
<thead>

	
	<th><td>id</td><td>title</td><td>content</td><td>writer</td><td>rdt</td><td>hit</td></th>
</thead>

<%
ArrayList<BoardVO> list = (ArrayList<BoardVO>) request.getAttribute("list");
for(BoardVO board : list) {
	%>
	<tr>
	<td><%=board.getId() %></td>
	<td><%=board.getTitle() %></td>
	<td><%=board.getContent() %></td>
	<td><%=board.getWriter() %></td>
	<td><%=board.getRdt() %></td>
	<td><%=board.getHit() %></td>
	</tr>
<%
}
%>


</table>

</body>
</html>