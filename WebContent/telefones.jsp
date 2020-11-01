<jsp:useBean id="calcula" class="beans.Beanportfolio"
	type="beans.Beanportfolio" scope="page" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>

	<a href="acessoliberado.jsp">Inicío</a>
	<a href="index.jsp">Sair</a>

	<center>
		<h1>CADASTRO DE TELEFONES</h1>
		<h3 style="color: blue;">${msg}</h3>
	</center>

	<form action="salvarTelefones" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>USER:</td>
						<td><input type="text" id="id" name="id" readonly="readonly"
							value="${userEscolhido.id}"></td>
							
							<td><input type="text" id="nome" name="nome" readonly="readonly"
							value="${userEscolhido.nome}"></td>
					</tr>
					
					<tr>
					<td>NÚMERO</td>
					<td><input type="text" id="numero" name="numero" ></td>
					<td>
					<select id="tipo" name="tipo">
						<option>Casa</option>
						<option>Contato</option>
						<option>Celular</option>
					</select>
					</td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
	<div class="tabela">
		<table class="responsive-table">
			<caption>Usuários cadastrados</caption>
			<tr>
				<th>ID</th>
				<th>NÚMERO</th>
				<th>TIPO</th>
				<th>EXCLUIR</th>
			</tr>
			<tbody>
				<c:forEach items="${telefones}" var="fone">
					<tr>
						<td style="width: 150px"><c:out value="${fone.id}"></c:out></td>
						<td style="width: 150px"><c:out value="${fone.numero}"></c:out></td>
						<td><c:out value="${fone.tipo}"></c:out></td>

						<td><a href="salvarTelefones?acao=deleteFone&foneId=${fone.id}"><img
								src="resources/img/images.png" alt="Excluir" title="Excluir"
								width="20px" height="20px"></a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numero").value == '') {
				alert('Informe o Número');
				return false;
			} else if (document.getElementById("tipo").value == '') {
				alert('Informe o Tipo');
				return false;
			}

			return true;
		}
	</script>


</body>
</html>