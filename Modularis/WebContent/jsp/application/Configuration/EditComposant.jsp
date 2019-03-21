<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Edition Composant</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

<div role="main" class="container">
  
<br>

<a href="/Modularis/Configuration/ListComposant" class="btn btn-outline-dark"><span aria-hidden="true">&larr;</span> Retour</a>
<br><br>
	<form method="post">
	  <div class="form-row">
		  <input type="text" value="${fn:escapeXml(Composant.composantId) }" id="ComposantId" name="ComposantId" hidden/>
		  <div class="form-group col-md-6">
		   	<label for="nom">Nom Composant</label>
	    	<input type="text" class="form-control" name="nom" id="nom" placeholder="Nom du composant" value="${fn:escapeXml(Composant.nom) }" required>
		  </div>
		  <div class="form-group col-md-6">
			<label for="prixUnitaire">Prix unitaire</label>
		    <input type="text" class="form-control" name="prixUnitaire" id="prixUnitaire" value="${fn:escapeXml(Composant.prixUnitaire) }" placeholder="Prix Unitaire" required>
		  </div>
	  </div>
	  <div class="form-row">
		  <div class="form-group col-md-4">
		  <label for="familleComposant">Famille Composant</label>
		   	<select id="familleComposant" name="familleComposant" class="custom-select" required>
		   		<c:forEach var="FamilleComposant" items="${ListFamilleComposant }">
		   			<c:choose>
					    <c:when test="${FamilleComposant.familleComposantId == Composant.familleComposant.familleComposantId }">
					       <option selected value="${fn:escapeXml(FamilleComposant.familleComposantId) }">${fn:escapeXml(FamilleComposant.nom) } </option>
					    </c:when>    
					    <c:otherwise>
							<option value="${fn:escapeXml(FamilleComposant.familleComposantId) }">${fn:escapeXml(FamilleComposant.nom) } </option>
					    </c:otherwise>
					</c:choose>
		   			
		   		</c:forEach>
			</select>
		  </div>
		  <div class="form-group col-md-4">
		  <label for="fournisseur">Fournisseur</label>
			<select id="fournisseur" class="custom-select" name="fournisseur" required>
		   		<c:forEach var="Fournisseur" items="${ListFournisseur }">
		   			<c:choose>
					    <c:when test="${Fournisseur.fournisseurId == Composant.fournisseur.fournisseurId}">
					       <option selected value="${fn:escapeXml(Fournisseur.fournisseurId) }">${fn:escapeXml(Fournisseur.nom) } </option>
					    </c:when>    
					    <c:otherwise>
							<option value="${fn:escapeXml(Fournisseur.fournisseurId) }">${fn:escapeXml(Fournisseur.nom) } </option>
					    </c:otherwise>
					</c:choose>
		   			
		   		</c:forEach>
			</select>
		  </div>
		  <div class="form-group col-md-4">
		  <label for="materiaux">Materiaux</label>
			<select id="materiaux" name="materiaux" class="custom-select" required>
		   		<c:forEach var="Materiaux" items="${ListMateriaux }" >
		   			<c:choose>
					    <c:when test="${Materiaux.materiauxId == Composant.materiaux.materiauxId}">
					       <option selected value="${fn:escapeXml(Materiaux.materiauxId) }">${fn:escapeXml(Materiaux.nom) } </option>
					    </c:when>    
					    <c:otherwise>
							<option value="${fn:escapeXml(Materiaux.materiauxId) }">${fn:escapeXml(Materiaux.nom) } </option>
					    </c:otherwise>
					</c:choose>
		   			
		   		</c:forEach>
			</select>
		  </div>
	  </div>
	   <button class="btn btn-primary btn-lg ">Editer Composant</button>
	</form>
</div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
  <jsp:include page="/jsp/common/confirmModal.jsp" />
</body>

</html>