<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.douzone.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	UserVo userVo = (UserVo)session.getAttribute("authUser");
%>
		<div id="header">
			<h1>MySite</h1>
			<ul>
			<%-- <%
				if(null == userVo){
			%> --%>
				<c:choose>
					<c:when test="${empty authUser }">
					
					<li><a href="${pageContext.request.contextPath }/user?a=loginform">로그인</a><li>
					<li><a href="${pageContext.request.contextPath }/user?a=joinform">회원가입</a><li>
					
					
				<%-- <li><a href="<%=request.getContextPath() %>/user?a=loginform">로그인</a><li>
					 <li><a href="<%=request.getContextPath() %>/user?a=joinform">회원가입</a><li> --%>
				
					</c:when>
				<%-- <%
				} else {
				%> --%>
					<c:otherwise>
					
					<li><a href="${pageContext.request.contextPath }/user?a=updateform">회원정보수정</a><li>
					<li><a href="${pageContext.request.contextPath }/user?a=logout">로그아웃</a><li>
					<li>${authUser.name }님 안녕하세요 ^^;</li>
			   <%-- <li><a href="<%=request.getContextPath() %>/user?a=updateform">회원정보수정</a><li>
					<li><a href="<%=request.getContextPath() %>/user?a=logout">로그아웃</a><li>
					<li>${authUser.name }님 안녕하세요 ^^;</li> --%>
					
					</c:otherwise>
				<%-- <%
				}
				%> --%>
				</c:choose>
			</ul>
		</div>	