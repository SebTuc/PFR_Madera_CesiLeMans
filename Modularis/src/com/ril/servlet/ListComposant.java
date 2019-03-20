package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Composant;
import com.ril.service.ComposantService;

/**
 * Servlet implementation class ListComposant
 */
@WebServlet("/ListComposant")
public class ListComposant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComposantService composantService = new ComposantService();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Composant> ListComposant= composantService.getAllComposants();
		request.setAttribute("ListComposant", ListComposant);
		request.getRequestDispatcher("/jsp/application/Configuration/ListComposant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String composantId = request.getParameter("id");
		
		if( composantId != null) {
			
			composantService.removeComposantById(Integer.valueOf(composantId));
			doGet(request, response);
			
		}else {
			
			doGet(request, response);
		}
		
		
	}

}
