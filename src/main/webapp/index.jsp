<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="resource/login.css">
</head>
<body>


	<div class="login-container">
		<h2>Login</h2>
		<form class="login-form" id="loginForm" name="loginForm" method="post" action="LoginServlet">
			<div class="form-group">
				<label for="loginFormUsername">Usuário:</label>
				<input type="text" id="loginFormUsername" name="loginFormUsername" required>
			</div>
			<div class="form-group">
				<label for="loginFormPassword">Senha:</label>
				<input type="password" id="loginFormPassword" name="loginFormPassword" required>
			</div>
			<button type="submit">Entrar</button>
		</form>
	</div>


</body>
</html>