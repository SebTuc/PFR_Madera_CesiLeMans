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

/**
 * Servlet implementation class ListUtilisateur
 */
@WebServlet("/ListUtilisateur")
public class ListUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UtilisateurService utilisateurService = new UtilisateurService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur)session.getAttribute("SessionUtilisateur");
		utilisateur = utilisateurService.getUtilisateurById(utilisateur.getUtilisateurId());
		if(utilisateur != null) {			
			if (!utilisateur.getMetier().getNom().equals("Moderateur")) {
				response.sendRedirect(request.getContextPath()+"/Annuaire");
				return;
			}
		}
		List<Utilisateur> ListUtilisateur = utilisateurService.getAllUtilisateurs();
		request.setAttribute("ListUtilisateur", ListUtilisateur);
		request.getRequestDispatcher("/jsp/application/Annuaire/ListUtilisateur.jsp").forward(request, response);
	}


}
