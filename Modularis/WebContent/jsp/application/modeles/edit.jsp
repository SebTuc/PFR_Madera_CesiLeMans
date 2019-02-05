<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Modifier un MODEL_NOM</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>
<body>
	
	<!-- EXEMPLE DE PAGE MODIFICATION -->
	
	<jsp:include page="/jsp/common/navbar.jsp" />
	
	<div role="main" class="container">
		<form method="post" action="ModelEdit">
			<input id="modelId" value="${MODEL.Id}" type="hidden" name="modelId" required/>
			<input id="modelNom" value="${fn:escapeXml(model.nom)}" type="text" name="modelNom" placeholder="" required/>
			<button id="buttonEdit">Modifier</button>
		</form>
	</div>
	
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>