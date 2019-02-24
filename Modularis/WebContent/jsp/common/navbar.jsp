<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div id="mySidenav" class="sidenav">
  <a class ="closebtn" id="close-btn">&times;</a>
  <a href="#">About</a>
  <a href="#">Services</a>
  <a href="#">Clients</a>
  <a href="#">Contact</a>
</div>


<nav class="navbar navbar-expand navbar-dark bg-primary">
	<button id="open-btn" class="btn-menu">
		<span class="navbar-toggler-icon"></span>
	</button>
	<a class="navbar-brand navbar-modularis" href="/Modularis">MODULARIS</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExample02">
		<form class="form-inline my-2 my-md-0">
			<input class="form-control" type="text" placeholder="Search">
			<button class="my-2 my-sm-0" type="submit">
				<i class="material-icons">search</i>
			</button>
		</form>
	</div>
	
	 <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse pull-right" id="bs-example-navbar-collapse-1">
    
      <ul class="nav navbar-nav navbar-left">
          <li class="dropdown">
           		<button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			<i class="material-icons">account_circle</i>
		</button>
          <ul class="dropdown-menu">
            <li align="center" class="well">
                <div><img class="img-responsive" style="padding:2%;" src="http://placehold.it/120x120"/><a class="change" href="">Change Picture</a></div>
                <a href="#" class="btn btn-sm btn-default dropdown-item" data-toggle="modal" data-target="#sem-login"><span class="glyphicon glyphicon-lock"></span> Connexion</a>
                <a href="#" class="btn btn-sm btn-default"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
            </li>
           </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
</nav>

<!-- The Modal -->
  <div class="modal fade seminor-login-modal" data-backdrop="static" id="sem-login">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">

        <!-- Modal body -->
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
      <div class="form-group">
        <label class="container-checkbox">
        Se souvenir de moi
        <input type="checkbox" checked="checked" required>
        <span class="checkmark-box"></span>
        </label>
        </div>

        <div class="btn-check-log">
            <button type="submit" class="btn-check-login">CONNEXION</button>
        </div>


       <div class="forgot-pass-fau text-center pt-3">
         <a href="/reset_pass" class="text-secondary">Mot de passe oubli&eacute; ?</a>
       </div>
       <div class="create-new-fau text-center pt-3">
           <a href="#" class="text-primary-fau"><span data-toggle="modal" data-target="#sem-reg" data-dismiss="modal">S'inscrire</span></a>
       </div>



      </form>

        </div>
      </div>
    </div>
  </div>