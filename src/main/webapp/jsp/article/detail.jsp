<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Map<String, Object> article = (Map<String, Object>) request.getAttribute("article");
	int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>게시글 상세보기</title>
</head>
<body>
	<h1><%= article.get("id") %>번 게시물</h1>
	<div>글 번호: <%= (int) article.get("id") %> </div>
	<div>작성일: <%= (LocalDateTime) article.get("regDate") %> </div>
	<div>작성자: <%=(String) article.get("name") %></div>
	<div>제목: <%= (String) article.get("title") %></div>
	<div>내용: <%= (String) article.get("body") %></div>
	<a href="list">목록</a>
	<%
		if((int) article.get("memberId") == loginedMemberId){
	%>
		<a href="modify?id=<%= (int) article.get("id")%>">수정</a>
		<a href="doDelete?id=<%= (int) article.get("id")%>" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
	<%
		}
	%>
</body>
</html>