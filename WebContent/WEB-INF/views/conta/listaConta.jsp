<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de contas</title>
</head>
<body>
<h3>Lista de contas</h3>
<table>
	<tr>
		<th>Código</th>
		<th>Descrição</th>
		<th>Valor</th>
		<th>Tipo</th>
		<th>Paga?</th>
		<th>Data de pagamento</th>
	</tr>
	<c:forEach items="${todasContas}" var="conta">
		<tr>
			<td>${conta.id}</td>
			<td>${conta.descricao}</td>
			<td>${conta.valor}</td>
			<td>${conta.tipo}</td>
			<td id="conta_${conta.id}">
				<c:if test="${conta.paga eq false}">
					Não paga
				</c:if>
				<c:if test="${conta.paga eq true}">
					Paga!
				</c:if>
			</td>
			<td><fmt:formatDate value="${conta.dataPagamento.time}" pattern="dd/MM/yyyy"/></td>
			<td>
				<a href="removeConta?id=${conta.id}">Excluir</a> 
				||<a href="mostraConta?id=${conta.id}">Alterar</a> 
				
				<c:if test="${conta.paga eq false}">
				||<a href="#" onclick="pagaAgora(${conta.id});">Pagar</a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
<script type="text/javascript">
	function pagaAgora(id){
		//requisicao que para pagar a conta e executa a callback
		$.post("pagaConta", {'id' : id}, function() {
			  $("#conta_"+id).html("Paga");
			});
	}
</script>
</html>