package com.ril.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Devis;
import com.ril.model.Utilisateur;
import com.ril.service.DevisService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class ListDevis
 */
@WebServlet("/ListDevis")
public class ListDevis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DevisService devisService = new DevisService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		List<Devis> ListDev = devisService.getAllDeviss();
		//afficher que les devis en brouillon
		List<Devis> ListDevis = new ArrayList<Devis>();
		if(ListDev!=null) {
			for(Devis devis : ListDev) {
				if(devis.getEtat().getNom().equals("Brouillon")) {
					ListDevis.add(devis);
				}
			}
		}
		
		if(ListDevis.size() == 0) {
			
			request.setAttribute("isEmptyList", true);
		}else {
			request.setAttribute("isEmptyList", false);
		}
		request.setAttribute("ListDevis", ListDevis);
		request.getRequestDispatcher("/jsp/application/DevisProjetFacture/ListDevis.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String devisId = request.getParameter("radio");
		String btnVisualiser = request.getParameter("btnVisualiser");
		String btnSupprimer = request.getParameter("btnSupprimer");
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		

		if( btnVisualiser != null && devisId != null) {
			if(MethodeUtile.isInteger(devisId)) {
			response.sendRedirect(request.getContextPath()+ "/DevisFacture/DetailDevis?id="+devisId);
			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}
			
		}else if( btnSupprimer != null && devisId != null) {
			if(MethodeUtile.isInteger(devisId)) {

				devisService.removeDevisById(Integer.valueOf(devisId));
				
				doGet(request, response);
			}else {
				request.setAttribute("Erreur", "Devis ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}
			
			
		}else {
			request.setAttribute("Erreur", "Veuillez saisir un projet.");
			doGet(request, response);
			
		}
	}

}
