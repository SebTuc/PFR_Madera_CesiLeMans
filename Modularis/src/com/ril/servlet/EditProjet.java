package com.ril.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Plan;
import com.ril.model.Projet;
import com.ril.service.PlanService;
import com.ril.service.ProjetService;

/**
 * Servlet implementation class EditProjet
 */
@WebServlet("/EditProjet")
public class EditProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
private ProjetService projetService = new ProjetService();
private PlanService planService = new PlanService();

	
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

		String projetId = request.getParameter("id");

		//Verifier que l'on edit pas un projet qui n'est pas en catalogue ou en devis
		if(isInteger(projetId)) {
			Projet projet = projetService.getProjetById(Integer.valueOf(projetId));
			if(projet.getCatalogue().size() == 0) {
				if(projet.getDevises().size() == 0) {
					if(projet.getPlans().size() == 0) {
						request.setAttribute("isEmptyList", true);
					}else {
						request.setAttribute("isEmptyList", false);
					}
					request.setAttribute("Projet", projet);
					request.getRequestDispatcher("/jsp/application/DevisProjetFacture/EditProjet.jsp").forward(request, response);
				}else {
					//Le mieux serais de faire une servlet de redirection
					request.setAttribute("Erreur", "Redirection");
					response.sendRedirect(request.getContextPath()+ "/DevisFacture/ListProjet");
				}
			}else {
				//Le mieux serais de faire une servlet de redirection
				request.setAttribute("Erreur", "Redirection");
				response.sendRedirect(request.getContextPath()+ "/DevisFacture/ListProjet");
			}
		}else {
			//Le mieux serais de faire une servlet de redirection
			request.setAttribute("Erreur", "Redirection");
			response.sendRedirect(request.getContextPath()+ "/DevisFacture/ListProjet");
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String planId = request.getParameter("radio");
		String btnEditer = request.getParameter("btnEditer");
		String btnSupprimer = request.getParameter("btnSupprimer");
		String btnAjouter = request.getParameter("btnAjouter");
		String planNom = request.getParameter("planNom");
		String idProjet = request.getParameter("idProjet");
		

		if( btnEditer != null && planId != null) {

			response.sendRedirect(request.getContextPath()+ "/DevisFacture/EditPlan?id="+planId);
			
		}else if( btnSupprimer != null && planId != null) {
			if(isInteger(planId)) {
				Plan plan = planService.getPlanById(Integer.valueOf(planId));

				planService.removePlan(plan);
				doGet(request, response);
				
			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}
			
			
		}else if( btnAjouter != null && planNom != null) {
			if(isInteger(idProjet)) {
				try {
					Projet projet = projetService.getProjetById(Integer.valueOf(idProjet));
					if(!planNom.equals("")) {
						
						Integer planIdAdd = planService.addPlan(projet, planNom);
						
						response.sendRedirect(request.getContextPath()+ "/DevisFacture/EditPlan?id="+planIdAdd);
						
					}
				}catch(Exception e){
					request.setAttribute("Erreur", "Une erreur est survenu.");
					doGet(request, response);
				}
			}else {
				request.setAttribute("Erreur", "L'id du projet est incorrect.");
				doGet(request, response);
			}
		}else {
			request.setAttribute("Erreur", "Veuillez Saisir un plan.");
			doGet(request, response);
		}
	}

}
