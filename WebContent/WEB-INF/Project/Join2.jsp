<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>회원가입</h1>
    <form action="Join" method="POST">
    <div>
        <input type="text"placeholder="ID" name="ID" maxlength="20">
    </div>
    <div>
        <input type="password" placeholder="PASSWORD" name="PASS" maxlength="20">
    </div>
    <div>
        <input type="text"  placeholder="이름" name="NAME" maxlength="20">
    </div>
    <div>
        <input type="email" placeholder="email" name="EMAIL" maxlength="20">
    </div>
    <input type="submit"  value="회원가입">
</form>
</body>
</html>