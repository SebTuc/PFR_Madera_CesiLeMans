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
			Utilisateur utilisateur = (Utilisateur)session.getAttribute("SessionUtilisateur");
			if(utilisateur != null) {			
				if (!utilisateur.getMetier().getNom().equals("Moderateur")) {
					response.sendRedirect(request.getContextPath()+"/Annuaire");
					return;
				}
			}

			request.setAttribute("Utilisateur", utilisateur);
		}
		List<Utilisateur> ListUtilisateur = utilisateurService.getAllUtilisateurs();
		request.setAttribute("ListUtilisateur", ListUtilisateur);
		request.getRequestDispatcher("/jsp/application/Annuaire/ListUtilisateur.jsp").forward(request, response);
	}


}
