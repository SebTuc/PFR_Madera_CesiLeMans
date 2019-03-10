<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- SIDEBAR -->
<div id="mySidenav" class="sidenav">
  <a class ="closebtn" id="close-btn">&times;</a>
  <a href="#">About</a>
  <a href="#">Services</a>
  <a href="#">Clients</a>
  <a href="#">Contact</a>
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
	
	<!-- Button Connexion -->
	<a href="#" class="btn btn-connect ml-auto" data-toggle="modal" data-target="#sem-login"><span class="glyphicon glyphicon-lock"></span> Connexion</a>
	
	<!-- Menu utilisateur connect� -->
<!--     <div class="collapse navbar-collapse pull-right ml-auto" id="bs-example-navbar-collapse-1"> -->
    
<!--       <ul class="nav navbar-nav navbar-left"> -->
<!--           <li class="dropdown"> -->
<!--        		<button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> -->
<!-- 				<i class="material-icons">account_circle</i> -->
<!-- 			</button> -->
<!--           	<ul class="dropdown-menu"> -->
<!--             	<li align="center" class="well"> -->
<!--                 	<a href="#" class="btn btn-sm btn-default dropdown-item"><span class="glyphicon glyphicon-lock"></span> Deconnexion</a> -->
<!--             	</li> -->
<!--            </ul> -->
<!--         </li> -->
<!--       </ul> -->
<!--     </div> -->
    
</nav>

<!-- MODAL CONNEXION-->
  <div class="modal fade seminor-login-modal" data-backdrop="static" id="sem-login">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">

        <!-- Corps Modal -->
		<div class="modal-body seminor-login-modal-body">
      		<h5 class="modal-title text-center">CONNEXION &Agrave; VOTRE COMPTE</h5>
         	<button type="button" class="close" data-dismiss="modal">
             	<span><i class="material-icons">close</i></span>
       		</button>


		 	<form class="seminor-login-form">
	      		<div class="form-group">
	        		<input type="email" class="form-control" required autocomplete="off">
	        		<label class="form-control-placeholder" for="name">Adresse email</label>
	      		</div>
	      		<div class="form-group">
			        <input type="password" class="form-control" required autocomplete="off">
		        	<label class="form-control-placeholder" for="password">Mot de passe</label>
		      	</div>

		        <div class="btn-check-log">
		            <button type="submit" class="btn-check-login">CONNEXION</button>
		        </div>

	      	</form>

        </div>
      </div>
    </div>
  </div>