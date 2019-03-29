<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Edition Catalogue</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />
	<link href="<%=request.getContextPath()%>/resources/css/jstree.min.css" rel="stylesheet">
</head>

<body>

	<jsp:include page="/jsp/common/navbar.jsp" />
	<a href="/Modularis/Catalogue" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span>
		Retour</a>
	<div role="main" class="container-fluid p2">

		<br><br>
		<div class="">

			<div class="">				
				<div class="d-xs-inline-flex d-md-flex flex-row justify-content-between align-items-center mb-3">
					<div class="p-2 tree-container align-self-start bd-highlight" id='jqxProjects'> </div>

					<div class="p-2 flex-shrink-1 bd-highlight">
						<button class="material-icons material-icons-button add-project" id='Add'
							disabled>forward</button>
					</div>

					<div class="p-2 tree-container align-self-start bd-highlight" id='jqxCatalogs'> </div>
				</div>

			</div>
		</div>

	</div>

	<jsp:include page="/jsp/common/defaultScripts.jsp" />
	<jsp:include page="/jsp/common/jsTreeScripts.jsp" />
	<script type="text/javascript">
		$(document).ready(function () {
			/* Recuperation des donnees permettant de generer les trees */
			var dataItemList = ${JsonProjet};
			var dataCategoryTree = ${JsonCatalogue};

		loadCategorizedTrees('jqxProjects', dataItemList, 'jqxCatalogs', dataCategoryTree, 'Add')
	});
	</script>
</body>

</html>