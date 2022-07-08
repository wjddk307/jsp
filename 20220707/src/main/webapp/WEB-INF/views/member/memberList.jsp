<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${list }" var="m">
		<tr>
			<td align="center">${m.memberId }</td>
			<td align="center">${m.memberName }</td>
			<td align="center">${m.memberAuthor }</td>
		</tr>
</c:forEach>
</body>
</html>