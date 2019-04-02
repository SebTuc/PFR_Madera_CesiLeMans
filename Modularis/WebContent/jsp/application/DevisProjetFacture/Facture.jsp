<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Facture</title>
<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />
<link href="<%=request.getContextPath()%>/resources/css/Facture.css" rel="stylesheet">
</head>
<body>
	<a href="/Modularis/DevisFacture/DetailFacture?id=${Facture.factureId }"
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
                                <span class="invoice-created">${fn:escapeXml(Facture.dateModification)}</span>
                                <br>
                                <span class="t-invoice-due">Ech�ance :</span>
                                <span class="invoice-due">${fn:escapeXml(Facture.dateModification)}</span>
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
                                <span id="company-address">${fn:escapeXml(Facture.devis.utilisateur.entrepot.lieux)}</span><br>
                                <span id="company-country">FRANCE</span><br>
                            </td>
                            
                            <td class="information-client">
                                <span class="t-invoice-to"></span><br>
                                <span id="client-name">${fn:escapeXml(Facture.devis.client.donneesPersonelle.nom)} ${fn:escapeXml(Devis.client.donneesPersonelle.prenom)}</span><br>
                                <span id="client-address">${fn:escapeXml(Facture.devis.client.donneesPersonelle.adresse)}</span><br>
                                <span id="client-town">${fn:escapeXml(Facture.devis.client.donneesPersonelle.ville)}</span><br>
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
            <c:forEach var="Plan" items="${Facture.devis.projet.plans}">
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
            <tr>
            	<td></td>
            </tr>
            <tr>
            	<td>Etape de paiment :</td>
            </tr>
            <c:choose>
				<c:when test="${PourcentageDejaPayer != 0}">
						<tr>
							<td><del> D&eacute;j&agrave; pay&eacute; (${fn:escapeXml(PourcentageDejaPayer)} %)</del></td>
							<td><fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(DejaPayer)}" maxFractionDigits="2" />&euro;  | TTC: <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(DejaPayer * 1.2)}" maxFractionDigits="2" />&euro;</td>
						</tr>
						</c:when>
					</c:choose>
					<tr>
						<td style="font-weight: bold; font-size: 130%">> ${fn:escapeXml(Facture.etapeFacture.etape)} (${fn:escapeXml(Facture.etapeFacture.pourcentage)} %)</td>
						<td><fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(ResteAPayer)}" maxFractionDigits="2" />&euro;  | TTC: <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(ResteAPayer * 1.2)}" maxFractionDigits="2" />&euro;</td>
					</tr>
        </table>
        
        <div class="invoice-summary">
            <div class="invoice-total">TOTAL HT :
				${fn:escapeXml(ResteAPayer)}&euro; | TTC: <fmt:formatNumber type="number" groupingUsed="false" value="${fn:escapeXml(ResteAPayer * 1.2)}" maxFractionDigits="2" />&euro;</div>
            <div class="invoice-final"></div>
            <div class="invoice-exchange"></div>
        </div>
    </div>
	<br>
	<button class="btn-impression" type=button onclick="window.print();">G�n�rer en PDF</button>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>