package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.FamilleComposant;
import com.ril.service.FamilleComposantService;

/**
 * Servlet implementation class AjoutFamilleComposant
 */
@WebServlet("/AjoutFamilleComposant")
public class AjoutFamilleComposant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FamilleComposantService familleComposantService = new FamilleComposantService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FamilleComposant> ListFamilleComposant = familleComposantService.getAllFamilleComposant();
		request.setAttribute("ListFamilleComposant", ListFamilleComposant);
		
		request.getRequestDispatcher("/jsp/application/FamilleComposant/AjoutFamilleComposant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String metierId = request.getParameter("metierId");
		String metierNom = request.getParameter("familleComposantNom");	
				
		// Ajout ou Delete
		if (metierNom != null) {						
			if (metierNom.trim() != null) {				
				familleComposantService.addFamilleComposant(metierNom);
			}
		} else if (metierId != null) {
			familleComposantService.removeFamilleComposantById(Integer.parseInt(metierId));
		}	
						
		//Definit la reponse comme "See Other" et redirige
		//Evite la multi-insertion apr�s un refresh de l'utilsateur		
		response.setStatus(303);	
		response.sendRedirect(request.getContextPath()+"/Configuration/AjoutFamilleComposant");
	}

}
