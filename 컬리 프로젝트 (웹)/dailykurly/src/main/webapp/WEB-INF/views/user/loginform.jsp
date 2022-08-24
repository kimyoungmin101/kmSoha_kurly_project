<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<form action="${pageContext.request.contextPath}/user/login.do" method="post" name="loginform" id="loginform" class="form" onsubmit="return sendit()">      		
		<div class="card card-custom">
			<div class="card-header">
				<h1>로그인</h1>
			</div>
		</div>
			<c:choose>
				<c:when test="${ empty param.url }">
					<input type="hidden" name="url" value="${pageContext.request.contextPath}/"/>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="url" value="${param.url }"/>
				</c:otherwise>
			</c:choose>
			<div class="card-body">
				<div class="form-floating ">
					<input type="text" class="form-control" name="user_id" id="user_id" placeholder="아이디 입력..." required>
					<label for="user_id">아이디</label>
				</div>
				<button class="login" type="submit">로그인</button>
			</div>
	</form>

</body>
</html>