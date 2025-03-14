<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>LOGIN : REGISTRATION :: Demo Web App</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="resource/login.css">
</head>
<body>

<div class="block-container">
	<div class="container m10">
		<h4>Usuário Logado: <%= request.getAttribute("logedUsername") %></h4>
		<h6>Usuário no banco de dados: <%= request.getAttribute("encryptedUsername") %></h6>
		<h6>Senha no banco de dados: <%= request.getAttribute("encryptedPassword") %></h6>
	</div>


	<div class="flex-container">
		<div class="form-container m10">
			<h2>Cadastro de Usuários</h2>
			<form class="login-form" id="loginForm" name="loginForm" method="post" action="LoginServlet">
				<div class="form-group">
					<label for="registFormUsername">Usuário:</label>
					<input type="text" id="loginFormUsername" name="loginFormUsername" required>
				</div>
				<div class="form-group">
					<label for="registFormPassword">Senha:</label>
					<input type="password" id="loginFormPassword" name="loginFormPassword" required>
				</div>
				<div class="form-group">
					<label for="registFormName">Nome:</label>
					<input type="text" id="registFormName" name="registFormName" required>
				</div>
				<div class="form-group">
					<label for="registFormUsername">Email:</label>
					<input type="text" id="registFormEmail" name="registFormEmail" required>
				</div>
				<div class="flex-container">			
					<button type="reset" class="secondary m5">Ressetar</button>
					<button type="submit" class="success m5">Gravar &gt;</button>
				</div>
				
			</form>
		</div>
		
		<div class="list-container m10">
			<h2>Lista de Usuários</h2>
			...
		</div>
		
	</div>
</div>

</body>
</html>