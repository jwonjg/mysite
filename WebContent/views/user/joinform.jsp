<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
		<jsp:include page="/views/include/header.jsp" flush="false"/>
		</div>
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post" action="user">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${ sessionScope.authUser.name }">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${ sessionScope.authUser.email }">
					<input type="button" value="id 중복체크">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test="${ sessionScope.authUser.gender eq 'female' }">
								<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="male">
							</c:when>
							<c:otherwise>
								<label>여</label> <input type="radio" name="gender" value="female">
								<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:otherwise>
						</c:choose>
					</fieldset>
					
					<c:choose>
						<c:when test="${ empty sessionScope.authUser }">
							<input type="hidden" name="a" value="join">
							<fieldset>
								<legend>약관동의</legend>
								<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
								<label>서비스 약관에 동의합니다.</label>
							</fieldset>
							
							<input type="submit" value="가입하기">
						</c:when>
						<c:otherwise>
							<input type="hidden" name="a" value="uinfo">
							<input type="hidden" name="no" value="${ sessionScope.authUser.no }">
							
							<input type="submit" value="수정하기">
						</c:otherwise>
					</c:choose>
					
				</form>
			</div>
		</div>
		<div id="navigation">
			<c:import url="/views/include/navigation.jsp">
				<c:param name="type" value="main"></c:param>
			</c:import>
		</div>
		<div id="footer">
			<c:import url="/views/include/footer.jsp"></c:import>
		</div>
	</div>
</body>
</html>