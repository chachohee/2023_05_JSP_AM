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
	<h1>게시물 리스트 v1</h1>
	<ul>
		<li><%= articleListMap.get(0).get("title") %>, <%= articleListMap.get(0).get("body") %></li>
		<li><%= articleListMap.get(1).get("title") %>, <%= articleListMap.get(0).get("body") %></li>
		<li><%= articleListMap.get(2).get("title") %>, <%= articleListMap.get(0).get("body") %></li>
	</ul>

	<h1>게시물 리스트 v2</h1>
	<ul>
	<% 
		for(int i=0; i<3; i++){		
	%>
		<li><%= articleListMap.get(i).get("title") %>, <%= articleListMap.get(i).get("body") %></li>
	<% 
		} 
	%>
	</ul>
	
	<h1>게시물 리스트 v3</h1>
	<ul>
	<% 
		for(int i=0; i<3; i++){	
			Map<String, Object> articleMap = articleListMap.get(i);
	%>
		<li><%= articleMap.get("title") %>, <%= articleMap.get("body") %></li>
	<% 
		} 
	%>
	</ul>
	
	<h1>게시물 리스트 v4</h1>
	<ul>
	<% 
		for(int i=0; i<articleListMap.size(); i++){	
			Map<String, Object> articleMap = articleListMap.get(i);
	%>
		<li><%= articleMap.get("title") %>, <%= articleMap.get("body") %></li>
	<% 
		} 
	%>
	</ul>
	
	<h1>게시물 리스트 v5</h1>
	<ul>
	<% 
		for(Map<String,Object> articleMap : articleListMap){
	%>
		<li><%= articleMap.get("title") %>, <%= articleMap.get("body") %></li>
	<% 
		} 
	%>
	</ul>
</body>
</html>