<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Configuration</title>
	<jsp:include page="/jsp/common/defaultHeadLinks.jsp" />
</head>
<body>
	<jsp:include page="/jsp/common/navbar.jsp" />
	<a href="/Modularis" class="btn btn-outline-dark return-btn"><span aria-hidden="true">&larr;</span> Retour</a>
	<div role="main" class="container">
	<div class="col-lg-12 text-center">
        	<h1>Configuration</h1>
        	<br />
      	</div>
      	<p class="header-2" data-toggle="collapse" href="#collapseressources" role="button" aria-expanded="false" aria-controls="collapseressources">Édition des ressources basiques</p>
	     <div class="collapse" id="collapseressources">
		     <div class="row">
				<div class="col-sm-6 col-md-4">
					<button onclick="window.location.href = 'Configuration/AjoutGamme';" class="menu-link"><i class="material-icons" style="float:left">extension</i>Gammes</button>
				</div>
				<div class="col-sm-6 col-md-4">
					<button onclick="window.location.href = 'Configuration/AjoutFamilleComposant';" class="menu-link"><i class="material-icons" style="float:left">group_work</i>Famille de composant</button>
				</div>
				<div class="col-sm-6 col-md-4">
					<button onclick="window.location.href = 'Configuration/AjoutMetier';" class="menu-link"><i class="material-icons" style="float:left">work</i> Metiers</button>
				</div>
				<div class="col-sm-6 col-md-4">
					<button onclick="window.location.href = 'Configuration/AjoutUniteMesure';" class="menu-link"><i class="material-icons" style="float:left">timeline</i>Unite de mesure</button>
				</div>
				<div class="col-sm-6 col-md-4">
					<button onclick="window.location.href = 'Configuration/AjoutEntrepot';" class="menu-link"><i class="material-icons" style="float:left">home</i>Entrepots</button>
				</div>
				<div class="col-sm-6 col-md-4">
					<button onclick="window.location.href = 'Configuration/AjoutMateriaux';" class="menu-link"><i class="material-icons" style="float:left">style</i>Materiaux</button>
				</div>
				<div class="col-sm-6 col-md-4">
					<button onclick="window.location.href = 'Configuration/AjoutAngle';" class="menu-link"><i class="material-icons" style="float:left">transform</i>Type Angle</button>
				</div>
			</div>
		</div>
		<br>
		<p class="header-2" data-toggle="collapse" href="#collapsemodules" role="button" aria-expanded="false" aria-controls="collapsemodules">Édition des composants/modules</p>
		<div class="collapse" id="collapsemodules">
			<div class="row">
				<div class="col-sm-6 col-md-4">
					<button onclick="window.location.href = 'Configuration/ListComposant';" class="menu-link"><i class="material-icons" style="float:left">dashboard</i>Liste des composants</button>
				</div>
				<div class="col-sm-6 col-md-4">
					<button onclick="window.location.href = 'Configuration/ListModule';" class="menu-link"><i class="material-icons" style="float:left">extension</i>Liste des modules</button>
				</div>
	    	</div>
    	</div>
     </div>
	<jsp:include page="/jsp/common/defaultScripts.jsp" />
</body>
</html>