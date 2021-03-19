<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${root}/css/user/style_board.css">
<title>Insert title here</title>
</head>
<body>
	<div class="wrap">
		<header class="header">
			<div class="inner">
				<c:if test="${sessionScope.User ne 1}">
					<div class="navbar_right">
						<ul>
							<li><a href="Login">Login</a></li>
							<li><a href="${root}/project/Join">Sign up</a></li>
						</ul>
					</div>
				</c:if>
				<c:if test="${sessionScope.User eq 1}">
					<div class="navbar_right">
						<ul>
							<li>${sessionScope.id}환영</li>
							<li><a href="${root}/project/Logout">Log Out</a></li>
						</ul>
					</div>
				</c:if>
				<h1>
					<a href="${root}/project/main"><img src="${root}/image/luggage.png" alt=""></a>
				</h1>
			</div>
		</header>
		<section class="middle_bar">
			<div class="inner">
				<ul>
					<li><a href="board">Board</a></li>
					<li><a href="#">여행지소개</a></li>
					<li><a href="#">여행후기</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
			</div>
		</section>

		<!-- list -->
		<div class="inner">
			<div class="d_table">
				<table class="texttable">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
						<td>조회수</td>
					</tr>
					<c:forEach var="n" items="${list}" varStatus="t">
						<tr>
							<td>${n.id}</td>
							<td><a
								href="detail?id=${n.id}&f=${param.f}&q=${param.q}&p=${param.p}">${n.title}</a></td>
							<td>${n.writerID}</td>
							<td><fmt:formatDate value="${n.regdate}"
									pattern="yyyy.MM.dd." /></td>
							<td><fmt:formatNumber value="${n.hit}" type="number"
									pattern="###,###" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!-- 삭제 -->
			<!-- 글쓰기 버튼 -->
			<div class="writing">
				<input type="button" onclick="location.href = 'Reg'" value="글쓰기" />
			</div>
			<!-- 변수 선언 -->
			<c:set var="page" value="${empty param?1:param.p}"></c:set>
			<!-- param == 파라미터 -->
			<c:set var="startNum" value="${page-(page-1)%5}"></c:set>
			<c:set var="lastNum"
				value="${fn:substringBefore(Math.ceil(count/10), '.')}"></c:set>
			<!-- Math.ceil로 올림 substring으로 소수점 짜르기 -->


			<!-- 현재페이지 -->
			<%--    <div>
         <h3>현재 페이지</h3>
         <div><span>${(empty param.p)?1:param.p}</span>/${lastNum}pages</div>   
   </div> --%>

			<!-- 페이지 이동 -->

			<!-- Prev 버튼 -->
			<div class="button">
				<div class="aaa">
					<ul>
						<li><c:if test="${startNum > 1}">
								<a href="?p=${startNum-1}&f=${param.f}&q=${param.q}">Prev</a>
								<!-- p:페이지 f:필드(타이틀) q:query(검색내용) -->
							</c:if> <c:if test="${startNum <= 1}">
								<a href="#" onclick="alert('첫번째 페이지 입니다.');">Prev</a>
							</c:if></li>
						<!-- 페이징 -->
						<li>
							<ul>
								<c:forEach var="i" begin="0" end="4">
									<!-- 현재 보고있는 페이지 색깔변경 -->
									<c:if test="${param.p == (startNum+i)}">
										<c:set var="style" value="font-weight:bold; color:red;" />
									</c:if>
									<c:if test="${param.p != (startNum+i)}">
										<c:set var="style" value="" />
									</c:if>

									<c:if test="${(startNum+i)<=lastNum}">
										<li><a style="${style}"
											href="?p=${startNum+i}&f=${param.f}&q=${param.q}">${startNum+i}</a></li>
									</c:if>

								</c:forEach>
							</ul>
						</li>
						<li>
							<!-- Next 버튼 --> <c:if test="${startNum+5 < lastNum}">
								<a href="?p=${startNum+5}&f=${param.f}&q=${param.q}">Next</a>
							</c:if> <c:if test="${startNum+5 >= lastNum}">
								<a href="#" onclick="alert('마지막 페이지 입니다.');">Next</a>
							</c:if>
						</li>
					</ul>
				</div>
			</div>
			<!-- 검색 -->
			<div class="search">
				<form>
					<select name="f">
						<option ${(param.f == "title")?"selected":""} value="title">제목</option>
						<option ${(param.f == "writer_id")?"selected":""}
							value="writer_id">글쓴이</option>
					</select> <label>검색어</label> <input type="text" name="q" value="${param.q}" />
					<input type="submit" value="Search" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>