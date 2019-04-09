package com.ril.servlet;

import java.io.IOException;

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
 * Servlet implementation class FacturePDF
 */
@WebServlet("/FacturePDF")
public class FacturePDF extends HttpServlet {
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
			request.getRequestDispatcher("/jsp/application/DevisProjetFacture/Facture.jsp").forward(request, response);
		}else {

			response.sendRedirect("/Modularis/DevisFacture/ListFacture");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
