<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript">
	$("#btn").click(function(){
		alert("11");
		$.get("stat/hello", function(message){
			alert(message);
		})
	});


</script>
</head>
<body>
<h2>Hello World!</h2>
<button id="btn">123</button>
</body>
</html>
