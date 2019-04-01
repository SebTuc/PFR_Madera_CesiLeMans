/*Fichier contenant toutes les interactions dynamiques du site ou futur code liaison avec le back en JEE (Eventuellement de l'AJAX pour la recherche,...)*/

/*Init du menu*/
$(document).ready(function () {
	$("#open-btn").click(e => { $("#mySidenav").css('width', '250px') });
	$("#close-btn").click(e => { $("#mySidenav").css('width', '0px') });

	
	//BtnSupprimer confirmation 
	
//	$("#btnSupprimer").click(function(event){
//		
//		
//		event.preventDefault();
//		var $form = $( this ),
//		 url = $form.attr( 'action' );
//		$("#ModalConfirmationSuppresion").show();
//		$("#btnModalSupprNon").click(function(){
//			
//			$("#ModalConfirmationSuppresion").hide();
//			
//		});
//		$("#btnModalSupprOui").click(function(){
//			
//			
//	       
//			var radioValue = $("input[name='radio']:checked").val();
//			if(radioValue != null){
//				/* Send the data using post with element id name and name2*/
//				$.ajax({
//					  type: "POST",
//					  data: "radio="+radioValue,
//					  async:false
//				});
//			}
//			
//		});
//		
//	});
	
	
	loadAltDataTable("Edition");
	

});


/**
 * Initialise une DataTable modifiable
 * @param {String} idTable ID de l'element <table> à initialiser
 */
function loadAltDataTable(idTable) {
	var tableHtml, dataSet, columnDefs;

	tableHtml = $('#' + idTable);

	// Recuperation des donnees envoyer par le serveur au format parsable en JSON
	dataSet = $(tableHtml).attr("data-set");

	if (dataSet.length > 2) {

		// Supprime la derniere virgule et ajoute la fin du JSON puis parse
		dataSet = dataSet.substring(0, dataSet.length - 2);
		dataSet += "]";
		dataSet = JSON.parse(dataSet);
	} else {
		dataSet = null;
	}
	
	// Recuperation des definition de columns (nom de colonnes du tableau) au format parsable en JSON
	columnDefs = $(tableHtml).attr("column-defs");

	// Verification et parse des valeurs en JSON
	if (columnDefs.length > 1) { columnDefs = JSON.parse(columnDefs); }

	var myTable = $(tableHtml).DataTable({
		data: dataSet,							// Definition des donnees a afficher
		columns: columnDefs,					// Definition des nom de colonnes
		dom: 'Bfrtip',        					// Needs button container
		select: 'single',
		responsive: true,
		altEditor: true,     					// Active altEditor permettant l'affichage des modals de crud
		language: {
			"decimal": "",
			"emptyTable": "Aucune donn&eacute;e disponible dans ce tableau",
			"info": "Affichage de la page _START_ sur _END_ de _TOTAL_ lignes",
			"infoEmpty": "Affichage de 0 lignes",
			"infoFiltered": "(Filtrer de _MAX_ lignes au total)",
			"infoPostFix": "",
			"thousands": ",",
			"lengthMenu": "Affichage de _MENU_ lignes",
			"loadingRecords": "Chargement...",
			"processing": "En cours...",
			"search": "Rechercher: ",
			"zeroRecords": "Aucune correspondance trouv&eacute;e",
			"select": {
				"rows": {
					_: "%d lignes selectionn&eacute;s",
					0: "Cliquez sur une ligne pour la selectionner",
					1: "1 ligne selectionn&eacute;"
				}
			},
			"paginate": {
				"previous": "<i class=\"material-icons\">keyboard_arrow_left</i>",
				"next": "<i class=\"material-icons\">keyboard_arrow_right</i>"
			}
		},
		buttons: [
			{
				extend: 'selected', 			// Definit que l'action s'execute sur la ligne selectionné
				text: 'Edition ligne s&eacute;lectionn&eacute;e',
				name: 'edit',        			// Ne pas changer le nom

			},
			{
				extend: 'selected', 			// Definit que l'action s'execute sur la ligne selectionné
				text: 'Suppression ligne s&eacute;lectionn&eacute;e',
				name: 'delete',     				// Ne pas changer le nom
			}],
		onDeleteRow: function (datatable, rowdata, success, error) {
			var request = formatAjaxRequest(rowdata);
			request += "&action=Delete";
			$.ajax({
				url: "",
				type: 'POST',
				data: request, 					// Données à transmettre
				success: success,
				error: error
			});
		},
		onEditRow: function (datatable, rowdata, success, error) {
			var request = formatAjaxRequest(rowdata);
			request += "&action=Edition";
			$.ajax({
				url: "",
				type: 'POST',
				data: request, 					// Données à transmettre
				dataType: 'json',
				success: success,
				error: error
			});
		}
	});
}


/**
 * Transforme des donnees JSON en une suite de parametre lisible par
 *  le serveur via une methode GET/POST
 * @param {JSON} rowdata Donnees a prendre en compte pour le formatage
 */
function formatAjaxRequest(rowJsonData) {
	let request = "";
	let rowData = Object.entries(rowJsonData)
	let rowDataLength = rowData.length;

	try {
		if (rowDataLength !== 0) {
			if (rowDataLength > 1) {
				for (var i = 0; i < rowDataLength - 1; i++) {
					request += rowData[i][0] + "=" + encodeURIComponent(rowData[i][1]) + "&";
				}
			}

			request += rowData[rowDataLength - 1][0] + "=" + encodeURIComponent(rowData[rowDataLength - 1][1]);
			return request;

		} else {
			throw "aucune valeur donnée";
		}
	} catch (error) {
		console.error("Echec de la requete : " + error);
	}
}


/**
 * Initialise le lancement des modal de confirmation
 */
function initConfimModal() {
	let domElements;
	domElements = $('[confirm-modal]');
	if (domElements.length > 0) {
		$(domElements).each((id, element) => {
			$(element).on("click", launchConfimModal);
		});
	}
}

/**
 * Modifie la modal de confirmation avant sont lancement
 * @param {HTMLElement} element Element du DOM ayant appelé la methode
 */
function launchConfimModal(element) {
	let title, buttonName, methodForm, actionForm, paramString, params;
	title = $(element.target).attr("confirm-modal-title");
	buttonName = $(element.target).attr("confirm-modal-button");
	actionForm = $(element.target).attr("confirm-modal-action");
	methodForm = $(element.target).attr("confirm-modal-method");
	paramString = $(element.target).attr("confirm-modal-param");

	title.length > 0 ? $("#confirm-modal-title").html(title) : $("#confirm-modal-title").html("Confirmation");
	buttonName.length > 0 ? $("#confirm-modal-button").html(buttonName) : $("#confirm-modal-button").html("Confirmation");
	actionForm.length > 0 ? $("#confirm-modal-form").attr("action", actionForm) : $("#confirm-modal-form").attr("action", '');

	if (methodForm.length > 0 && (methodForm.toUpperCase() === "POST" || methodForm.toUpperCase() === "GET")) {
		$("#confirm-modal-form").attr("method", methodForm.toUpperCase())
	} else {
		$("#confirm-modal-form").attr("method", 'get');
	}

	if (paramString.length > 0) {
		try {

			// Parsing d'un objet JSON simple type {"name": "name", "value": "value"}
			params = JSON.parse(paramString);

			let confirmModalInputs = $("#confirm-modal-inputs");

			$.each(params, function (i, param) {
				confirmModalInputs.append('<input type="hidden" name="' + param.name + '" value="' + param.value + '">');
			});

		} catch (error) {
			console.log(error);
		}
	}

}