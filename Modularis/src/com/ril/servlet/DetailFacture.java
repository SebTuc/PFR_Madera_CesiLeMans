package com.ril.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.EtapeFacture;
import com.ril.model.Facture;
import com.ril.service.EtapeFactureService;
import com.ril.service.FactureService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class DetailFacture
 */
@WebServlet("/DetailFacture")
public class DetailFacture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FactureService factureService = new FactureService();
	private EtapeFactureService etapeFactureService = new EtapeFactureService();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String factureId = request.getParameter("id");
		if(MethodeUtile.isInteger(factureId)) {
			 Facture facture = factureService.getFactureById(Integer.valueOf(factureId));
		        
		        
		        //DejaPayer  PourcentageDejaPayer  ResteAPayer
		        EtapeFacture beforeEtape = etapeFactureService.findBeforeEtape(facture.getEtapeFacture());
		        
		        float prixTotal = facture.getDevis().getPrixHt();
		        int percentageDejaPayer;
		        float DejaPayer;
		        float ResteAPayer;
		        if(beforeEtape != null) {
		        	percentageDejaPayer =  beforeEtape.getPourcentage();
			        DejaPayer = (prixTotal * beforeEtape.getPourcentage())/100f;
			        ResteAPayer = ((prixTotal * facture.getEtapeFacture().getPourcentage())/100f) - DejaPayer;
		        }else {
		        	
		        	percentageDejaPayer = 0;
			        DejaPayer = 0;
			        ResteAPayer = ((prixTotal * facture.getEtapeFacture().getPourcentage())/100f) - DejaPayer;
		        }

		        
		        
		        request.setAttribute("DejaPayer", DejaPayer);
		        request.setAttribute("PourcentageDejaPayer", percentageDejaPayer);
		        request.setAttribute("ResteAPayer", ResteAPayer);
		        request.setAttribute("Facture", facture);
				request.getRequestDispatcher("/jsp/application/DevisProjetFacture/DetailFacture.jsp").forward(request, response);
		}else {
			
			response.sendRedirect("/Modularis/DevisFacture/ListFacture");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String btnFacture = request.getParameter("btnFacture");
		String factureId = request.getParameter("factureId");
		String btnFactureBack = request.getParameter("btnFactureBack");
		Date dateNow = new Date();
		
		if(btnFacture != null && factureId != null) {
			if(MethodeUtile.isInteger(factureId)) {
				//passez en facturation a l'�tape 1 c'est a dire 3% et devis accepter
				Facture facture = factureService.getFactureById(Integer.valueOf(factureId));
				EtapeFacture nextEtape = etapeFactureService.findNextEtape(facture.getEtapeFacture());
				if(nextEtape!=null) {
					facture.setEtapeFacture(nextEtape);
					facture.setDateModification(dateNow);
					factureService.editFacture(facture);
				}else {
					request.setAttribute("Erreur", "Ceci est la dernier �tape de facturation.");
				}
				
				
			}
		}
		
		if(btnFactureBack != null && factureId != null) {
			if(MethodeUtile.isInteger(factureId)) {
				//passez en facturation a l'�tape 1 c'est a dire 3% et devis accepter
				Facture facture = factureService.getFactureById(Integer.valueOf(factureId));
				EtapeFacture beforeEtape = etapeFactureService.findBeforeEtape(facture.getEtapeFacture());
				if(beforeEtape!=null) {
					facture.setEtapeFacture(beforeEtape);
					facture.setDateModification(dateNow);
					factureService.editFacture(facture);
				}else {
					request.setAttribute("Erreur", "Ceci est la premiere �tape de facturation.");
				}
				
				
			}
		}
		
		
		
		doGet(request, response);
	}

}
