<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Edition Famille Composant</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

  <div role="main" class="container">
  	<table id="Edition" class="display" style="width:100%">
  		<thead>
  			<tr>
  				<th>Famille Composant</th>
  				<th>Editer</th>
  				<th>Supprimer</th>
  			</tr>
  		</thead>
  		<tbody>
      <c:forEach var="FamilleComposant" items="${ListFamilleComposant}">
        <tr>
        	<td ${FamilleComposant.familleComposantId }> ${fn:escapeXml(FamilleComposant.nom) } </td>
        	<td><button class="material-icons-btn material-icons material-icons-airy">edit</button></td>
        	<td><button class="material-icons-btn material-icons material-icons-airy">delete</button></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <form method="post" class="form-inline mt-3">
      <div class="form-group">
        <input id="familleComposantNom" class="form-control" name="familleComposantNom" placeholder="Nouvel Famille Composant" required />
        <button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
      </div>
    </form>
  </div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
  <jsp:include page="/jsp/common/confirmModal.jsp" />
</body>

</html>