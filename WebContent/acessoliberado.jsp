<jsp:useBean id="calcula" class="beans.Beanportfolio"
	type="beans.Beanportfolio" scope="page" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<center>
		<h3>SEJA BEM VINDO</h3>

		<a href="salvarUsuario?acao=listartodos"><img
			alt="Cadastrar Usuários" src="resources/img/parceiros.png"
			title="Cadastrar Usuários" width="100px" height="100px">
		<p>Cadastro de usuários</p></a> <a href="salvarProduto?acao=listartodos"><img
			alt="Cadastrar Produtos" src="resources/img/1170577.png"
			title="Cadastrar Produtos" width="100px" height="100px"><p>Cadastro de Produtos</p></a>
	</center>

</body>
</html>