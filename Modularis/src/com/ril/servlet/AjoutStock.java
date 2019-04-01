package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Composant;
import com.ril.model.Entrepot;
import com.ril.model.Stock;
import com.ril.model.Utilisateur;
import com.ril.service.ComposantService;
import com.ril.service.EntrepotService;
import com.ril.service.StockService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutStock
 */
@WebServlet("/AjoutStock")
public class AjoutStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EntrepotService entrepotService 	= new EntrepotService();
	private ComposantService composantService 	= new ComposantService();
	private StockService stockService			= new StockService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		

		
		String entrepotId = request.getParameter("id");
		if(MethodeUtile.isInteger(entrepotId)) {
			 Entrepot entrepot = entrepotService.getEntrepotById(Integer.valueOf(entrepotId));
				List<Composant> ListComposant = composantService.getAllComposantNotInEntrepot(entrepot);
				
				request.setAttribute("ListComposant", ListComposant);
		        request.setAttribute("Entrepot", entrepot);
		        
				request.getRequestDispatcher("/jsp/application/Stocks/AjoutStock.jsp").forward(request, response);
		}else {
			response.sendRedirect("/Modularis/GestionStock/ListEntrepot");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}


		String insertQuantite = request.getParameter("insertQuantite");
		String idComposant = request.getParameter("idComposant");
		String idEntrepot = request.getParameter("idEntrepot");
		
		String action = request.getParameter("action");
		String idValeur = request.getParameter("idStock");
		
		String quantite = request.getParameter("quantite");
				
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				if(MethodeUtile.isInteger(idValeur)) {
					stockService.removeStockById(Integer.valueOf(idValeur));
				}else {
					request.setAttribute("Erreur", "Erreur lors de la suppresion");
				}
				
				
			}else if(action.equals("Edition")) {
				
				if(MethodeUtile.isInteger(idValeur) && MethodeUtile.isInteger(quantite)) {
					Stock stock = stockService.getStockById(Integer.valueOf(idValeur));
					
					stock.setQuantite(Integer.valueOf(quantite));
					
					stockService.editStock(stock);
						response.setStatus(200);
						response.setContentType("application/json");
						response.getWriter().print("{ \"idStock\": \""+stock.getStockId()+"\", \"nomComposant\": \""+stock.getComposant().getNom()+"\" , \"quantite\": \""+stock.getQuantite()+"\", \"fournisseur\": \""+stock.getComposant().getFournisseur().getNom()+"\" }");
						response.getWriter().flush();
		
				}else {
					response.setStatus(400);	
				}
				
				
			}
		}else if (insertQuantite != null && idComposant != null && idEntrepot != null) {						
			if (!insertQuantite.equals("") && !idComposant.equals("") && !idEntrepot.equals("")) {				
				if(MethodeUtile.isInteger(idComposant) && MethodeUtile.isInteger(insertQuantite) && MethodeUtile.isInteger(idEntrepot)) {
					
					//-1 si pas de creation car etape ou pourcentage incorect
					Entrepot entrepot = entrepotService.getEntrepotById(Integer.valueOf(idEntrepot));
					Composant composant = composantService.getComposantById(Integer.valueOf(idComposant));
					Integer idStock = stockService.addStock(entrepot, Integer.valueOf(insertQuantite), composant);
					
					if(idStock != -1) {
						
						response.setStatus(303);	
						response.sendRedirect(request.getContextPath()+"/GestionStock/AjoutStock?id="+Integer.valueOf(idEntrepot));
					}else {
						request.setAttribute("Erreur", "Les informations saisie exist déjà");
						doGet(request,response);
					}
				
					
				}else {
					
					request.setAttribute("Erreur", "Veuillez saisir un chiffre/composant.");
					doGet(request,response);
					
				}
			}else {
				request.setAttribute("Erreur", "Veuillez saisir un chiffre/composant.");
				doGet(request,response);
			}
		}else {
			request.setAttribute("Erreur", "Erreur Veuillez réessayer.");
			doGet(request,response);
		}
	}

}
