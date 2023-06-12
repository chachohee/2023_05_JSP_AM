<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form action="doWrite" method="post">
		<div>
			제목: <input type="text" name="title" placeholder="제목을 입력해주세요.">
		</div>
		<div>
			내용: <textarea name="body" placeholder="내용을 입력해주세요." ></textarea>
		</div>
		<input type="submit" value="등록"><input type="reset" value="다시 입력">
	</form>
	<div>
		<a href="list">목록</a>
	</div>
</body>
</html>