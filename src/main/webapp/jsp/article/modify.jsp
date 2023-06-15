<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Map<String, Object> article = (Map<String, Object>) request.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<%@ include file="../common/topBar.jsp" %>
	<h1>글 수정 페이지</h1>
	<div>글 번호: <%= (int) article.get("id") %> </div>
	<div>작성일: <%= (LocalDateTime) article.get("regDate") %> </div>
	<form action="doModify?id=<%= (int) article.get("id") %>" method="post">
		<div>
			제목: <textarea name="title"><%= (String) article.get("title") %></textarea>
		</div>
		<div>
			내용: <textarea name="body"><%= (String) article.get("body") %></textarea>
		</div>
		<input type="submit" value="수정"><input type="reset" value="다시 입력">
	</form>
	<a href="doDelete?id=<%= article.get("id")%>">삭제</a>
	<a href="detail?id=<%= (int) article.get("id")%>">뒤로가기</a>
</body>
</html>