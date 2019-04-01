package com.ril.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Utilisateur;
import com.ril.service.FournisseurService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutFournisseur
 */
@WebServlet("/AjoutFournisseur")
public class AjoutFournisseur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FournisseurService fournisseurService = new FournisseurService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		request.getRequestDispatcher("/jsp/application/Annuaire/AjoutFournisseur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");	
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
		if (nom != null && adresse != null && codePostal != null) {						
			if (nom.trim() != null) {	
				if(telephone == null) {
					telephone = "";
				}
				if(email == null) {
					email = "";
				}
				
				fournisseurService.addFournisseur(nom, adresse, codePostal, telephone, email);

				//Definit la reponse comme "See Other" et redirige
				//Evite la multi-insertion apr�s un refresh de l'utilsateur		
				response.sendRedirect("/Modularis/Annuaire/ListFournisseur");
			}
		}
	}

}
