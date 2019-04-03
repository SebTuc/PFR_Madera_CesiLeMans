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
			<h6>Devis fait le ${fn:escapeXml(Facture.dateModification)}</h6>
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
							<h5>Projet : ${fn:escapeXml(Facture.devis.projet.nom)} | Prix du devis HT: ${fn:escapeXml(Facture.devis.prixHt)}&euro; | Prix du devis TTC: <fmt:formatNumber type="number" groupingUsed="false" value="${(Facture.devis.prixHt) * 1.2}" maxFractionDigits="2" />&euro; </h5>
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
				<c:forEach var="Plan" items="${Facture.devis.projet.plans}">
					<li><input type="checkbox"
						name="${fn:escapeXml(Plan.nom)}${fn:escapeXml(Plan.planId)}"
						id="${fn:escapeXml(Plan.nom)}${fn:escapeXml(Plan.planId)}">
						<label for="${fn:escapeXml(Plan.nom)}${fn:escapeXml(Plan.planId)}">Plan
							: ${fn:escapeXml(Plan.nom)}</label>
						<ul>
							<c:forEach var="Piece" items="${Plan.pieces}">
								<li><input type="checkbox"
									name="${fn:escapeXml(Piece.nom)}${fn:escapeXml(Piece.pieceId)}${fn:escapeXml(Plan.planId)}"
									id="${fn:escapeXml(Piece.nom)}${fn:escapeXml(Piece.pieceId)}${fn:escapeXml(Plan.planId)}">
									<label
									for="${fn:escapeXml(Piece.nom)}${fn:escapeXml(Piece.pieceId)}${fn:escapeXml(Plan.planId)}">Pièce
										: ${fn:escapeXml(Piece.nom)} - ${fn:escapeXml(Piece.surface)}m&sup2;</label>
									<ul>
										<c:forEach var="Module" items="${Piece.modules}">
											<li><input type="checkbox"
												name="${fn:escapeXml(Module.nom)}${fn:escapeXml(Module.moduleId)}${fn:escapeXml(Plan.planId)}${fn:escapeXml(Piece.pieceId)}"
												id="${fn:escapeXml(Module.nom)}${fn:escapeXml(Module.moduleId)}${fn:escapeXml(Plan.planId)}${fn:escapeXml(Piece.pieceId)}">
												<label
												for="${fn:escapeXml(Module.nom)}${fn:escapeXml(Module.moduleId)}${fn:escapeXml(Plan.planId)}${fn:escapeXml(Piece.pieceId)}">Module
													: ${fn:escapeXml(Module.nom)}</label>
												<ul>
												<c:choose>
												<c:when test="${Module.angle != null }">
													<li>
														<h4>
															> ${fn:escapeXml(Module.angle.typeAngle)}
																HT: ${fn:escapeXml(Module.angle.prixUnitaire)}&euro; | TTC: <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(Module.angle.prixUnitaire * 1.2)}" maxFractionDigits="2" />&euro;
															</h4>
													</li>
												</c:when>
												</c:choose>
													<c:forEach var="ModuleXComposant"
														items="${Module.moduleXComposants}">
														<li>
															<h4>
																> ${fn:escapeXml(ModuleXComposant.composant.nom)}
																${fn:escapeXml(ModuleXComposant.composant.prixUnitaire)}&euro;
																x${fn:escapeXml(ModuleXComposant.quantite)} | HT:
																<fmt:formatNumber type="number" groupingUsed="false"
																	value="${fn:escapeXml(ModuleXComposant.quantite * ModuleXComposant.composant.prixUnitaire)}"
																	maxFractionDigits="2" /> | TTC: <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml((ModuleXComposant.quantite * ModuleXComposant.composant.prixUnitaire) * 1.2)}" maxFractionDigits="2" />&euro; />
																&euro;
															</h4>
														</li>
													</c:forEach>
												</ul></li>
										</c:forEach>
									</ul></li>
							</c:forEach>
						</ul></li>
				</c:forEach>
			
					<c:choose>
						<c:when test="${PourcentageDejaPayer != 0}">
						<li>
							<h5><del> D&eacute;j&agrave; pay&eacute; (${fn:escapeXml(PourcentageDejaPayer)} %) HT: <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(DejaPayer)}" maxFractionDigits="2" />&euro; | TTC: <fmt:formatNumber type="number" groupingUsed="false" value="${DejaPayer * 1.2}" maxFractionDigits="2" />&euro;</del></h5>
						</li>
						</c:when>
					</c:choose>
					
					
					<li>
						<h4>> ${fn:escapeXml(Facture.etapeFacture.etape)} (${fn:escapeXml(Facture.etapeFacture.pourcentage)} %) HT: <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(ResteAPayer)}" maxFractionDigits="2" />&euro; | TTC: <fmt:formatNumber type="number" groupingUsed="false" value="${ResteAPayer * 1.2}" maxFractionDigits="2" />&euro;</h4>
					</li>
				</ul>
				<br> <br>
				<div class="row justify-content-end w-100"
					style="color: red; font-size: 150%;">Prix total HT : <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(ResteAPayer)}" maxFractionDigits="2" />&euro;  | TTC: <fmt:formatNumber type="number" groupingUsed="false" value="${ResteAPayer * 1.2}" maxFractionDigits="2" />&euro;</div>
			</div>
		</div>
		<div id="editor"></div>
	</div>
	<br>
	<br>

	<form method="post" id="formNextPrev">
		<input type="text" name="factureId" id="factureId"
			value="${fn:escapeXml(Facture.factureId) }" style="display: none">
		<div class="row justify-content-center">
			<div class="col-md-3 col-sm-6">
				<button type="button" class="btn btn-warning btn-block" name="btnFactureBack"
					id="btnFacturePrev" data-toggle="modal"
					data-target="#ModalSimpleConfirmation">Etape Facturation pr&eacute;c&eacute;dente</button>
			</div>
			<div class="col-md-3 col-sm-6">
				<button type="button" class="btn btn-success btn-block" name="btnFacture"
					id="btnFactureNext" data-toggle="modal"
					data-target="#ModalSimpleConfirmation">Etape Facturation suivante</button>
			</div>
			<div class="col-md-3 col-sm-6">
					<a href="/Modularis/DevisFacture/DetailDevis?id=${fn:escapeXml(Facture.devis.devisId)}" class="btn btn-primary btn-block">Visualiser detail devis</a>
				</div>
			<br>
			<div class="col-md-3 col-sm-6">
					<a href="/Modularis/DevisFacture/Facture?id=${fn:escapeXml(Facture.factureId)}" class="btn btn-primary btn-block">Générer PDF</a>
				</div>
			<br>
			<br>
		</div>
	</form>
	
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
	<jsp:include page="/jsp/common/confirmModal.jsp" />
	<script type="text/javascript">
	$("#btnFactureNext").add("#btnFacturePrev").click(function(e){
		$("#btnSimpleModalOui").click(function(){			
			var input = $("<input>")
	        .attr("type", "hidden")
	        .attr("name", e.target.attributes.name.value);
			
	        $('#formNextPrev').append(input);
			$("#formNextPrev").submit();
		})
	});
	</script>
</body>
</html>