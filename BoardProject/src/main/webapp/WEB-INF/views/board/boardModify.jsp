<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
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
     $(document).ready(()=>{
    	 $("#list").click(()=>{
    		 location.href="<c:url value='/board/list'/>";
    	 });

     });
     
     $(document).ready(()=>{
    	 $("#remove").click(()=>{
    		 location.href="<c:url value='/board/remove/${board.id}'/>";
    	 });

     });
  
  </script>
</head>
<body>
 
<div class="container">
  <h2>Board Modify Page</h2>
  <div class="panel panel-default">    
    <div class="panel-heading">Board Modify Page</div>
    <div class="panel-body">
	   <form action="<c:url value='/board/modify/${board.id}'/>" method="post">
		  <div class="form-group">
		    <label>Id</label>
		    <input type="text" class="form-control" name="id" id="id" value="${board.id}" readonly="readonly">
		  </div>		 
		  <div class="form-group">
		    <label>Title</label>
		    <input type="text" class="form-control" name="title" id="title" value="${board.title}">
		  </div>
		  <div class="form-group">
		    <label>Text area</label>
		    <textarea class="form-control" rows="3" name="content">${board.content}</textarea>
		  </div>
		  <div class="form-group">
		    <label>Writer</label>
		    <input type="text" class="form-control" name="writer" value="${board.writer}">
		  </div>
		  <button type="submit" class="btn btn-primary">수정</button>
		  <button id="remove" type="button" class="btn btn-danger">삭제</button>
		  <button id="list" type="button" class="btn btn-info">조회</button>
		</form>
    </div>
    <div class="panel-footer">프레임워크 게시판 과제</div>
  </div>
</div>

</body>
</html>