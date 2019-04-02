<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- SIDEBAR -->
<div id="mySidenav" class="sidenav">
  <a class ="closebtn" id="close-btn">&times;</a>
  <a href="/Modularis/Configuration">Configuration</a>
  <a href="/Modularis/Annuaire">Annuaire</a>
  <a href="/Modularis/DevisFacture">Devis/Facture</a>
  <a href="/Modularis/Catalogue">Catalogue</a>
  <a href="/Modularis/GestionStock/ListEntrepot">Stock</a>
</div>

<!-- NAVBAR -->
<nav class="navbar navbar-expand navbar-dark navbar-modularis">
	<button id="open-btn" class="btn-menu">
		<span class="navbar-toggler-icon"></span>
	</button>
	<a class="navbar-brand navbar-logo" href="/Modularis">MODULARIS</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	
	
	<span class="navbar-text ml-auto" style="color:#fff" >${fn:escapeXml(Utilisateur.donneesPersonelle.nom)}  ${fn:escapeXml(Utilisateur.donneesPersonelle.prenom)}  |</span>
	
	<a href="/Modularis/Deconnexion" class="btn btn-connect">Deconnexion</a>
	
</nav>
