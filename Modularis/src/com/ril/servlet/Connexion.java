package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Utilisateur;
import com.ril.service.UtilisateurService;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//3 mois
	private static final Integer TIME_EXPIRE_COOKIE = 90*(24*(60*60));
	//1 jour
	private static final Integer TIME_EXPIRE_SESSION = 1*(24*(60*60));

	private UtilisateurService user = new UtilisateurService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/jsp/application/Compte/Connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Utilisateur> ListUser = user.getAllUtilisateurs();

		String login = request.getParameter("login");
		String pw	= request.getParameter("password");
		String addToCookie = request.getParameter("addToCookie");

		String valeurRetour = "";
		if(ListUser != null) {
			if(!login.equals("") && login != null) {
				if(!pw.equals("") && pw != null) {
					for(Utilisateur utilisateur : ListUser) {
						if(login.equals(utilisateur.getLogin())){
							if(pw.equals(utilisateur.getPassword())){
								
								HttpSession session = request.getSession();
								session.setAttribute("SessionUtilisateur", utilisateur);
								//Session expire après 24H / 1J
								session.setMaxInactiveInterval(TIME_EXPIRE_SESSION);
								//Only String in Cookie donc on envoie l'utilisateur ID
								if(addToCookie != null) {
									//Camoufler la data pour quelle ne sois pas lu par un tiers!
									String structuredDataForCookie = utilisateur.getLogin()+"."+ utilisateur.getPassword() +"."+utilisateur.getUtilisateurId().toString();  
									Cookie cookieUtilisateur = new Cookie("UtlisateurLoginPasswordId",structuredDataForCookie);
									
									//Cookie expire après 24H / 1J
									cookieUtilisateur.setMaxAge(TIME_EXPIRE_COOKIE);
									response.addCookie(cookieUtilisateur);
								}
								
								response.sendRedirect("/Modularis/Index");
																
							}else {
								valeurRetour = "Les identifiants pour cette utilisateur sont incorrect.";
							}
						}else{
							valeurRetour = "Les identifiants pour cette utilisateur sont incorrect.";
						}
					}
				}else{
					valeurRetour = "Veuillez saisir un login.";
				}
			}else {
				valeurRetour = "Veuillez saisir un mot de passe.";
			}
		}else {
			valeurRetour = "Contacter le support, aucun compte existant.";
		}
		//Creer une session connecter et empecher l'ouverture des autre page si pas connecter /!\
		request.setAttribute("Erreur", valeurRetour);

		doGet(request, response);
	}
}
