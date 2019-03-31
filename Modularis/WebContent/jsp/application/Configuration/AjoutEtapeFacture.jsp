<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Ajout etape facture</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>
<jsp:include page="/jsp/common/navbar.jsp" />

<a href="/Modularis/Configuration" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
<div role="main" class="container">

<c:choose>
				<c:when test="${Erreur != null }">
					<div class="alert alert-danger" role="alert">${fn:escapeXml(Erreur)}</div>
				</c:when>
			</c:choose>
<br>
<br>
<div class="card card-edition">

	<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
		<table id="Edition" class="table table-edition table-striped table-bordered"cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important;text-align: center; "
				column-defs='[{"data": "id","title": "","type": "hidden","visible": false},
				{"data":"nEtape","title": "Index etape" , "type" : "number"},
				{"data":"etape","title": "descriptif etape"},
				{"data":"pourcentage","title": "pourcentage" , "type" : "number"}]' 
				
				data-set='[<c:forEach var="EtapeFacture" items="${ListEtapeFacture}">{"id":"${fn:escapeXml(EtapeFacture.etapeFactureId)}",
				"nEtape":"${fn:escapeXml(EtapeFacture.nEtape)}",
				"etape":"${fn:escapeXml(EtapeFacture.etape)}",
				"pourcentage":"${fn:escapeXml(EtapeFacture.pourcentage)}"},</c:forEach>]'>

		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	
  
  <div class="row">
		<div class="col-xs-6 col-sm-4">
		    <form method="post" class="form-inline">
		      <div class="form-group">
		        <input id="insertNEtape" class="form-control" type="number" name="insertNEtape" placeholder="Index etape" required />
		         <input id="insertEtape" class="form-control" type="text" name="insertEtape" placeholder="Descriptif etape" required />
		         <input id="insertPourcentage" class="form-control" type="number"  name="insertPourcentage" placeholder="Pourcentage" required />
		        <button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
		      </div>
		    </form>
		</div>
		
	</div>
</div>	
  <jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>