package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Composant;
import com.ril.model.Fournisseur;
import com.ril.model.Utilisateur;
import com.ril.service.UtilisateurService;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connexion() {
		super();
		// TODO Auto-generated constructor stub
	}
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

		String valeurRetour = "";
		if(!login.equals("") && login != null) {
			if(!pw.equals("") && pw != null) {
				for(Utilisateur utilisateur : ListUser) {
					if(login.equals(utilisateur.getLogin())){
						if(pw.equals(utilisateur.getPassword())){
							response.sendRedirect("/Modularis");
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

		//Creer une session connecter et empecher l'ouverture des autre page si pas connecter /!\
		request.setAttribute("Erreur", valeurRetour);
		
		doGet(request, response);
	}
}
