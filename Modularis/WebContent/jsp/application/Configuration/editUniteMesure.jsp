<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Modifier une unit&eacute; de mesure</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>
<body>
		
	<jsp:include page="/jsp/common/navbar.jsp" />
	
	<div role="main" class="container">
		<form method="post" action="UniteMesureEdit">
			<input id="uniteId" value="${uniteMesure.uniteId}" type="hidden" name="uniteId" required/>
			<input id="uniteNom" value="${fn:escapeXml(uniteMesure.nomUnite)}" type="text" name="uniteNom" placeholder="Unit&eacute;" required/>
			<button id="buttonEdit">Modifier</button>
		</form>
	</div>
	
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>