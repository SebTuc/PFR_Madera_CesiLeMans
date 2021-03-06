<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Devis</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />
<link href="<%=request.getContextPath()%>/resources/css/Facture.css" rel="stylesheet">
</head>
<body>
	<a href="/Modularis/DevisFacture/DetailDevis?id=${fn:escapeXml(Devis.devisId)}"
		class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>Retour</a>
	<br />
    <div class="invoice-box" id="section-to-print">
        <table cellpadding="0" cellspacing="0">
            <tr class="top">
                <td colspan="2">
                    <table>
                        <tr>
                            <td class="title">
                               <img src="../resources/img/mpjsblack.png" style="max-width:100px;top:0px;left:0px;">
                            </td>
                            
                            <td>
                                <span class="t-invoice">FACTURE</span>
                                <span class="invoice-id"></span>
                                <br>
                                <span class="t-invoice-created">Date :</span>
                                <span class="invoice-created">${fn:escapeXml(Devis.dateCreation)}</span>
                                <br>
                                <span class="t-invoice-due">Ech�ance :</span>
                                <span class="invoice-due">${fn:escapeXml(Devis.dateCreation)}</span>
                                <br>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            
            <tr class="information">
                <td colspan="2">
                    <table>
                        <tr>
                            <td class="information-company">
                                <span class="t-invoice-from"></span><br>
                                <span id="company-name">MADERA</span><br>
                                <span id="company-address">${fn:escapeXml(Devis.utilisateur.entrepot.lieux)}</span><br>
                                <span id="company-town">FRANCE</span><br>
                            </td>
                            
                            <td class="information-client">
                                <span class="t-invoice-to"></span><br>
                                <span id="client-name">${fn:escapeXml(Devis.client.donneesPersonelle.nom)} ${fn:escapeXml(Devis.client.donneesPersonelle.prenom)}</span><br>
                                <span id="client-address">${fn:escapeXml(Devis.client.donneesPersonelle.adresse)}</span><br>
                                <span id="client-town">${fn:escapeXml(Devis.client.donneesPersonelle.ville)}</span><br>
                                <span id="client-country">France</span><br>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <table class="invoice-items" cellpadding="0" cellspacing="0">
        	 <tr class="heading">
        		<td><span class="t-item">Composant</span></td>
				<td><span class="t-price">Prix U (HT | TTC)</span></td>
            </tr>  
            <c:forEach var="Plan" items="${Devis.projet.plans}">
            	<c:forEach var="Piece" items="${Plan.pieces}">
	            	<c:forEach var="Module" items="${Piece.modules}">
		            	<tr><td style="font-weight:bold;">${fn:escapeXml(Plan.nom)} - ${fn:escapeXml(Piece.nom)} - ${fn:escapeXml(Piece.surface)}m&sup2; - ${fn:escapeXml(Module.nom)}</td></tr>
		            	<c:choose>
							<c:when test="${Module.angle != null }">
								<td>${fn:escapeXml(Module.angle.typeAngle)}</td>
							    <td>${fn:escapeXml(Module.angle.prixUnitaire)}&euro; | <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(Module.angle.prixUnitaire * 1.2)}" maxFractionDigits="2" />&euro;</td>
							</c:when>
						</c:choose>
	            		<c:forEach var="ModuleXComposant" items="${Module.moduleXComposants}">
	            			<tr>
				                <td>${fn:escapeXml(ModuleXComposant.composant.nom)} (x${fn:escapeXml(ModuleXComposant.quantite)})</td>
				                <td>${fn:escapeXml(ModuleXComposant.composant.prixUnitaire)}&euro; | <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(ModuleXComposant.composant.prixUnitaire * 1.2)}" maxFractionDigits="2" />&euro;</td>
			                </tr>
		                </c:forEach>
	                </c:forEach>
                </c:forEach>
            </c:forEach>
        </table>
        
        <div class="invoice-summary">
            <div class="invoice-total">TOTAL HT :
				<fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(Devis.prixHt)}" maxFractionDigits="2" />&euro; | TTC: <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(Devis.prixHt * 1.2)}" maxFractionDigits="2" />&euro;</div>
            <div class="invoice-final"></div>
            <div class="invoice-exchange"></div>
        </div>
       <br><br><br>
	<c:choose>
							<c:when test="${Devis.projet.image.imageId != null}">
								<div class="row justify-content-center">
								<img src="/Modularis/Photo?id=${Devis.projet.image.imageId }"
									class="ml-3" style="max-height: 400px;" />
									</div>
							</c:when>
						</c:choose>
    </div>
	<br><br>

	<button class="btn-impression" type=button onclick="window.print();">G�n�rer en PDF</button>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>