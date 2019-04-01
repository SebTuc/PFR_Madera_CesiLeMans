package com.ril.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Devis;
import com.ril.service.DevisService;
import com.ril.service.EtapeFactureService;
import com.ril.service.EtatService;
import com.ril.service.FactureService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class Facture
 */
@WebServlet("/Facture")
public class Facture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DevisService devisService = new DevisService();
	
	private FactureService factureService = new FactureService();
	
	private EtapeFactureService etapeFactureService = new EtapeFactureService();
	
	private EtatService etatService = new EtatService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String devisId = request.getParameter("id");
		if(MethodeUtile.isInteger(devisId)) {
			 Devis devis = devisService.getDevisById(Integer.valueOf(devisId));
		        
		        request.setAttribute("Devis", devis);
		        
				request.getRequestDispatcher("/jsp/application/DevisProjetFacture/Facture.jsp").forward(request, response);
		}else {
			
			response.sendRedirect("/Modularis/DevisFacture/ListDevis");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
