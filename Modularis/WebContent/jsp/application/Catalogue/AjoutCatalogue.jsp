<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Gestion Catalogue</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />
	<a href="/Modularis/Catalogue" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>
		Retour</a>
	<div role="main" class="container">

		<br><br>
		<div class="card card-edition">

			<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
				<table id="Edition" class="table table-edition table-striped table-bordered" cellspacing="0px"
					style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important; text-align: center;"
					column-defs='[{"data": "id","title": "","type": "hidden","visible": false},{"data":"valeur","title": "Catalogue", "required": "true"},{"data":"year","title": "Ann&eacute;e","type": "select","options" : [<c:forEach var="year" items="${years}" end = "6">"${fn:escapeXml(year)}",</c:forEach>"${fn:escapeXml(year[6])}"]}]'
					data-set='[<c:forEach var="Catalogue" items="${ListCatalogue}">{"id":"${fn:escapeXml(Catalogue.catalogueId)}","valeur":"${fn:escapeXml(Catalogue.catalogueNom)}","year":"${fn:escapeXml(Catalogue.annee)}"},</c:forEach>]'>
				</table>
			</div>
		</div>
		<br>
		<div id="new_table_paginate" class="d-flex justify-content-center"></div>

		<div class="row">
			<div class="col-xs-6 col-sm-4">
				<form method="post" class="form-inline">
					<div class="form-group">
						<input id="catalogueNom" class="form-control" name="catalogueNom" placeholder="Nouveau Catalogue"
							required  autofocus/>
						<select class="form-control" name="catalogueAnnee" required>
							<c:forEach var="year" items="${years}">
								<option value="${fn:escapeXml(year)}">${fn:escapeXml(year)}</option>
							</c:forEach>
						</select>							
						<button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
					</div>
				</form>
			</div>
		</div>

	</div>

	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>

</html>