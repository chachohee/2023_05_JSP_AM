<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
<script>
	function joinFormSubmit(form) {
		form.name.value = form.name.value.trim();
		form.loginId.value = form.loginId.value.trim();
		form.loginPw.value = form.loginPw.value.trim();
		form.checkLoginPw.value = form.checkLoginPw.value.trim();

		if (form.name.value.length == 0) {
			alert('이름은 필수입니다.');
			form.name.focus();
			return;
		}
		if (form.loginId.value.length == 0) {
			alert('아이디는 필수입니다.');
			form.loginId.focus();
			return;
		}
		if (form.loginPw.value.length == 0) {
			alert('비밀번호는 필수입니다.');
			form.loginPw.focus();
			return;
		}
		if (form.checkLoginPw.value.length == 0) {
			alert('비밀번호 확인을 입력해주세요.');
			form.checkLoginPw.focus();
			return;
		}
		if (form.loginPw.value != form.checkLoginPw.value) {
			alert('비밀번호를 확인해주세요');
			form.loginPw.value = null;
			form.checkLoginPw.value = null;
			form.loginPw.focus();
			return;
		}
		form.submit();
	}
</script>
</head>
<body>
	<h1>회원가입</h1>
	<form action="doJoin" method="post"
		onsubmit="joinFormSubmit(this); return false;">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" placeholder="User Name"/></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="loginId" placeholder="ID"/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="loginPw"
					placeholder="Password" /></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="checkLoginPw"
					placeholder="Confirm Password" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="가입하기" /> <input type="reset"
					value="다시입력" /></td>
			</tr>
		</table>
	</form>
	<a href="../home/main">뒤로가기</a>
</body>
</html>