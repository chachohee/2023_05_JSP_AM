<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Map<String, Object>> articleListMap = (List<Map<String, Object>>) request.getAttribute("articleListMap");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
	<h1>게시물 리스트</h1>
	<table border="1">
		<colgroup>
			<col width="50" />
			<col />
			<col width="200"/>
		</colgroup>
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>등록일</th>
		</tr>
		<%
			for(Map<String, Object> articleMap: articleListMap){
		%>
		<tr>
			<td><%= articleMap.get("id") %></td>
			<td><a href="detail?id=<%= articleMap.get("id")%>"><%= articleMap.get("title") %></a></td>
			<td><%= articleMap.get("regDate") %></td>
		</tr>
		<%
			}
		%>
	</table>
	<div><a href="../home/main">메인</a></div>
</body>
</html>