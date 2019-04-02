package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.DonneesPersonelle;
import com.ril.model.Metier;
import com.ril.model.Utilisateur;
import com.ril.service.DonneesPersonelleService;
import com.ril.service.MetierService;
import com.ril.service.UtilisateurService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutUtilisateur
 */
@WebServlet("/AjoutUtilisateur")
public class AjoutUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UtilisateurService utilisateurService = new UtilisateurService();
	private DonneesPersonelleService donneeService = new DonneesPersonelleService(); 
	private MetierService metierService = new MetierService(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		List<Metier> ListMetier = metierService.getAllMetiers();
		request.setAttribute("ListMetier", ListMetier);
		request.getRequestDispatcher("/jsp/application/Annuaire/AjoutUtilisateur.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");	
		String codePostal = request.getParameter("codePostal");
		String telephone = request.getParameter("telephone");	
		String email = request.getParameter("email");
		String metier = request.getParameter("metier");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		//rajouter verife du reste
		if (nom != null) {						
			if (nom.trim() != null) {	
				if(telephone == null)telephone = "";
				if(adresse == null)adresse = "";
				if(email == null)email = "";
				if(codePostal == null)codePostal = "";
				
				if(!password.equals(confirmPassword)) {
					
					//retour sur la meme page mais remettre les valeur saisie et signaler que la saisie est incorrect
					request.setAttribute("Erreur", "Le mot de passe ne correspont pas.");
					doGet(request, response);
					
				}else {
					
					DonneesPersonelle donneePerso = donneeService.getDonneesPersonelleById(donneeService.addDonneesPersonelle(nom, prenom, email, adresse, telephone, codePostal));
					
					Metier boulot = metierService.getMetierById(Integer.valueOf(metier));
					
//					utilisateurService.addUtilisateur(donneePerso, boulot, login, password);

					//Definit la reponse comme "See Other" et redirige
					//Evite la multi-insertion après un refresh de l'utilsateur		
					response.sendRedirect("/Modularis/Annuaire/ListUtilisateur");
					
				}
			}
		}
	}

}
