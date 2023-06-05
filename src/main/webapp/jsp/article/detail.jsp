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
<title>상세 보기</title>
</head>
<body>
	<h1>글 상세 페이지</h1>
	<div>글 번호: <%= (int) article.get("id") %> </div>
	<div>작성일: <%= (LocalDateTime) article.get("regDate") %> </div>
	<div>제목: <%= (String) article.get("title") %></div>
	<div>내용: <%= (String) article.get("body") %></div>
	<a href="doDelete?id=<%= article.get("id")%>">삭제</a>
	<a href="list">목록</a>
</body>
</html>