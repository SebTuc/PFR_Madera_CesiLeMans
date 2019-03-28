package com.ril.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.daoHibernate.PieceHome;
import com.ril.daoHibernate.PlanHome;
import com.ril.daoHibernate.ProjetHome;
import com.ril.model.Devis;
import com.ril.model.Piece;
import com.ril.model.Plan;
import com.ril.model.Projet;
import com.ril.service.DevisService;
import com.ril.service.ImageService;
import com.ril.service.PieceService;
import com.ril.service.PlanService;
import com.ril.service.ProjetService;

/**
 * Servlet implementation class DetailDevis
 */
@WebServlet("/DetailCatalogueProjet")
public class DetailCatalogueProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProjetService projetService = new ProjetService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

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
				
		if (idProjet != null && isInteger(idProjet)) {
		
			// Copie du projet en renvoie vers edition projet
			Projet projet = projetService.getProjetById(Integer.valueOf(idProjet));

			Projet projetCloned = this.cloneProjet(projet);
			request.setAttribute("Projet", projetCloned);			
			request.getRequestDispatcher("/jsp/application/DevisProjetFacture/DetailCatalogueProjet.jsp").forward(request, response);			
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
	public Projet cloneProjet(Projet projet) {
		
		ImageService imageService = new ImageService();
		PlanService planService = new PlanService();
		PieceService pieceService = new PieceService();		
		
		if(projet != null) {
		
			// Ajout du nouveau projet clones
			Projet projetCloned = new Projet(projet.getNom(),projet.getImage());
			projetService.addProjet(projetCloned);
			
			Set<Plan> plansToClone = projet.getPlans(); 
			
			if (plansToClone != null && plansToClone.size() > 0) {
				
				// Clonage des plans originaux
				Set<Plan> plansCloned = new HashSet<Plan>(); 
				Plan planCloned = null;								
				for (Plan planToClone : plansToClone) {
					planCloned = new Plan(projetCloned,planToClone.getNom());
					planService.addPlan(planCloned);
					
					Set<Piece> piecesToClone = planToClone.getPieces();
					
					if(piecesToClone != null && piecesToClone.size() > 0) {
						
						// Clonage des pieces originales
						Set<Piece> piecesCloned = new HashSet<Piece>();
						Piece pieceCloned = null;
						for (Piece pieceToClone : piecesToClone) {
							
							// Ajoute les memes modules que l'original
							pieceCloned = new Piece(planCloned,pieceToClone.getNom(),pieceToClone.getSurface());
							pieceService.addPiece(pieceCloned);							
//							pieceService.editPiece(pieceCloned);
							piecesCloned.add(pieceCloned);
						}
						
						// Ajoute les pieces clonees dans le plan clones
						planCloned.setPieces(piecesCloned);
//						planService.editPlan(planCloned);
//						plansCloned.add(planCloned);		
					}
				}

				// Ajoute les plans clones dans le projet clones
				projetCloned.setPlans(plansCloned);
//				projetService.editProjet(projetCloned);
			}
			
			return projetCloned;
		} else {
			throw new NullPointerException("Le projet données est null");
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

