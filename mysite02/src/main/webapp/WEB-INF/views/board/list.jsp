<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align:left">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					
					<c:forEach items="${list }" var="board" varStatus="status">
						<tr>
							<%-- ${20*board.depth } --%>
							<td>${status.count}</td>
							<c:choose>
								 <c:when test="${board.depth == 0}">
									 <td style="text-align:left; padding-left:0px"><a href="${pageContext.servletContext.contextPath }/board?g=view&no=${board.no }">${board.title }</a></td>
								 </c:when>
								 <c:otherwise>
									 <td style="text-align:left; padding-left:${20*board.depth }px"><img src='${pageContext.request.contextPath }/assets/images/reply.png' /><a href="${pageContext.servletContext.contextPath }/board?g=view&no=${board.no }">${board.title }</a></td>
							 	</c:otherwise>
							</c:choose>                  
							
							<td>${board.userName }</td>
							<td>${board.hit }</td>
							<td>${board.regDate }</td>
							
							<td><a href="${pageContext.servletContext.contextPath }/board?g=delete&no=${board.no }" class="del">삭제</a></td>
						</tr>
					</c:forEach>
					
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
						<!-- 페이지가없으면 링크 안걸어주면 연하게 표시된다 꼭 5개씩 보여주는 걸로-->
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board?g=writeform" id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>