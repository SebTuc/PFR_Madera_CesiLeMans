/*Fichier contenant toutes les interactions dynamiques du site ou futur code liaison avec le back en JEE (Eventuellement de l'AJAX pour la recherche,...)*/
 
/*Init du menu*/
$(document).ready ( function () {
    $("#open-btn").click(e => {$("#mySidenav").css('width','250px')});
  	$("#close-btn").click(e =>{$("#mySidenav").css('width','0px')});
});