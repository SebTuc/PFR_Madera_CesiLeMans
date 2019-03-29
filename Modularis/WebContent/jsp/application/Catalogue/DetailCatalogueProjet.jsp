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

	<a href="/Modularis/DevisFacture"
		class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>Retour</a>
	<br />
	<div class="col-lg-12 text-center">
		<h1>Détail du projet ${fn:escapeXml(Projet.nom)}</h1>
		<br />
	</div>
	<br />
	<div class="container">
		<div class="row">
			<div class="row justify-content-center w-100">
				<div class="media w-100">
					<div class="media-body">
						<h5>Projet : ${fn:escapeXml(Projet.nom)}</h5>
					</div>
					<c:choose>
						<c:when test="${Projet.image.imageId != null}">
							<img src="/Modularis/Photo?id=${Projet.image.imageId }"
								class="ml-3" style="max-height: 60px;" />
						</c:when>
					</c:choose>
				</div>
			</div>
			<ul class="devisdetail checklist">
				<c:forEach var="Plan" items="${Projet.plans}">
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
										: ${fn:escapeXml(Piece.nom)}</label>
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
																<h4>> ${fn:escapeXml(Module.angle.typeAngle)} HT:
																	${fn:escapeXml(Module.angle.prixUnitaire)}&euro;</h4>
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
			<br> <br>
			<div class="row justify-content-center mt-5">
				<div class="col-xs-12">
					<form method="POST">
						<input type="hidden" name="idProjet"
							value="${fn:escapeXml(Projet.projetId)}">
						<button class="btn btn-primary" type="submit">
							<i class="material-icons pr-1)" style="float: left">file_copy</i>Copier
							le projet
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
		<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>