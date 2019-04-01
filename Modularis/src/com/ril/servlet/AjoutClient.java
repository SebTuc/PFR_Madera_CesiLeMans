package com.ril.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.DonneesPersonelle;
import com.ril.model.Utilisateur;
import com.ril.service.ClientService;
import com.ril.service.DonneesPersonelleService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutClient
 */
@WebServlet("/AjoutClient")
public class AjoutClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ClientService clientService = new ClientService();
    private DonneesPersonelleService donneeService = new DonneesPersonelleService(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		request.getRequestDispatcher("/jsp/application/Annuaire/AjoutClient.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");	
		String codePostal = request.getParameter("codePostal");
		String telephone = request.getParameter("telephone");	
		String email = request.getParameter("email");
		
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		//rajouter verife du reste
		if (nom != null && prenom != null) {						
			if (!(nom.equals("")) && !(prenom.equals("")) ) {	
				if(telephone == null)telephone = "";
				if(adresse == null)adresse = "";
				if(email == null)email = "";
				if(codePostal == null)codePostal = "";
				
				DonneesPersonelle donneePerso = donneeService.getDonneesPersonelleById(donneeService.addDonneesPersonelle(nom, prenom, email, adresse, telephone, codePostal));
					
					clientService.addClient(donneePerso);

					//Definit la reponse comme "See Other" et redirige
					//Evite la multi-insertion après un refresh de l'utilsateur		
					response.sendRedirect("/Modularis/Annuaire/ListClient");
					
			}else {
				request.setAttribute("Erreur", "Valeur saisie erronée.");
				doGet(request, response);
			}
		}else {
			request.setAttribute("Erreur", "Valeur saisie erronée.");
			doGet(request, response);
		}
	}

}
