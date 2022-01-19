<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="/resources/common/js/require/require.js"></script>
		<script src="/resources/common/js/require/main.js"></script>
		<title>FCM 전송 TEST</title>
	</head>
	<body>
		<h1> FCM V1 API!! </h1>
		<hr>
		<button>메세지 전송</button>
	</body>
	
	<script>
		require(["home_app"], function(app){
			app.init();
		})
	</script>
</html>