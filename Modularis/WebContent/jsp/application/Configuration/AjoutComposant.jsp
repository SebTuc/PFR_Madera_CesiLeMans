<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Ajout Composant</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<div role="main" class="container">
  
<br>

<a href="/Modularis/Configuration" class="btn btn-outline-dark"><span aria-hidden="true">&larr;</span> Retour</a>
<br><br>
	<form method="post">
	  <div class="form-row">
		  <div class="form-group col-md-6">
		   	<label for="formGroupExampleInput">Nom Composant</label>
	    	<input type="text" class="form-control" name="nom" id="nom" placeholder="Nom du composant" required>
		  </div>
		  <div class="form-group col-md-6">
			<label for="formGroupExampleInput2">Prix unitaire</label>
		    <input type="text" class="form-control" name="prixUnitaire" id="adresse" placeholder="Prix Unitaire" required>
		  </div>
	  </div>
	  <div class="form-row">
		  <div class="form-group col-md-4">
		   	<select id="familleComposant" name="familleComposant" class="custom-select" required>
		   		<c:forEach var="FamilleComposant" items="${ListFamilleComposant }">
		   			<option value="${fn:escapeXml(FamilleComposant.familleComposantId) }">${fn:escapeXml(FamilleComposant.nom) } </option>
		   		</c:forEach>
			</select>
		  </div>
		  <div class="form-group col-md-4">
			<select id="fournisseur" class="custom-select" name="fournisseur" required>
		   		<c:forEach var="Fournisseur" items="${ListFournisseur }">
		   			<option value="${fn:escapeXml(Fournisseur.fournisseurId) }">${fn:escapeXml(Fournisseur.nom) } </option>
		   		</c:forEach>
			</select>
		  </div>
		  <div class="form-group col-md-4">
			<select id="materiaux" name="materiaux" class="custom-select" required>
		   		<c:forEach var="Materiaux" items="${ListMateriaux }" >
		   			<option value="${fn:escapeXml(Materiaux.materiauxId) }">${fn:escapeXml(Materiaux.nom) } </option>
		   		</c:forEach>
			</select>
		  </div>
	  </div>
	   <button class="btn btn-primary btn-lg ">Ajouter Composant</button>
	</form>
</div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
  <jsp:include page="/jsp/common/confirmModal.jsp" />
</body>

</html>