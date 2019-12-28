<!-- 
Todo arquivo de visualização do springMVC tem que ficar nesna pasta views, conforme configuração no spring-context
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adicionar contas</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
	<h3>Adicionar contas</h3>
	<form action="adicionaConta" method="post">
		Descrição:<br />
		<textarea name="descricao" id="descricao" rows="5" cols="100"></textarea>
		<br /> 
		Valor: <input type="text" name="valor" id="valor" /> <br />
		Tipo: 
		<select name="tipo" id="tipo">
			<option value="ENTRADA">Entrada</option>
			<option value="SAIDA">Saída</option>
		</select> <br /> <br /> <input type="submit" value="Adicionar" />
	</form>
</body>
</html>