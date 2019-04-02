<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des stocks</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />
<a href="/Modularis/GestionStock/ListEntrepot" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
<div role="main" class="container">
<c:choose>
				<c:when test="${Erreur != null }">
					<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
				</c:when>
			</c:choose>
<div class="card card-edition config-table">

	<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
		<table id="Edition" class="table table-edition table-striped table-bordered" cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important; text-align: center;" 
			column-defs='[{"data": "idStock","title": "","type": "hidden","visible": false},{"data":"nomComposant","title": "Nom du composant","type": "readonly"},{"data":"quantite","title": "Etat du stock"},{"data":"fournisseur","title": "Non du fournisseur","type": "readonly"}]' 
			data-set='[<c:forEach var="Stock" items="${Entrepot.stocks}">{"idStock":"${fn:escapeXml(Stock.stockId)}","nomComposant":"${fn:escapeXml(Stock.composant.nom)}","quantite":"${fn:escapeXml(Stock.quantite)}","fournisseur":"${fn:escapeXml(Stock.composant.fournisseur.nom)}"},</c:forEach>]'>
		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	

		    <form method="post">
		     	<div class="form-row justify-content-center">
		        <input id=idEntrepot name="idEntrepot" value="${Entrepot.entrepotId }" style="display:none;" />
		        <div class="form-group col-md-4" style="margin-top: 30px;">
		        	<input id=insertQuantite name="insertQuantite" placeholder="Indiquer le stock" type="number" class="form-control" autofocus required/>
		        </div>
		        <div class="form-group col-md-4">
				  <label for="idComposant">Composant</label>
					<select id="idComposant" class="form-control" name="idComposant" required>
				   		<c:forEach var="Composant" items="${ListComposant }">
				   			<option value="${fn:escapeXml(Composant.composantId) }">${fn:escapeXml(Composant.nom) } </option>
				   		</c:forEach>
					</select>
				  </div>
				  <div class="form-group col-md-2 col-4" style="margin-top: 30px;">
		        	<button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
		        </div>
		      </div>
		    </form>
    
</div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>