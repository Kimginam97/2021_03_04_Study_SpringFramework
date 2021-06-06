<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>   
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){ 
    	$("#writeBtn").click(()=>{
    	   location.href="<c:url value='/board/write'/>";	
    	});
    }); 
  </script>
</head>
<body>
 
<div class="container">
  <h2>메인화면</h2>
  <div class="panel panel-default">
    <div class="panel-heading">프레임워크 게시판 만들기
     <button id="writeBtn" type="button" class="btn btn-xs pull-right btn-primary">게시물쓰기</button>
    </div>
    <div class="panel-body">
		<div class="table-responsive">          
		  <table class="table table-hover">
		    <thead>
		      <tr>
		        <th>게시물번호</th>
		        <th>제목</th>
		        <th>내용</th>
		        <th>등록자</th>
		        <th>등록일</th>
		      </tr>
		    </thead>
		    <tbody>
		    <c:forEach var="list" items="${boardList}">
		      <tr>
		        <td>${list.id}</td>
		        <td><a href="<c:url value='/board/list/${list.id}'/>">${list.title}</a></td>
		        <td>${list.content}</td>
		        <td>${list.writer}</td>
		        <td><tf:formatDateTime value="${list.registerDateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
		      </tr>
		    </c:forEach>
		    </tbody>
		  </table>
		  </div>
  </div>
    <div class="panel-footer">프레임워크 게시판 과제</div>
  </div>
</div>

</body>
</html>