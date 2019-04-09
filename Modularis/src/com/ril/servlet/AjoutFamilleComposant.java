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
		String familleComposantNom = request.getParameter("familleComposantNom");	
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
				
				// Retour de l'objet modifier sous format json
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print("{ \"id\": \""+familleComposant.getFamilleComposantId()+"\", \"valeur\": \""+familleComposant.getNom()+"\" }");
				response.getWriter().flush();
				
			}
		}else if (familleComposantNom != null) {						
			if (familleComposantNom.trim() != null) {				
				familleComposantService.addFamilleComposant(familleComposantNom);

				//Definit la reponse comme "See Other" et redirige
				//Evite la multi-insertion apr�s un refresh de l'utilsateur		
				response.setStatus(303);	
				response.sendRedirect(request.getContextPath()+"/Configuration/AjoutFamilleComposant");
			}
		}else {
			//Post de null part
		}
						
	}

}
