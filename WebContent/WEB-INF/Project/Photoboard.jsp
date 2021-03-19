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
<style>
.photo {
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>
</head>
<body>
	<ul class="photo">
		<c:forEach var="p" items="${plist}">
			<li><a href="photoview?pid=${p.pid}"><img src="${root}/${p.path}" width="250px" /></a>
			<div>${p.picname}</div>
			<div>${p.regdate}</div>
			</li>
		</c:forEach>
	</ul>






</body>
</html>