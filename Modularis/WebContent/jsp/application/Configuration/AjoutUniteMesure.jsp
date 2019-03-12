<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <title>Edition Unite Mesure</title>
  <jsp:include page="/jsp/common/defaultHeadLinks.jsp" />

</head>

<body>

  <jsp:include page="/jsp/common/navbar.jsp" />

  <div role="main" class="container">
  
<br>
<div class="card">
	<div class="card-header">
		<input id="search_table" type="text" class="form-control card-search" placeholder="Rechercher..." />
	</div>
	<div class="card-body" style="overflow-x: scroll; overflow:auto;padding:0;">
		<table id="Edition" class="table table-striped table-bordered"cellspacing="0px" style="width:100%;overflow-y:auto; margin-top:-1px!important ;margin-bottom:0!important; ">
			<thead>
				<tr>
			  		<th>Uniter Mesure</th>
			  		<th>Editer</th>
			  		<th>Supprimer</th>
			  	</tr>
			</thead>
			<tbody>
				<c:forEach var="FamilleComposant" items="${ListFamilleComposant}">
					<tr>
				    	<td id="${FamilleComposant.familleComposantId }"> ${fn:escapeXml(FamilleComposant.nom) } </td>
				        <td><button class="material-icons-btn material-icons material-icons-airy">edit</button></td>
				        <td><button class="material-icons-btn material-icons material-icons-airy">delete</button></td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>	
<br>			
<div id="new_table_paginate" class="d-flex justify-content-center"></div>	
  
  
    <form method="post" class="form-inline mt-3">
      <div class="form-group">
        <input id="familleComposantNom" class="form-control" name="familleComposantNom" placeholder="Nouvel Famille Composant" required />
        <button class="btn material-icons material-icons-btn material-icons-btn-add ml-2">add_circle</button>
      </div>
    </form>
  </div>

  <jsp:include page="/jsp/common/defaultScripts.jsp" />
  <jsp:include page="/jsp/common/confirmModal.jsp" />
</body>

</html>