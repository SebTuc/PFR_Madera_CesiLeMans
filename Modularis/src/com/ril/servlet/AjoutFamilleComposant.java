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
		
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutFamilleComposant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String metierId = request.getParameter("metierId");
		String metierNom = request.getParameter("familleComposantNom");	
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		String valeur = request.getParameter("valeur");
				
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				
				familleComposantService.removeFamilleComposantById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				FamilleComposant familleComposant = familleComposantService.getFamilleComposantById(Integer.valueOf(idValeur));
				familleComposant.setNom(valeur);
				familleComposantService.editFamilleComposant(familleComposant);
				
			}
		}else if (metierNom != null) {						
			if (metierNom.trim() != null) {				
				familleComposantService.addFamilleComposant(metierNom);
			}
		}else {
			//Post de null part
		}
						
		//Definit la reponse comme "See Other" et redirige
		//Evite la multi-insertion après un refresh de l'utilsateur		
		response.setStatus(303);	
		response.sendRedirect(request.getContextPath()+"/Configuration/AjoutFamilleComposant");
	}

}
