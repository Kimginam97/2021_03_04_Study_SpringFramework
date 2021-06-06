<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>메인</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

    <p>환영합니다.</p>
    <p><a href="<c:url value="/register/step1" />">[회원 가입하기]</a>
    <p><a href="<c:url value="/survey" />">[설문 조사하기]</a>
</body>
</html>