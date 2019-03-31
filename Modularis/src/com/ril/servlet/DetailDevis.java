package com.ril.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Devis;
import com.ril.model.EtapeFacture;
import com.ril.model.Etat;
import com.ril.service.DevisService;
import com.ril.service.EtapeFactureService;
import com.ril.service.EtatService;
import com.ril.service.FactureService;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String devisId = request.getParameter("id");
		if(MethodeUtile.isInteger(devisId)) {
			 Devis devis = devisService.getDevisById(Integer.valueOf(devisId));
		        
		        request.setAttribute("Devis", devis);
		        
				request.getRequestDispatcher("/jsp/application/DevisProjetFacture/Devis.jsp").forward(request, response);
		}else {
			
			response.sendRedirect("/Modularis/DevisFacture/ListDevis");
		}
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String btnFacture = request.getParameter("btnFacture");
		String devisId = request.getParameter("devisId");
		
		if(btnFacture != null && devisId != null) {
			if(MethodeUtile.isInteger(devisId)) {
				//passez en facturation a l'étape 1 c'est a dire 3% et devis accepter
				EtapeFacture firstEtape = etapeFactureService.findFirstEtape();
				Devis devis = devisService.getDevisById(Integer.valueOf(devisId));
				Etat etat = etatService.getEtatById(2);
				devis.setEtat(etat);
				
				factureService.addFacture(devis, firstEtape);
				response.sendRedirect("/Modularis/DevisFacture/ListFacture");
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

