package com.ril.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Client;
import com.ril.model.Facture;
import com.ril.service.ClientService;
import com.ril.service.FactureService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class ListFacture
 */
@WebServlet("/ListFacture")
public class ListFacture extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private FactureService factureService = new FactureService();
	private ClientService clientService = new ClientService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Facture> ListFact = factureService.getAllFacture();
		//Trier par date
		List<Facture> ListFacture = new ArrayList<Facture>();
		if(ListFact != null) {
			ListFacture = ListFact;
		}
		
		//sorted by date modification
		ListFacture = ListFacture.stream().sorted(Comparator.comparing(Facture::getDateModification).reversed()).collect(Collectors.toList());
		
		
		String clientId = request.getParameter("clientId");
		if(clientId != null) {
			if(!clientId.equals("")) {
				if(MethodeUtile.isInteger(clientId)) {
					//filter par client id
					ListFacture = ListFacture.stream().filter(d -> d.getDevis().getClient().getClientId() == Integer.valueOf(clientId)).collect(Collectors.toList());
					request.setAttribute("clientId", clientId);
				}else {
					request.setAttribute("Erreur", "Veuillez saisir un client.");
				}
			}else {
				request.setAttribute("Erreur", "Veuillez saisir un client.");
			}
		}
		
		if(ListFacture.size() == 0) {

			request.setAttribute("isEmptyList", true);
		}else {
			request.setAttribute("isEmptyList", false);
		}
		
		List<Client> ListClient = clientService.getAllClients();
		
		request.setAttribute("ListClient", ListClient);
		
		request.setAttribute("ListFacture", ListFacture);
		request.getRequestDispatcher("/jsp/application/DevisProjetFacture/ListFacture.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String factureId = request.getParameter("radio");
		String btnVisualiser = request.getParameter("btnVisualiser");
		String btnSupprimer = request.getParameter("btnSupprimer");

		if( btnVisualiser != null && factureId != null) {
			if(MethodeUtile.isInteger(factureId)) {
				response.sendRedirect(request.getContextPath()+ "/DevisFacture/DetailFacture?id="+factureId);
			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}

		}else if( btnSupprimer != null && factureId != null) {
			if(MethodeUtile.isInteger(factureId)) {

				request.setAttribute("Erreur", "A voir se qu'il faut faire.");

				doGet(request, response);
			}else {
				request.setAttribute("Erreur", "Devis ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}


		}else {
			request.setAttribute("Erreur", "Veuillez saisir un projet.");
			doGet(request, response);

		}
	}

}
