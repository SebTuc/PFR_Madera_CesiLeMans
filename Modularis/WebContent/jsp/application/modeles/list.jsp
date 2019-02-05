<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Liste des Models</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>
<body>

	<!-- EXEMPLE DE PAGE LISTING -->
	
	<jsp:include page="/jsp/common/navbar.jsp" />
	
    <div role="main" class="container">
      <ul class="list-group" id="modelContainer">
		<c:forEach var="Model" items="${models}">
        <li class="list-group-item d-flex justify-content-between align-items-center block-unite-mesure">
          <span>${fn:escapeXml(Model.nom)}</span>
          <div class="d-flex">
          <form method="get" action="ModeEdit">
          	<input type="hidden" name="buttonEdit" value="${Model.Id}">
            <button type="submit" class="material-icons-btn material-icons material-icons-airy">edit</button>
           </form>
           <form method="get" action="ModelDelete">
            <input type="hidden" name="buttonDelete" value="${Model.Id}">
            <button type="submit" class="material-icons-btn material-icons material-icons-airy">delete</button>
           </form>
          </div>
        </li>
		</c:forEach>
      </ul>
     <form method="post">
		<input id="modelNom" name="modelNom" placeholder="Nouveau model" required/>
		<button>Ajouter</button>--
	</form>
    </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>