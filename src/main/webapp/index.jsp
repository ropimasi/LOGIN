<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN :: Demo Web App</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="resource/login.css">
</head>
<body>

<div class="block-container">
	
	<% if ( (request.getAttribute("msg") != null)
			&& (!((String)request.getAttribute("msg")).isBlank())
			&& (!((String)request.getAttribute("msg")).isEmpty()) ) { %>
	<div class="w3-panel w3-yellow">
		<%= request.getAttribute("msg") %>
	</div>
	<% } %>

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
			<button type="submit" class="success">Entrar</button>
		</form>
	</div>
</div>

</body>
</html>