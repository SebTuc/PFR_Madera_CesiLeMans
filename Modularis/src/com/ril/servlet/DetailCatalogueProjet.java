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

import com.ril.model.Module;
import com.ril.model.Piece;
import com.ril.model.Plan;
import com.ril.model.Projet;
import com.ril.model.Utilisateur;
import com.ril.service.ModuleService;
import com.ril.service.PieceService;
import com.ril.service.PlanService;
import com.ril.service.ProjetService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class DetailDevis
 */
@WebServlet("/DetailCatalogueProjet")
public class DetailCatalogueProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProjetService projetService = new ProjetService();
	private PlanService planService = new PlanService();
	private PieceService pieceService = new PieceService();	
	private ModuleService moduleService = new ModuleService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		String idProjet = request.getParameter("idProjet");

		if (idProjet != null && isInteger(idProjet)) {			
			Projet projet = projetService.getProjetById(Integer.valueOf(idProjet));
			request.setAttribute("Projet", projet);			
			request.getRequestDispatcher("/jsp/application/Catalogue/DetailCatalogueProjet.jsp").forward(request, response);			
		}else {
			request.getRequestDispatcher("/jsp/common/error.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idProjet = request.getParameter("idProjet");

		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		if (idProjet != null && isInteger(idProjet)) {

			// Copie du projet en renvoie vers edition projet
			Projet projet = projetService.getProjetById(Integer.valueOf(idProjet));

			this.cloneProjet(projet);		
			response.sendRedirect("/Modularis/DevisFacture/ListProjet");		
		}else {
			request.getRequestDispatcher("/jsp/common/error.jsp").forward(request, response);
		}

		doGet(request, response);
	}

	/**
	 * Clone l'ensemble d'un projet sauf les devis et les utilisateur
	 * liés à celui-ci
	 * @param projet Projet à cloner
	 * @return id du projet cloné
	 */
	public void cloneProjet(Projet projet) {
		if(projet != null) {

			// Ajout du nouveau projet clones
			Projet projetCloned = new Projet(projet.getNom(),projet.getImage());
			Integer projetId = projetService.addProjet(projetCloned);
			
			Projet newProjet = projetService.getProjetById(projetId);
			newProjet.setClone(true);
			projetService.editProjet(newProjet);
			for(Plan plan : projet.getPlans()) {
				
				Plan newPlan = new Plan(newProjet,plan.getNom());
				Integer planId = planService.addPlan(newPlan);
				Plan newPlanV2 = planService.getPlanById(planId);
				
				for(Piece piece : plan.getPieces()) {
					pieceService.addPiece(newPlanV2,piece.getNom(),piece.getSurface());	
				}
			}
			
			Projet projetV2 = projetService.getProjetById(projetId);
			
			CopySameModuleProjetToProjet(projet,projetV2);
		} else {
			throw new NullPointerException("Le projet données est null");
		}
	}


	public void JoinListModuleToPiece(List<Module> list , Piece piece) {
		if(list.size()!=0) {
			//Get the instance hibernate with the java instance object
			Piece newPiece = pieceService.getPieceById(piece.getPieceId());
			for(Module module : list){
				//Get the instance hibernate with the java instance object
				Module mod = moduleService.getModuleById(module.getModuleId());
				Boolean flag = mod.isDisplay();
				if(flag == null || flag != false) {
					pieceService.addModuleToPiece(mod, newPiece);
				}
				//reinstance piece
				newPiece = pieceService.getPieceById(piece.getPieceId());
			}
		}
	}
	
	
	public void CopySameModuleProjetToProjet(Projet projetOriginal,Projet projetCloned){

		
		for(Plan plan : projetOriginal.getPlans()) {
			for(Plan planClone : projetCloned.getPlans()) {
				if(planClone.getNom().equals(plan.getNom())) {
					for(Piece piece : plan.getPieces()) {
						for(Piece pieceClone : planClone.getPieces()) {
							String pieceSurface = piece.getNom() + piece.getSurface();
							String pieceSurfaceClone = pieceClone.getNom() + pieceClone.getSurface();
							if(pieceSurface.equals(pieceSurfaceClone)) {
								//Set to the method the java instace object
								List<Module> list = new ArrayList<Module>(piece.getModules());
								Piece testPiece = pieceClone;
								JoinListModuleToPiece(list,testPiece);
							}
						}
					}
				}
			}
		}
		
	}

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

}

