<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<title>Tour</title>
<link rel="stylesheet" href="${root}/css/user/style_tour.css">
</head>
<body>
	<div class="wrap">
		<header class="header">
			<div class="inner">
				<c:if test="${sessionScope.User ne 1}">
					<div class="navbar_right">
						<ul>
							<li><a href="Login">Login</a></li>
							<li><a href="${root}/project/join">Sign up</a></li>
						</ul>
					</div>
				</c:if>
				<c:if test="${sessionScope.User eq 1}">
					<div class="navbar_right">
						<ul>
							<li>${sessionScope.id}님환영합니다.</li>
							<li><a href="${root}/project/Logout">Log Out</a></li>
						</ul>
					</div>
				</c:if>
				<h1>
					<a href="${root}/project/main"><img
						src="${root}/image/luggage.png" alt=""></a>
				</h1>
			</div>
		</header>
		<section class="middle_bar">
			<div class="inner">
				<ul>
					<li><a href="board">Board</a></li>
					<li><a href="board">여행지소개</a></li>
					<li><a href="board">여행후기</a></li>
					<li><a href="board">Board</a></li>
				</ul>
			</div>
		</section>
		<section class="content">
			<div class="inner">
			<div class="search">
				<form>
					<select name="f">
						<option ${(param.f == "title")?"selected":""} value="title">지역명</option>
						<option ${(param.f == "writer_id")?"selected":""}
							value="writer_id">글쓴이</option>
					</select> <label>검색어</label> <input type="text" name="q" value="${param.q}" />
					<input type="submit" value="Search" />
				</form>
			</div>
				<ul class="contentbox">
					<li class="contentlist">
						<div class="smallcontent">
							<span class=thum> <a class="imgover" href="#"><img src="${root}/image/cf2b6d07548849a1bfaf80010c0d34a0.jpg"
								alt=""></a></span>
							<div class="smallname">부산</div>
							<div class="smalldescrp"> 장소 설명</div>
						</div>
					</li>
					<li class="contentlist">
						<div class="smallcontent">
							<span class=thum> <a class="imgover" href="#"><img src="${root}/image/cf2b6d07548849a1bfaf80010c0d34a0.jpg"
								alt=""></a></span>
							<div class="smallname">서울</div>
							<div class="smalldescrp"> 장소 설명</div>
						</div>
					</li>
					<li class="contentlist">
						<div class="smallcontent">
							<span class=thum> <a class="imgover" href="#"><img src="${root}/image/cf2b6d07548849a1bfaf80010c0d34a0.jpg"
								alt=""></a></span>
							<div class="smallname">경주</div>
							<div class="smalldescrp">장소 설명</div>
						</div>
					</li>
					<li class="contentlist">
						<div class="smallcontent">
							<span class=thum> <a class="imgover" href="#"><img src="${root}/image/cf2b6d07548849a1bfaf80010c0d34a0.jpg"
								alt=""></a></span>
							<div class="smallname">대구</div>
							<div class="smalldescrp"> 장소 설명</div>
						</div>
					</li>
				</ul>
			</div>
		</section>

	</div>

</body>
</html>