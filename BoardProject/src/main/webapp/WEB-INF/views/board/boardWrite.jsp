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
    $(document).ready(function(){ 
    	$("#reset").click(()=>{
    	   location.href="<c:url value='/'/>";	
    	});
    }); 
  </script>
</head>
<body>
 
<div class="container">
  <h2>Board Register</h2>
  <div class="panel panel-default">    
    <div class="panel-heading">Board Register</div>
    <div class="panel-body">
	   <form action="<c:url value='/board/write'/>" method="post">
		  <div class="form-group">
		    <label>Title</label>
		    <input type="text" class="form-control" name="title">
		  </div>
		  <div class="form-group">
		    <label>Text area</label>
		    <textarea class="form-control" rows="3" name="content"></textarea>
		  </div>
		  <div class="form-group">
		    <label>Writer</label>
		    <input type="text" class="form-control" name="writer">
		  </div>
		  <button type="submit" class="btn btn-default">등록</button>
		  <button id="reset" type="reset" class="btn btn-default">취소</button>
		</form>
    </div>
  </div>
</div>

</body>
</html>