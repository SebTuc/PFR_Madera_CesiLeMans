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

	<a href="/Modularis/DevisFacture/ListDevis"
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
		<h1>Détail du devis de
			${fn:escapeXml(Devis.client.donneesPersonelle.nom)}
			${fn:escapeXml(Devis.client.donneesPersonelle.prenom)}</h1>
		<br />
	</div>
	<div class="col-lg-12 text-center">
		<h6>Fait le ${fn:escapeXml(Devis.dateCreation)}</h6>
	</div>
	<div class="col-lg-12 text-center">
		<h6>par :
			${fn:escapeXml(Devis.utilisateur.donneesPersonelle.nom)}
			${fn:escapeXml(Devis.utilisateur.donneesPersonelle.prenom)}</h6>
	</div>

	<br />
	<div class="container">
		<div class="row">
			<div class="row justify-content-center w-100">
				<div class="media w-100">
					<div class="media-body">
						<h5>Projet : ${fn:escapeXml(Devis.projet.nom)}</h5>
					</div>
					<c:choose>
						<c:when test="${Devis.projet.image.imageId != null}">
							<img src="/Modularis/Photo?id=${Devis.projet.image.imageId }"
								class="ml-3" style="max-height: 60px;" />
						</c:when>
					</c:choose>
				</div>

			</div>
			<ul class="devisdetail checklist">
				<c:forEach var="Plan" items="${Devis.projet.plans}">
					<li><input type="checkbox"
						name="${fn:escapeXml(Plan.nom)}${fn:escapeXml(Plan.planId)}"
						id="${fn:escapeXml(Plan.nom)}${fn:escapeXml(Plan.planId)}">
						<label for="${fn:escapeXml(Plan.nom)}${fn:escapeXml(Plan.planId)}">Plan
							: ${fn:escapeXml(Plan.nom)}</label>
						<ul>
							<c:forEach var="Piece" items="${Plan.pieces}">
								<li><input type="checkbox"
									name="${fn:escapeXml(Piece.nom)}${fn:escapeXml(Piece.pieceId)}"
									id="${fn:escapeXml(Piece.nom)}${fn:escapeXml(Piece.pieceId)}">
									<label
									for="${fn:escapeXml(Piece.nom)}${fn:escapeXml(Piece.pieceId)}">Pièce
										: ${fn:escapeXml(Piece.nom)} - ${fn:escapeXml(Piece.surface)}m&sup2;</label>
									<ul>
										<c:forEach var="Module" items="${Piece.modules}">
											<li><input type="checkbox"
												name="${fn:escapeXml(Module.nom)}${fn:escapeXml(Plan.nom)}${fn:escapeXml(Plan.planId)}${fn:escapeXml(Piece.pieceId)}"
												id="${fn:escapeXml(Module.nom)}${fn:escapeXml(Plan.nom)}${fn:escapeXml(Plan.planId)}${fn:escapeXml(Piece.pieceId)}">
												<label
												for="${fn:escapeXml(Module.nom)}${fn:escapeXml(Plan.nom)}${fn:escapeXml(Plan.planId)}${fn:escapeXml(Piece.pieceId)}">Module
													: ${fn:escapeXml(Module.nom)}</label>
												<ul>
												<c:choose>
												<c:when test="${Module.angle != null }">
													<li>
														<h4>
															> ${fn:escapeXml(Module.angle.typeAngle)}
																HT: ${fn:escapeXml(Module.angle.prixUnitaire)}&euro; 
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
																	maxFractionDigits="3" />
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
			</ul>
			<br>
			<br>
			<div class="row justify-content-end w-100"
				style="color: red; font-size: 180%;">Prix total HT :
				${Devis.prixHt}&euro;</div>
		</div>
	</div>
	<div id="editor"></div>
</div>
	<br> <br>
	
		<form method="post">
			<input type="text" name="devisId" id="devisId" value="${fn:escapeXml(Devis.devisId) }" style="display:none">
			<div class="row justify-content-center">
				<div class="col-md-3 col-sm-6">
					<button class="btn btn-success btn-block btn-blok" name="btnFacture" id="btnFacture">Passer en facturation</button>
				</div>
				<br>
				<div class="col-md-3 col-sm-6">
					<a href="/Modularis/DevisFacture/Devis?id=${fn:escapeXml(Devis.devisId)}" class="btn btn-primary btn-block">Générer PDF</a>
				</div>
				<br>
			</div>
		</form>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>