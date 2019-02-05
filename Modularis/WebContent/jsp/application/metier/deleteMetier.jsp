<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Supprimer un Metier</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>
<body>
	
	<jsp:include page="/jsp/common/navbar.jsp" />
	
	<div role="main" class="container">
		<p class="lead">&Ecirc;tes vous vraiment s&ucirc;r de vouloir supprimer le m&eacute;tier ${fn:escapeXml(metier.nom)} ?</p>
		<form method="post" action="MetierDelete">
			<input value="${metier.metierId}" type="hidden" name="metierId" required/>
			<button type="submit">Confirmer</button>
		</form>
	</div>
	
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>