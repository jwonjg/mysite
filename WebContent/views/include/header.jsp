<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<h1>MySite</h1>
	<ul>
		<c:choose>
			<c:when test="${ empty sessionScope.authUser }">
				<li><a href="user?a=loginform">로그인</a><li>
				<li><a href="user?a=joinform">회원가입</a><li>
			</c:when>
			<c:otherwise>
				<li><a href="user?a=logout">로그아웃</a><li>
				<li><a href="user?a=uinfoform">회원정보수정</a><li>
				<li>${ sessionScope.authUser.name }님 안녕하세요 ^^;</li>
			</c:otherwise>
		</c:choose>
	</ul>