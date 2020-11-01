<jsp:useBean id="calcula" class="beans.Beanportfolio"
	type="beans.Beanportfolio" scope="page" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body>

	<div class="login-page">
		<div class="form">
			<h1 class="titulo1">LOGIN</h1>
			<form class="titulo1" action="LoginServlet" method="post">
				LOGIN: <input type="text" id="login" name="login"> <br />
				SENHA: <input type="password" id="senha" name="senha"> <br />
				<button type="submit" value="Logar">Logar</button>
			</form>
		</div>
	</div>

</body>
</html>