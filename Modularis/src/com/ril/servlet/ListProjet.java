package com.ril.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Projet;
import com.ril.service.ProjetService;

/**
 * Servlet implementation class ListProjet
 */
@WebServlet("/ListProjet")
public class ListProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProjetService projetService = new ProjetService();
	
	private static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Projet> ListProjet = projetService.getAllProjets();
		List<Projet> List = new ArrayList<Projet>();
		if(ListProjet.size()!=0) {
			for(Projet projet : ListProjet) {
				//On ne prend que les projet qui ne sont pas en devis ou qui ne sont pas dans le catalogue
				if(projet.getCatalogue().size() == 0) {
					if(projet.getDevises().size() == 0) {
						
						List.add(projet);
						
					}
				}
			}
			if(List.size() == 0) {
				request.setAttribute("isEmptyList", true);
			}else {
				request.setAttribute("isEmptyList", false);
			}
			
			request.setAttribute("ListProjet", List);
		}else {
			
			if(ListProjet.size() == 0) {
				request.setAttribute("isEmptyList", true);
			}else {
				request.setAttribute("isEmptyList", false);
			}
			
			request.setAttribute("ListProjet", ListProjet);
		}
		
		
		
		request.getRequestDispatcher("/jsp/application/DevisProjetFacture/ListProjet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projetId = request.getParameter("radio");
		String btnEditer = request.getParameter("btnEditer");
		String btnSupprimer = request.getParameter("btnSupprimer");
		String btnGenerateDevis = request.getParameter("btnGenerateDevis");
		
		

		if( btnEditer != null && projetId != null) {

			response.sendRedirect(request.getContextPath()+ "/DevisFacture/EditProjet?id="+projetId);
			
		}else if( btnSupprimer != null && projetId != null) {
			if(isInteger(projetId)) {

				projetService.removeProjetById(Integer.valueOf(projetId));
				
				doGet(request, response);
			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}
			
			
		}else if( btnGenerateDevis != null && projetId != null) {
			
			//On Créer un devis pour sa on calcul le pris de tout HT et on recup la date du jour et bien sur le projet
			
			
			//On renvois vers le devis
			doGet(request, response);
		}else {
			request.setAttribute("Erreur", "Veuillez saisir un projet.");
			doGet(request, response);
			
		}
		
		
	}

}
