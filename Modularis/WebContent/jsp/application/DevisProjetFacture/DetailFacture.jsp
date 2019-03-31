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

	<a href="/Modularis/DevisFacture/ListFacture"
		class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>Retour</a>
	<br />
	
	<c:choose>
			<c:when test="${Erreur != null }">
				<div class="row justify-content-center">
					<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
				</div>
			</c:when>
		</c:choose>
	
	<div id="toPDF">
		<div class="col-lg-12 text-center">
			<h1>Détail facture de
				${fn:escapeXml(Facture.devis.client.donneesPersonelle.nom)}
				${fn:escapeXml(Facture.devis.client.donneesPersonelle.prenom)}</h1>
			<br />
		</div>
		<div class="col-lg-12 text-center">
			<h6>Devis fait le ${fn:escapeXml(Facture.devis.dateCreation)}</h6>
		</div>
		<div class="col-lg-12 text-center">
			<h6>par :
				${fn:escapeXml(Facture.devis.utilisateur.donneesPersonelle.nom)}
				${fn:escapeXml(Facture.devis.utilisateur.donneesPersonelle.prenom)}</h6>
		</div>

		<br />
		<div class="container">
			<div class="row">
				<div class="row justify-content-center w-100">
					<div class="media w-100">
						<div class="media-body">
							<h5>Projet : ${fn:escapeXml(Facture.devis.projet.nom)} | Prix du devis: ${fn:escapeXml(Facture.devis.prixHt)}&euro; </h5>
						</div>
						<c:choose>
							<c:when test="${Facture.devis.projet.image.imageId != null}">
								<img src="/Modularis/Photo?id=${Facture.devis.projet.image.imageId }"
									class="ml-3" style="max-height: 60px;" />
							</c:when>
						</c:choose>
					</div>

				</div>
				<ul class="devisdetail checklist">
					<c:choose>
						<c:when test="${PourcentageDejaPayer != 0}">
						<li>
							<h5><del> D&eacute;j&agrave; pay&eacute; (${fn:escapeXml(PourcentageDejaPayer)} %) HT: <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(DejaPayer)}" maxFractionDigits="2" />&euro;</del></h5>
						</li>
						</c:when>
					</c:choose>
					
					
					<li>
						<h4>> ${fn:escapeXml(Facture.etapeFacture.etape)} (${fn:escapeXml(Facture.etapeFacture.pourcentage)} %) HT: <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(ResteAPayer)}" maxFractionDigits="2" />&euro;</h4>
					</li>
				</ul>
				<br> <br>
				<div class="row justify-content-end w-100"
					style="color: red; font-size: 180%;">Prix total HT : ${fn:escapeXml(ResteAPayer)}&euro;</div>
			</div>
		</div>
		<div id="editor"></div>
	</div>
	<br>
	<br>

	<form method="post">
		<input type="text" name="factureId" id="factureId"
			value="${fn:escapeXml(Facture.factureId) }" style="display: none">
		<div class="row justify-content-center">
			<div class="col-md-3 col-sm-6">
				<button class="btn btn-success btn-block btn-blok" name="btnFacture"
					id="btnFacture">Etape Facturation suivante</button>
			</div>
			<div class="col-md-3 col-sm-6">
				<button class="btn btn-warning btn-block btn-blok" name="btnFactureBack"
					id="btnFactureBack">Etape Facturation pr&eacute;c&eacute;dente</button>
			</div>
			<br>
			<div class="col-md-3 col-sm-6">
				<button class="btn btn-primary btn-block btn-block" type=button
					name="btnPDF" id="btnPDF">Generer PDF</button>
			</div>
			<br>
		</div>
	</form>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>