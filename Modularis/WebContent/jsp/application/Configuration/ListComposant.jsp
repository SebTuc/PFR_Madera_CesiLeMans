<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>List Composant</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<div role="main" class="container">
  
<br>
<a href="/Modularis/Configuration" class="btn btn-outline-dark"><span aria-hidden="true">&larr;</span> Retour</a>
<br>

	<c:forEach var="Composant" items="${ListComposant}" >
		<div class="form-check form-check-inline">
		  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="${fn:escapeXml(Composant.composantId)}">
		  <label class="form-check-label" for="inlineRadio1">${fn:escapeXml(Composant.nom)}</label>
		</div>
	</c:forEach>
	<div class="form-check form-check-inline">
	 <button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
	</div>
</div>
  <jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>