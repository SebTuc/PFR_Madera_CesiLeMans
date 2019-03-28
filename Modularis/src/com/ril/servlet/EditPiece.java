package com.ril.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Piece;
import com.ril.model.Module;
import com.ril.service.ModuleService;
import com.ril.service.PieceService;
import com.ril.service.PlanService;

/**
 * Servlet implementation class EditPiece
 */
@WebServlet("/EditPiece")
public class EditPiece extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlanService planService = new PlanService();
	private PieceService pieceService = new PieceService();
	private ModuleService moduleService = new ModuleService();

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
		String pieceId = request.getParameter("id");

		//Verifier que l'on edit pas un projet/plan qui n'est pas en catalogue ou en devis
		if(isInteger(pieceId)) {
			Piece piece = pieceService.getPieceById(Integer.valueOf(pieceId));
			if(piece.getPlan().getProjet().getCatalogue().size() == 0) {
				if(piece.getPlan().getProjet().getDevises().size() == 0) {
					if(piece.getModules().size() == 0) {
						request.setAttribute("isEmptyList", true);
					}else {
						request.setAttribute("isEmptyList", false);
					}

					List<Module> ListModule = moduleService.getAllModules();

					request.setAttribute("ListModule", ListModule);
					request.setAttribute("Piece", piece);
					request.getRequestDispatcher("/jsp/application/DevisProjetFacture/EditPiece.jsp").forward(request, response);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String moduleId = request.getParameter("radio");
		String btnSupprimer = request.getParameter("btnSupprimer");
		String btnAjouter = request.getParameter("btnAjouter");
		String moduleSelectId = request.getParameter("module");
		String idPiece = request.getParameter("idPiece");


		if( btnSupprimer != null && moduleId != null) {
			if(isInteger(moduleId)) {
				if(isInteger(idPiece)) {
					try {

						Piece piece = pieceService.getPieceById(Integer.valueOf(idPiece));
						Module module = moduleService.getModuleById(Integer.valueOf(moduleId));
						
						pieceService.removeModuleInPiece(piece, module);

					}catch(Exception e){
						request.setAttribute("Erreur", "Une erreur est survenu.");
						doGet(request, response);
					}
				}else {
					request.setAttribute("Erreur", "Piece ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
					doGet(request, response);
				}
			}else {
				request.setAttribute("Erreur", "Module ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}


		}else if( btnAjouter != null && moduleSelectId != null && idPiece != null) {
			if(isInteger(idPiece)) {
				if(isInteger(moduleSelectId)) {
					try {

						Piece piece = pieceService.getPieceById(Integer.valueOf(idPiece));
						if(!moduleSelectId.equals("")) {

							Module module = moduleService.getModuleById(Integer.valueOf(moduleSelectId));

							piece.getModules().add(module);
							
							pieceService.editPiece(piece);

							doGet(request, response);
						}
					}catch(Exception e){
						request.setAttribute("Erreur", "Une erreur est survenu.");
						doGet(request, response);
					}
				}else {
					request.setAttribute("Erreur", "L'id du module est incorrect.");
					doGet(request, response);
					
				}
				
			}else {
				request.setAttribute("Erreur", "L'id de la piece est incorrect.");
				doGet(request, response);
			}
		}else {
			request.setAttribute("Erreur", "Veuillez Saisir un module.");
			doGet(request, response);
		}
	}

}
