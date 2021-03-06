package com.ril.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Devis;
import com.ril.service.DevisService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class DevisPDF
 */
@WebServlet("/DevisPDF")
public class DevisPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DevisService devisService = new DevisService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

		doGet(request, response);
	}

}
