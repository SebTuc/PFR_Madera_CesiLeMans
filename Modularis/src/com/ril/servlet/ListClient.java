package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Client;
import com.ril.service.ClientService;

/**
 * Servlet implementation class ListClient
 */
@WebServlet("/ListClient")
public class ListClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientService clientService = new ClientService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Client> ListClient = clientService.getAllClients();
		request.setAttribute("ListClient", ListClient);
		request.getRequestDispatcher("/jsp/application/Annuaire/ListClient.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idValeur = request.getParameter("id");
		String nomClient = request.getParameter("nomClient");
		String prenomClient = request.getParameter("prenomClient");
		String ville = request.getParameter("ville");
		String codePostalClient = request.getParameter("codePostalClient");
		String adresseClient = request.getParameter("adresseClient");
		String telephoneClient = request.getParameter("telephoneClient");
		String emailClient = request.getParameter("emailClient");

		String action = request.getParameter("action");

		if(action != null) {
			if(action.equals("Edition")) {

				Client client = clientService.getClientById(Integer.valueOf(idValeur));

				//pour le reste c'est une erreur ! 
				if(codePostalClient == null) codePostalClient = "";
				if(telephoneClient == null) telephoneClient = "";
				if(emailClient == null) emailClient = "";
				if(ville == null) ville = "";

				client.getDonneesPersonelle().setNom(nomClient);
				client.getDonneesPersonelle().setPrenom(prenomClient);
				client.getDonneesPersonelle().setCodePostal(codePostalClient);
				client.getDonneesPersonelle().setTelephone(telephoneClient);
				client.getDonneesPersonelle().setEmail(emailClient);
				client.getDonneesPersonelle().setAdresse(adresseClient);
				client.getDonneesPersonelle().setVille(ville);

				clientService.editClient(client);

				// Retour de l'objet modifier sous format json
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print("{ \"id\": \""+client.getClientId()+"\""
						+ ", \"nomClient\": \""+client.getDonneesPersonelle().getNom()+"\""
						+ ", \"prenomClient\": \""+client.getDonneesPersonelle().getPrenom()+"\""
						+ ", \"adresseClient\": \""+client.getDonneesPersonelle().getAdresse()+"\""
						+ ", \"ville\": \""+client.getDonneesPersonelle().getVille()+"\""
						+ ", \"codePostalClient\": \""+client.getDonneesPersonelle().getCodePostal()+"\""
						+ ", \"telephoneClient\": \""+client.getDonneesPersonelle().getTelephone()+"\""
						+ ", \"emailClient\": \""+client.getDonneesPersonelle().getEmail()+"\"}");
				response.getWriter().flush();
			}
		}
	}

}
