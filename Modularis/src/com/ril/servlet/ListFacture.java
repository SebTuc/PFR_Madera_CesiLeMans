package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Facture;
import com.ril.service.FactureService;

/**
 * Servlet implementation class ListFacture
 */
@WebServlet("/ListFacture")
public class ListFacture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	private FactureService factureService = new FactureService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Facture> ListFacture = factureService.getAllFacture();
		if(ListFacture == null) {
			
			request.setAttribute("isEmptyList", true);
		}else {
			request.setAttribute("isEmptyList", false);
		}
		request.setAttribute("ListFacture", ListFacture);
		request.getRequestDispatcher("/jsp/application/DevisProjetFacture/ListFacture.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
