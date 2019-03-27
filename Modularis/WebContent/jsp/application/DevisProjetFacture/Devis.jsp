<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détail du devis</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />
</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />

	<a href="/Modularis/DevisFacture" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>Retour</a>
	<br />
		 <div class="col-lg-12 text-center">
        	<h1>Détail du devis</h1>
        	<br />
      	</div>
    <br/>
	<div class="container">
		<div class="row">
			<ul class="devisdetail checklist">
			<c:forEach var="Plan" items="${Devis.projet.plans}" >	 
			  <li>
			    <input type="checkbox" name="${fn:escapeXml(Plan.nom)}" id="${fn:escapeXml(Plan.nom)}">
			    <label for="${fn:escapeXml(Plan.nom)}">Plan : ${fn:escapeXml(Plan.nom)}</label>
			    <ul>
			    <c:forEach var="Piece" items="${Plan.pieces}" >
			      <li>     
			        <input type="checkbox" name="${fn:escapeXml(Piece.nom)}" id="${fn:escapeXml(Piece.nom)}">
			        <label for="${fn:escapeXml(Piece.nom)}">Pièce : ${fn:escapeXml(Piece.nom)}</label>
			        <ul>
			        <c:forEach var="Module" items="${Piece.modules}" >
			          <li>
			        <input type="checkbox" name="${fn:escapeXml(Module.nom)}" id="${fn:escapeXml(Module.nom)}">
			        <label for="${fn:escapeXml(Module.nom)}">Module : ${fn:escapeXml(Module.nom)}</label>
			        <ul>
			        <c:forEach var="ModuleXComposant" items="${Module.moduleXComposant}" >
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
	</div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>