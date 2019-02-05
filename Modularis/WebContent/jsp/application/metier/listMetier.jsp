<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Liste des Metiers</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>
<body>
	
	<jsp:include page="/jsp/common/navbar.jsp" />
	
    <div role="main" class="container">
      <ul class="list-group" id="uniteMesureContainer">
		<c:forEach var="Metier" items="${metiers}">
        <li class="list-group-item d-flex justify-content-between align-items-center block-unite-mesure">
          <span>${fn:escapeXml(Metier.nom)}</span>
          <div class="d-flex">
          <form method="get" action="MetierEdit">
          	<input type="hidden" name="buttonEdit" value="${Metier.metierId}">
            <button type="submit" class="material-icons-btn material-icons material-icons-airy">edit</button>
           </form>
           <form method="get" action="MetierDelete">
            <input type="hidden" name="buttonDelete" value="${Metier.metierId}">
            <button type="submit" class="material-icons-btn material-icons material-icons-airy">delete</button>
           </form>
          </div>
        </li>
		</c:forEach>
      </ul>
     <form method="post">
		<input id="metierNom" name="metierNom" placeholder="Nouveau metier" required/>
		<button>Ajouter</button>
	</form>
    </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>