package com.ril.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Piece;
import com.ril.model.Plan;
import com.ril.model.Utilisateur;
import com.ril.service.PieceService;
import com.ril.service.PlanService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class EditPlan
 */
@WebServlet("/EditPlan")
public class EditPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	private PlanService planService = new PlanService();
	private PieceService pieceService = new PieceService();

	
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
	
	private static boolean isFloat(String s) {
	    try { 
	        Float.parseFloat(s) ;
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String planId = request.getParameter("id");

		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		//Verifier que l'on edit pas un projet/plan qui n'est pas en catalogue ou en devis
		if(isInteger(planId)) {
			Plan plan = planService.getPlanById(Integer.valueOf(planId));
			if(plan.getProjet().getCatalogue().size() == 0) {
				if(plan.getProjet().getDevises().size() == 0) {
					if(plan.getPieces().size() == 0) {
						request.setAttribute("isEmptyList", true);
					}else {
						request.setAttribute("isEmptyList", false);
					}
					request.setAttribute("Plan", plan);
					request.getRequestDispatcher("/jsp/application/DevisProjetFacture/EditPlan.jsp").forward(request, response);
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
		String pieceId = request.getParameter("radio");
		String btnEditer = request.getParameter("btnEditer");
		String btnSupprimer = request.getParameter("btnSupprimer");
		String btnAjouter = request.getParameter("btnAjouter");
		String pieceNom = request.getParameter("pieceNom");
		String surface = request.getParameter("surface");
		String idPlan = request.getParameter("idPlan");

		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}

		if( btnEditer != null && pieceId != null) {

			response.sendRedirect(request.getContextPath()+ "/DevisFacture/EditPiece?id="+pieceId);
			
		}else if( btnSupprimer != null && pieceId != null) {
			if(isInteger(pieceId)) {
				Piece piece = pieceService.getPieceById(Integer.valueOf(pieceId));

				pieceService.removePiece(piece);
				doGet(request, response);
				
			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}
			
			
		}else if( btnAjouter != null && pieceNom != null && surface != null) {
			if(isInteger(idPlan)) {
				if(isFloat(surface)) {
					try {
						
						Plan plan = planService.getPlanById(Integer.valueOf(idPlan));
						if(!pieceNom.equals("")) {
							
							Integer IdPieceAdd = pieceService.addPiece(plan, pieceNom, Float.valueOf(surface));
							
							response.sendRedirect(request.getContextPath()+ "/DevisFacture/EditPiece?id="+IdPieceAdd);
						}
					}catch(Exception e){
						request.setAttribute("Erreur", "Une erreur est survenu.");
						doGet(request, response);
					}
				}else {
					request.setAttribute("Erreur", "La surface n'est pas un chiffre.");
					doGet(request, response);
				}
			}else {
				request.setAttribute("Erreur", "L'id du projet est incorrect.");
				doGet(request, response);
			}
		}else {
			request.setAttribute("Erreur", "Veuillez Saisir une piece.");
			doGet(request, response);
		}
	}

}
