package com.ril.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Devis;
import com.ril.model.Entrepot;
import com.ril.model.EtapeFacture;
import com.ril.model.Etat;
import com.ril.model.Module;
import com.ril.model.ModuleXComposant;
import com.ril.model.Piece;
import com.ril.model.Plan;
import com.ril.model.Stock;
import com.ril.model.Utilisateur;
import com.ril.service.DevisService;
import com.ril.service.EntrepotService;
import com.ril.service.EtapeFactureService;
import com.ril.service.EtatService;
import com.ril.service.FactureService;
import com.ril.service.StockService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class DetailDevis
 */
@WebServlet("/DetailDevis")
public class DetailDevis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DevisService devisService = new DevisService();
	
	private FactureService factureService = new FactureService();
	
	private EtapeFactureService etapeFactureService = new EtapeFactureService();
	
	private EtatService etatService = new EtatService();
	
	private StockService stockService = new StockService();
	
	private EntrepotService entrepotService = new EntrepotService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("SessionUtilisateur"));
		}
		
		String devisId = request.getParameter("id");
		if(MethodeUtile.isInteger(devisId)) {
			 Devis devis = devisService.getDevisById(Integer.valueOf(devisId));
		        
		        request.setAttribute("Devis", devis);
		        
				request.getRequestDispatcher("/jsp/application/DevisProjetFacture/DetailDevis.jsp").forward(request, response);
		}else {
			
			response.sendRedirect("/Modularis/DevisFacture/ListDevis");
		}
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("SessionUtilisateur"));
		}
		String btnFacture = request.getParameter("btnFacture");
		String devisId = request.getParameter("devisId");
		
		if(btnFacture != null && devisId != null) {
			if(MethodeUtile.isInteger(devisId)) {
				//passez en facturation a l'�tape au plus faible index
				
				Devis devis = devisService.getDevisById(Integer.valueOf(devisId));
				
				
				//Mettre a jour les stock
				//On recupe l'entrepot
				Entrepot entrepot = devis.getUtilisateur().getEntrepot();
				//On boucle sur chaque plan => piece => module => composant , pour avoir le nombre de composant
				//On annule la facture si les stock ne sont pas definie
				
				//On boucle une fois pour voir que tout les composant exist dans les stocks.
				for(Plan plan : devis.getProjet().getPlans()) {
					for(Piece piece : plan.getPieces()) {
						for(Module module : piece.getModules()) {
							for(ModuleXComposant modXComposant : module.getModuleXComposants()) {
								boolean flag=false;
								for(Stock stock : entrepot.getStocks()) {
									if(stock.getComposant().getComposantId() == modXComposant.getComposant().getComposantId()) {
										flag = true;
									}
								}
								if(flag == false) {
									request.setAttribute("Erreur", "Le composant "+modXComposant.getComposant().getNom()+ " n'est pas dans les stocks de l'entrepot "+entrepot.getLieux()+"." );
									doGet(request, response);
									return;
								}
							}
						}
					}
				}
				//Puis on met a jour le tout !
				for(Plan plan : devis.getProjet().getPlans()) {
					for(Piece piece : plan.getPieces()) {
						for(Module module : piece.getModules()) {
							for(ModuleXComposant modXComposant : module.getModuleXComposants()) {
								for(Stock stock : entrepot.getStocks()) {
									if(stock.getComposant().getComposantId() == modXComposant.getComposant().getComposantId()) {
										stock.setQuantite(stock.getQuantite() - modXComposant.getQuantite());
										stockService.editStock(stock); 
										
									}
								}
								entrepot = entrepotService.getEntrepotById(entrepot.getEntrepotId());
							}
						}
					}
				}
				devis = devisService.getDevisById(Integer.valueOf(devisId));
				EtapeFacture firstEtape = etapeFactureService.findFirstEtape();
				Etat etat = etatService.getEtatById(2);
				Date dateNow = new Date();
				devis.setEtat(etat);
				
				Integer factureId = factureService.addFacture(devis, firstEtape,dateNow);
				
				if(factureId == -1) {
					request.setAttribute("Erreur", "Erreur lors du passage en facturation.");
					doGet(request, response);
				}else {
					response.sendRedirect("/Modularis/DevisFacture/ListFacture");
				}
				
			}else {
				request.setAttribute("Erreur", "Erreur lors du passage en facturation.");
				doGet(request, response);
			}
		}else {
			request.setAttribute("Erreur", "Erreur lors du passage en facturation.");
			doGet(request, response);
		}
		
		
		
		
	}

}

