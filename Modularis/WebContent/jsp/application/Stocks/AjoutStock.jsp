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
<a href="/GestionStock/ListEntrepot" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
<div role="main" class="container">

<div class="card card-edition config-table">

	<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
		<table id="Edition" class="table table-edition table-striped table-bordered" cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important; text-align: center;" 
			column-defs='[{"data": "id","title": "","type": "hidden","visible": false},{"data":"nomComposant","title": "Nom du composant"},{"data":"stock","title": "Stocks"}]' 
			data-set='[<c:forEach var="Stock" items="${Entrepot.stocks}">{"id":"${fn:escapeXml(Stock.stockId)}","nomComposant":"${fn:escapeXml(Stock.composant.nom)}","stock":"${fn:escapeXml(Stock.quantite)}"},</c:forEach>]'>
		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	
  	<div class="row">
		<div class="col-xs-6 col-sm-4">
		    <form method="post" class="form-inline">
		      <div class="form-group">
		        <input id="gammeNom" class="form-control" name="gammeNom" placeholder="Nouvel Gamme" autofocus required />
		        <button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
		      </div>
		    </form>
		</div>
	</div>
    
</div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>