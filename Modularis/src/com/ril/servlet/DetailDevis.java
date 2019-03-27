package com.ril.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Devis;
import com.ril.service.DevisService;

/**
 * Servlet implementation class DetailDevis
 */
@WebServlet("/DetailDevis")
public class DetailDevis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DevisService devisService = new DevisService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String devisId = request.getParameter("id");
        Devis devis = devisService.getDevisById(Integer.valueOf(devisId));
        
        request.setAttribute("Devis", devis);
        
		request.getRequestDispatcher("/jsp/application/DevisProjetFacture/Devis.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

