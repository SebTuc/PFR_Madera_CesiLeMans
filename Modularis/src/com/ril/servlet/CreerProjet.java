package com.ril.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreerProjet
 */
@WebServlet("/CreerProjet")
public class CreerProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("/jsp/application/DevisProjetFacture/CreerProjet.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nomProjet = request.getParameter("nomProjet");
		String photo = request.getParameter("customFile");
		String[] test = request.getParameterValues("customFile");

		
		
		doGet(request, response);
	}

}
