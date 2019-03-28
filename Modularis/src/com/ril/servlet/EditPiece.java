package com.ril.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Gamme;
import com.ril.model.Module;
import com.ril.model.Piece;
import com.ril.service.GammeService;
import com.ril.service.ModuleService;
import com.ril.service.PieceService;

/**
 * Servlet implementation class EditPiece
 */
@WebServlet("/EditPiece")
public class EditPiece extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PieceService pieceService = new PieceService();
	private ModuleService moduleService = new ModuleService();
	private GammeService gammeService = new GammeService();

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
	
	private boolean findContains(String value, String moduleValue) {
		
		String valueUpper = value.toUpperCase();
		String moduleValueUpper = moduleValue.toUpperCase();
		
		if(moduleValueUpper.contains(valueUpper)) {
			
			return true;
			
		}else {
		 
			return false;
			
		}
		
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
					List<Gamme> ListGamme = gammeService.getAllGammes();
					List<Module> list = new ArrayList<Module>();
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

					String gamme = request.getParameter("gamme");
					String nomModule = request.getParameter("nomModule");
					//Trie par critere
					if(gamme != null && !(gamme.equals("-1")) || nomModule != null && !(nomModule.equals(""))) {
						if(ListModule != null) {
							if(isInteger(gamme)) {
								for(Module module : ListModule) {
									if(!gamme.equals("-1")) {
										if(Integer.valueOf(gamme) == module.getGamme().getGammeId()){
											if(!nomModule.equals("")) {
												if(findContains(nomModule,module.getNom())){
							
													list.add(module);
													
												}
											}else {
												list.add(module);
											}	
										}
									}else if(!nomModule.equals("")) {
										if(findContains(nomModule,module.getNom())){
					
											list.add(module);
											
										}
									}
									
								}
							}
						}
						
						request.setAttribute("ListModule", list);
					}else {
						
						request.setAttribute("ListModule", ListModule);
						
					}
					if(nomModule != null && gamme != null && !(gamme.equals(""))) {
						if(isInteger(gamme)) {
							request.setAttribute("gammeId", gamme);
							request.setAttribute("nomModule", nomModule);
						}
					}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------					
					request.setAttribute("ListGamme", ListGamme);
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
						
						if(!pieceService.removeModuleToPiece(module , piece)) {
							request.setAttribute("Erreur", "Erreur lors de la suppression.");
						}
					}catch(Exception e){
						request.setAttribute("Erreur", "Une erreur est survenu.");
					}
				}else {
					request.setAttribute("Erreur", "Piece ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				}
			}else {
				request.setAttribute("Erreur", "Module ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
			}

			
		}else if( btnAjouter != null && moduleSelectId != null && idPiece != null) {
			if(isInteger(idPiece)) {
				if(isInteger(moduleSelectId)) {
					try {

						Piece piece = pieceService.getPieceById(Integer.valueOf(idPiece));
						if(!moduleSelectId.equals("")) {

							Module module = moduleService.getModuleById(Integer.valueOf(moduleSelectId));
							
							if(!pieceService.addModuleToPiece(module, piece)) {
								request.setAttribute("Erreur", "Module/piece incorrect ou module déjà existant.");
							}
						}
					}catch(Exception e){
						request.setAttribute("Erreur", "Une erreur est survenu.");
					}
				}else {
					if(moduleSelectId.equals("")) {
						request.setAttribute("Erreur", "Veuillez saisir un module.");
					}else {
						request.setAttribute("Erreur", "L'id du module est incorrect.");
					}
					
				}
				
			}else {
				request.setAttribute("Erreur", "L'id de la piece est incorrect.");
			}
		}else {
			request.setAttribute("Erreur", "Veuillez Saisir un module.");
		}
		
		doGet(request, response);
	}

}
