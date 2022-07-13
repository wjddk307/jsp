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
	<div align="center">
		<table border="1">
			<thead>
				<th>아이디</th>
				<th>이름</th>
				<th>권한</th>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="m">
					<tr>
						<td align="center">${m.memberId }</td>
						<td align="center">${m.memberName }</td>
						<td align="center">${m.memberAuthor }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>