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
import com.ril.model.Devis;
import com.ril.service.ClientService;
import com.ril.service.DevisService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class ListDevis
 */
@WebServlet("/ListDevis")
public class ListDevis extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DevisService devisService = new DevisService();
	private ClientService clientService = new ClientService();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Devis> ListDev = devisService.getAllDeviss();
		//afficher que les devis en brouillon
		//Trier par date :
		List<Devis> ListDevis = new ArrayList<Devis>();
		if(ListDev!=null) {
			for(Devis devis : ListDev) {
				if(devis.getEtat().getNom().equals("Brouillon")) {
					ListDevis.add(devis);
				}
			}
		}
		//sorted by date creation
		ListDevis = ListDevis.stream().sorted(Comparator.comparing(Devis::getDateCreation).reversed()).collect(Collectors.toList());
		
		String clientId = request.getParameter("clientId");
		if(clientId != null) {
			if(!clientId.equals("")) {
				if(MethodeUtile.isInteger(clientId)) {
					//filter par client id
					ListDevis = ListDevis.stream().filter(d -> d.getClient().getClientId() == Integer.valueOf(clientId)).collect(Collectors.toList());
					request.setAttribute("clientId", clientId);
				}else {
					request.setAttribute("Erreur", "Veuillez saisir un client.");
				}
			}else {
				request.setAttribute("Erreur", "Veuillez saisir un client.");
			}
		}
		if(ListDevis.size() == 0) {

			request.setAttribute("isEmptyList", true);
		}else {
			request.setAttribute("isEmptyList", false);
		}
		
		List<Client> ListClient = clientService.getAllClients();
		
		request.setAttribute("ListClient", ListClient);
		request.setAttribute("ListDevis", ListDevis);
		request.getRequestDispatcher("/jsp/application/DevisProjetFacture/ListDevis.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String devisId = request.getParameter("radio");
		String btnVisualiser = request.getParameter("btnVisualiser");
		String btnSupprimer = request.getParameter("btnSupprimer");

		if( btnVisualiser != null && devisId != null) {
			if(MethodeUtile.isInteger(devisId)) {
				response.sendRedirect(request.getContextPath()+ "/DevisFacture/DetailDevis?id="+devisId);
			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}

		}else if( btnSupprimer != null && devisId != null) {
			if(MethodeUtile.isInteger(devisId)) {

				devisService.removeDevisById(Integer.valueOf(devisId));

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
