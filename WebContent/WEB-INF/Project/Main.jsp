<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/9eb162ac0d.js"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<title>Main</title>
<link rel="stylesheet" href="${root}/css/user/style_main.css">
<style>
</style>
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
					<li><a href="tour">여행지소개</a></li>
					<li><a href="#">여행후기</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
			</div>
		</section>
		<section class="mainslider">
			<div class="slider">
				<div>
					<img src="${root}/image/CN-Tower-Stock.jpg" alt="">
				</div>
				<div>
					<img src="${root}/image/victoria_inner_harbour_flowers.jpg" alt="">
				</div>
			</div>
		</section>
		<button type="button" class="sliderbutton">
	   <a href="#section2"> <i class="fas fa-arrow-circle-down"></i></a>
		</button>

		<section class="pic_info" id="section2">
			<div class="inner">
				<p>여행지 소개</p>
				<div class="information_box">
					<ul class="info">
						<li class="one_quarter">
							<figure>
								<a class="imgover" href="#"><img
									src="${root}/image/korea.jpg" alt=""></a>
								<figcaption>
									<strong> 한국</strong><br> <em>Job Title Here</em>
								</figcaption>
							</figure>
						</li>
						<li class="one_quarter">
							<figure>
								<a class="imgover" href="tour_usa"><img
									src="${root}/image/europe.jpg" alt=""></a>
								<figcaption>
									<strong> 미국</strong><br> <em>Job Title Here</em>
								</figcaption>
							</figure>
						</li>
						<li class="one_quarter">
							<figure>
								<a class="imgover" href="tour_japan"><img
									src="${root}/image/japan.jpg" alt=""></a>
								<figcaption>
									<strong> 일본</strong><br> <em>Job Title Here</em>
								</figcaption>
							</figure>
						</li>
					</ul>
				</div>
			</div>
		</section>

	</div>
	<script>
		$(document).ready(function() {
			$('.slider').bxSlider();
		});
	</script>
	<script>

 

$(document).ready(function(){
  $("a").on('click', function(event) {
    if (this.hash !== "") {
      event.preventDefault();
     var hash = this.hash;
      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 800, function(){
        window.location.hash = hash;
      });
    } 

  });

});

 

</script>​ 
</body>

</html>