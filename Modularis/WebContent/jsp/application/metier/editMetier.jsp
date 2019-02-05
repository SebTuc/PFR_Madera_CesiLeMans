<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Modifier un Metier</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>
<body>
	
	<jsp:include page="/jsp/common/navbar.jsp" />
	
	<div role="main" class="container">
		<form method="post" action="MetierEdit">
			<input id="metierId" value="${metier.metierId}" type="hidden" name="metierId" required/>
			<input id="metierNom" value="${fn:escapeXml(metier.nom)}" type="text" name="metierNom" placeholder="Metier" required/>
			<button id="buttonEdit">Modifier</button>
		</form>
	</div>
	
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>