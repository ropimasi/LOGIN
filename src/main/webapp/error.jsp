<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página padrão de erro</title>
</head>
<body>
<h1>Erro, entre em contato com a equipe de suporte do sistema.</h1>

<textarea rows="16" cols="80" readonly="readonly"> <%= request.getAttribute("msg") %> </textarea>

</body>
</html>