$(document).ready(function () {
	var doc = new jsPDF();
	var specialElementHandlers = {
	    '#editor': function (element, renderer) {
	        return true;
	    }
	};
	
	//pour le nom du devis recup le nom du client ?
	//Autre methode est de créer un page jsp qui retourne un pdf car sa sa fonctionne pas ouf...
	
	$('#btnPDF').click(function () {   
	    doc.fromHTML($('#toPDF').html(), 15, 15, {
	        'width': $('#toPDF').offsetHeight,
	            'elementHandlers': specialElementHandlers
	    });
	    doc.save('devis.pdf');
	});
});