package com.ril.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Plan;
import com.ril.model.Projet;
import com.ril.model.Utilisateur;
import com.ril.service.PlanService;
import com.ril.service.ProjetService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class EditProjet
 */
@WebServlet("/EditProjet")
public class EditProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
private ProjetService projetService = new ProjetService();
private PlanService planService = new PlanService();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String projetId = request.getParameter("id");

		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		//Verifier que l'on edit pas un projet qui n'est pas en catalogue ou en devis
		if(MethodeUtile.isInteger(projetId)) {
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
		

		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		if( btnEditer != null && planId != null) {

			response.sendRedirect(request.getContextPath()+ "/DevisFacture/EditPlan?id="+planId);
			
		}else if( btnSupprimer != null && planId != null) {
			if(MethodeUtile.isInteger(planId)) {
				Plan plan = planService.getPlanById(Integer.valueOf(planId));

				planService.removePlan(plan);
				doGet(request, response);
				
			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}
			
			
		}else if( btnAjouter != null && planNom != null) {
			if(MethodeUtile.isInteger(idProjet)) {
				try {
					Projet projet = projetService.getProjetById(Integer.valueOf(idProjet));
					if(!planNom.equals("")) {
						
						Integer planIdAdd = planService.addPlan(projet, planNom);
						
						response.sendRedirect(request.getContextPath()+ "/DevisFacture/EditPlan?id="+planIdAdd);
						
					}else {
						request.setAttribute("Erreur", "Veuillez saisir un nom de plan.");
						doGet(request, response);
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
