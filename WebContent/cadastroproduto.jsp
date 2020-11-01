<jsp:useBean id="calcula" class="beans.BeanProduto"
	type="beans.BeanProduto" scope="page" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de produtos</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>

<a href="acessoliberado.jsp">Inicío</a>
<a href="index.jsp">Sair</a>

	<center>
		<h1>CADASTRO DE PRODUTOS</h1>
		<h3 style="color: blue;">${msg}</h3>
	</center>

	<form action="salvarProduto" method="post" id="formProdu"
		onsubmit="return validarCampos() ? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${prod.id}"></td>
					</tr>
					<tr>
						<td>NOME:</td>
						<td><input type="text" id="nome" name="nome"
							value="${prod.nome}" placeholder="Informe o nome do produto"></td>
					</tr>
					<tr>
						<td>QUANTIDADE:</td>
						<td><input type="text" id="quantidade" name="quantidade"
							value="${prod.quantidade}" placeholder="Informe a quantidade"></td>
					</tr>
					<tr>
						<td>VALOR R$:</td>
						<td><input type="text" id="valor" name="valor"
							value="${prod.valor}" placeholder="Informe o valor"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"><input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formProdu').action='salvarProduto?acao=reset'"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
	<div class="tabela">
		<table class="responsive-table">
			<caption>Produtos cadastrados</caption>
			<tr>
				<th>ID</th>
				<th>NOME</th>
				<th>QUANTIDADE</th>
				<th>VALOR</th>
				<th>EXCLUIR</th>
				<th>EDITAR</th>
			</tr>
			<tbody>
				<c:forEach items="${produtos}" var="prod">
					<tr>
						<td style="width: 150px"><c:out value="${prod.id}"></c:out></td>
						<td style="width: 150px"><c:out value="${prod.nome}"></c:out></td>
						<td><c:out value="${prod.quantidade}"></c:out></td>
						<td><c:out value="${prod.valor}"></c:out></td>

						<td><a href="salvarProduto?acao=delete&prod=${prod.id}"><img
								src="resources/img/images.png" alt="Excluir" title="Excluir"
								width="20px" height="20px"></a></td>

						<td><a href="salvarProduto?acao=editar&prod=${prod.id}"><img
								alt="Editar" src="resources/img/Edit-validated-icon.png"
								title="Editar" width="20px" height="20px"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("nome").value == '') {
				alert('Informe o Nome');
				return false;
			} else if (document.getElementById("quantidade").value == '') {
				alert('Informe a Quantidade');
				return false;
			} else if (document.getElementById("valor").value == '') {
				alert('Informe o Valor');
				return false;
			} 

			return true;
		}
	</script>

</body>
</html>