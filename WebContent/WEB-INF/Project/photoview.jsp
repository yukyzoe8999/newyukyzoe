<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td rowspan="3"><img src="${root}/${p.path}" /></td>
			<td>${p.picname}</td>
		</tr>

	</table>
	<br>
	<br>
	<c:if test="${count!=0}">
		<table>
			<c:forEach var="c" items="${c}">
				<tr>
					<td>${c.userid}</td>
				</tr>
				<tr>
					<td>${c.regdate}</td>
				</tr>
				<tr>
					<td>${c.comment}</td>
					<td>${c.score}</td>
				</tr>
			</c:forEach>
		</table>

	</c:if>
	<c:if test="${count==0}">
		<h3>댓글을 입력해주세요.</h3>
	</c:if>

	<form action="" method="post">
		<div class="comment">
			<input type="text" name="comment" /> <select name="s">
				<option ${(param.s=="5")?"selected":""} value="5">5</option>
				<option ${(param.s=="4")?"selected":""} value="5">4</option>
				<option ${(param.s=="3")?"selected":""} value="5">3</option>
				<option ${(param.s=="2")?"selected":""} value="5">2</option>
				<option ${(param.s=="1")?"selected":""} value="5">1</option>
			</select>
		</div>
		<div class="comment">
			<input type="text" name="comment" /> <span> <input
				type="submit" value="입력"></span>
		</div>
	</form>

</body>
</html>