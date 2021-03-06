package com.ril.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Piece;
import com.ril.model.Plan;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String planId = request.getParameter("id");

		//Verifier que l'on edit pas un projet/plan qui n'est pas en catalogue ou en devis
		if(MethodeUtile.isInteger(planId)) {
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

		if( btnEditer != null && pieceId != null) {

			response.sendRedirect(request.getContextPath()+ "/DevisFacture/EditPiece?id="+pieceId);

		}else if( btnSupprimer != null && pieceId != null) {
			if(MethodeUtile.isInteger(pieceId)) {
				Piece piece = pieceService.getPieceById(Integer.valueOf(pieceId));

				pieceService.removePiece(piece);
				doGet(request, response);

			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}


		}else if( btnAjouter != null && pieceNom != null && surface != null) {
			if(MethodeUtile.isInteger(idPlan)) {
				if(MethodeUtile.isFloat(surface)) {
					try {

						Plan plan = planService.getPlanById(Integer.valueOf(idPlan));
						if(!pieceNom.equals("")) {

							Integer IdPieceAdd = pieceService.addPiece(plan, pieceNom, Float.valueOf(surface));

							response.sendRedirect(request.getContextPath()+ "/DevisFacture/EditPiece?id="+IdPieceAdd);
						}else {
							request.setAttribute("Erreur", "Veuillez saisir un nom de pi�ce.");
							doGet(request, response);
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
