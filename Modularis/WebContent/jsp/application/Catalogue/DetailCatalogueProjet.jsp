<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détail d'un projet</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />
</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />

	<a href="/Modularis/DevisFacture" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>Retour</a>
	<br />
		 <div class="col-lg-12 text-center">
        	<h1>Détail du projet ${fn:escapeXml(Projet.nom)}</h1>
        	<br />
      	</div>
    <br/>
	<div class="container">
		<div class="row">
			<ul class="devisdetail checklist">
			<c:forEach var="Plan" items="${Projet.plans}" >	 
			  <li>
			    <input type="checkbox" name="${fn:escapeXml(Plan.nom)}" id="plan-${fn:escapeXml(Plan.planId)}">
			    <label for="plan-${fn:escapeXml(Plan.planId)}">Plan : ${fn:escapeXml(Plan.nom)}</label>
			    <ul>
			    <c:forEach var="Piece" items="${Plan.pieces}" >
			      <li>     
			        <input type="checkbox" name="${fn:escapeXml(Piece.nom)}" id="piece-${fn:escapeXml(Piece.pieceId)}">
			        <label for="piece-${fn:escapeXml(Piece.pieceId)}">Pièce : ${fn:escapeXml(Piece.nom)}</label>
			        <ul>
			        <c:forEach var="Module" items="${Piece.modules}" >
			          <li>
			        <input type="checkbox" name="${fn:escapeXml(Module.nom)}" id="module-${fn:escapeXml(Module.moduleId)}">
			        <label for="module-${fn:escapeXml(Module.moduleId)}">Module : ${fn:escapeXml(Module.nom)}</label>
			        <ul>
			        <c:forEach var="ModuleXComposant" items="${Module.moduleXComposants}" >
			          <li> 
			            <h4>Composant : ${fn:escapeXml(ModuleXComposant.composant.nom)} |   ${fn:escapeXml(ModuleXComposant.composant.prixUnitaire)}</h4>
			          </li>
			          </c:forEach>
			        </ul>
			        </li>
			        </c:forEach>
			        </ul>
			      </li>
			      </c:forEach>
			    </ul>
			  </li>
			  </c:forEach>
			</ul>
		</div>
		<div class="row justify-content-center mt-5">
			<div class="col-xs-12">
				<form method="POST">
					<input type="hidden" name="idProjet" value="${fn:escapeXml(Projet.projetId)}">
					<button class="btn btn-primary" type="submit"><i class="material-icons pr-1)" style="float:left">file_copy</i>Copier le projet</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>