<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

   input[type=text] {
      padding: 10px; 
      text-align: center; 
      margin: 0px;
   }
   
   table {
    margin-left:auto; 
    margin-right:auto;
}

body {
  text-align: center; 
}
   
</style>
</head>
<body>
	<h3>게시글 상세보기</h3>
<table border=1>
	<thead>
		<tr>
          <td>제목</td>
          <td colspan="7">${vo.noticeTitle }</td>
        </tr>
        <tr>  
          <td>번호</td>
          <td>${vo.noticeId }</td>
          <td>글쓴이</td>
          <td>${vo.noticeWriter }</td>
          <td>날짜</td>
          <td>${vo.noticeDate }</td>
          <td>조회수</td>
          <td>${vo.noticeHit }</td>
		</tr>
	</thead>
	<tbody>
		<tr>
		  <td>내용</td>
		  <td colspan="7">${vo.noticeSubject }</td>	
		</tr>
	</tbody>
</table><br><br>

<table> 
   <thead>
      <tr>
        <td><input type="text" size=10 maxlength=5 placeholder="작성자"></td>
      </tr> 
   </thead>
   <tbody>
      <tr>
        <td><input type="text" size=100 maxlength=30 placeholder="댓글내용"></td>
      </tr> 
   </tbody>
</table>
<button>등록</button>
<br><br>

<table  border="1"> 
   <thead>
      <tr>
        <td>번호</td>
        <td>작성자</td>
        <td>작성일</td>
        <td>내용</td>
      </tr>
   </thead>
   <tbody>
      <c:choose>
       <c:when test="${not empty list }">
           <c:forEach items="${list }" var="r">
           <tr>
                <td>${r.no}</td>
                <td>${r.writer}</td>
                <td>${r.wrdate}</td>
                <td>${r.content}</td>
           </tr>
           </c:forEach>
       </c:when>
       <c:otherwise>
				<td colspan="4" align="center">
					댓글이 존재하지 않습니다
				</td>
	   </c:otherwise>
	   </c:choose>
   </tbody>
</table>
</body>
</html>