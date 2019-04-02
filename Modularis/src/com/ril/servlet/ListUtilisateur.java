package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Utilisateur;
import com.ril.service.UtilisateurService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class ListUtilisateur
 */
@WebServlet("/ListUtilisateur")
public class ListUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public UtilisateurService utilisateurService = new UtilisateurService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			Utilisateur utilisateur = (Utilisateur)session.getAttribute("Utilisateur");
			if (utilisateur.getMetier().getNom() != "admin")
				return;
			request.setAttribute("Utilisateur", utilisateur);
		}
		List<Utilisateur> ListUtilisateur = utilisateurService.getAllUtilisateurs();
		request.setAttribute("ListUtilisateur", ListUtilisateur);
		request.getRequestDispatcher("/jsp/application/Annuaire/ListUtilisateur.jsp").forward(request, response);
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idValeur = request.getParameter("id");
		String nomUtilisateur = request.getParameter("nomUtilisateur");
		String prenomUtilisateur = request.getParameter("prenomUtilisateur");
		
		String codePostalUtilisateur = request.getParameter("codePostalUtilisateur");
		String adresseUtilisateur = request.getParameter("adresseUtilisateur");
		String telephoneUtilisateur = request.getParameter("telephoneUtilisateur");
		String emailUtilisateur = request.getParameter("emailUtilisateur");
		
		String loginUtilisateur = request.getParameter("loginUtilisateur");
		String passwordUtilisateur = request.getParameter("passwordUtilisateur");
		String action = request.getParameter("action");
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		if(action != null) {
			if(action.equals("Delete")) {
				
				utilisateurService.removeUtilisateurById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				Utilisateur utilisateur = utilisateurService.getUtilisateurById(Integer.valueOf(idValeur));

				//pour le reste c'est une erreur ! 
				if(codePostalUtilisateur == null) codePostalUtilisateur = "";
				if(telephoneUtilisateur == null) telephoneUtilisateur = "";
				if(emailUtilisateur == null) emailUtilisateur = "";
				
				utilisateur.getDonneesPersonelle().setNom(nomUtilisateur);
				utilisateur.getDonneesPersonelle().setPrenom(prenomUtilisateur);
				utilisateur.getDonneesPersonelle().setCodePostal(codePostalUtilisateur);
				utilisateur.getDonneesPersonelle().setTelephone(telephoneUtilisateur);
				utilisateur.getDonneesPersonelle().setEmail(emailUtilisateur);
				utilisateur.getDonneesPersonelle().setAdresse(adresseUtilisateur);
				utilisateur.setMetier(utilisateur.getMetier());
				utilisateur.setLogin(loginUtilisateur);
				utilisateur.setPassword(passwordUtilisateur);

				utilisateurService.editUtilisateur(utilisateur);;
				
				// Retour de l'objet modifier sous format json
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print("{ \"id\": \""+utilisateur.getUtilisateurId()+"\""
						+ ", \"nomUtilisateur\": \""+utilisateur.getDonneesPersonelle().getNom()+"\""
								+ ", \"prenomUtilisateur\": \""+utilisateur.getDonneesPersonelle().getPrenom()+"\""
										+ ", \"adresseUtilisateur\": \""+utilisateur.getDonneesPersonelle().getAdresse()+"\""
											+ ", \"codePostalUtilisateur\": \""+utilisateur.getDonneesPersonelle().getCodePostal()+"\""
													+ ", \"telephoneUtilisateur\": \""+utilisateur.getDonneesPersonelle().getTelephone()+"\""
															+ ", \"emailUtilisateur\": \""+utilisateur.getDonneesPersonelle().getEmail()+"\""
																	+ ", \"metierUtilisateur\": \""+utilisateur.getMetier().getNom()+"\""
																			+ ", \"loginUtilisateur\": \""+utilisateur.getLogin()+"\""
																					+ ", \"passwordUtilisateur\": \""+utilisateur.getPassword()+"\" }");
				response.getWriter().flush();
				
			}
		}
	}

}
