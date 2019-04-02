package com.ril.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Catalogue;
import com.ril.model.Projet;
import com.ril.model.Utilisateur;
import com.ril.service.CatalogueService;
import com.ril.service.ProjetService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class ListComposant
 */
@WebServlet("/ListCatalogueProjet")
public class ListCatalogueProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CatalogueService catalogueService = new CatalogueService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("SessionUtilisateur"));
		}
		String idCatalogue = request.getParameter("idCatalogue");
		
		if(idCatalogue != "" && MethodeUtile.isInteger(idCatalogue)) {
			Catalogue parentCatalogue = catalogueService.getCatalogueById(Integer.parseInt(idCatalogue));
			List<Projet> listProjet = parentCatalogue.getProjets().stream().collect(Collectors.toList());
			
			if(listProjet != null) {				
				request.setAttribute("Catalogue", parentCatalogue);
				request.setAttribute("ListProjet", listProjet);
				request.getRequestDispatcher("/jsp/application/Catalogue/ListCatalogueProjet.jsp").forward(request, response);
			}else {
				response.sendRedirect("/Modularis/Catalogue/ListCatalogue");
			}
			
		}else {			
			response.sendRedirect("/Modularis/Catalogue/ListCatalogue");
		}
		
	}
						
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("SessionUtilisateur"));
		}
	}	
}
