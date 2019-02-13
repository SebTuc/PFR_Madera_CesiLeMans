<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Liste des unit&eacute;s de mesures</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>
<body>

	<!-- EXEMPLE DE PAGE LISTING -->
	
	<jsp:include page="/jsp/common/navbar.jsp" />
	
    <div role="main" class="container">
      <ul class="list-group" id="modelContainer">
		<c:forEach var="UniteMesure" items="${uniteMesures}">
        <li class="list-group-item d-flex justify-content-between align-items-center block-unite-mesure">
          <span>${fn:escapeXml(UniteMesure.nomUnite)}</span>
          <div class="d-flex">
          <form method="get" action="UniteMesureEdit">
          	<input type="hidden" name="buttonEdit" value="${UniteMesure.uniteId}">
            <button type="submit" class="material-icons-btn material-icons material-icons-airy">edit</button>
           </form>
           <form method="get" action="UniteMesureDelete">
            <input type="hidden" name="buttonDelete" value="${UniteMesure.uniteId}">
            <button type="submit" class="material-icons-btn material-icons material-icons-airy">delete</button>
           </form>
          </div>
        </li>
		</c:forEach>
      </ul>
     <form method="post">
		<input id="uniteNom" name="uniteNom" placeholder="Nouvelle unit&eacute; de mesure" required/>
		<button>Ajouter</button>
	</form>
    </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>