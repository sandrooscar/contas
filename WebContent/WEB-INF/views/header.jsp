<!-- 
Todo arquivo de visualização do springMVC tem que ficar mesna pasta views, conforme configuração no spring-context
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="resources/js/jquery.js"> </script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
</head>
<body>
<%-- <c:set var="usuarioLogado" value="request.getSession().getAttribute("usuarioLogado")' /> --%>
<%-- <c:out value="${usuarioLogado}"/> --%>

<c:if test="${sessionScope.usuarioLogado != null}">
	<a href="efetuaLogout">Logout</a>
</c:if>
</body>
</html>