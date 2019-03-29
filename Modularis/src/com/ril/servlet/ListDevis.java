package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Devis;
import com.ril.service.DevisService;

/**
 * Servlet implementation class ListDevis
 */
@WebServlet("/ListDevis")
public class ListDevis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DevisService devisService = new DevisService();

	private static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Devis> ListDevis = devisService.getAllDeviss();
		if(ListDevis == null) {
			
			request.setAttribute("isEmptyList", true);
		}else {
			request.setAttribute("isEmptyList", false);
		}
		request.setAttribute("ListDevis", ListDevis);
		request.getRequestDispatcher("/jsp/application/DevisProjetFacture/ListDevis.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String devisId = request.getParameter("radio");
		String btnVisualiser = request.getParameter("btnVisualiser");
		String btnSupprimer = request.getParameter("btnSupprimer");
		
		

		if( btnVisualiser != null && devisId != null) {
			if(isInteger(devisId)) {
			response.sendRedirect(request.getContextPath()+ "/DevisFacture/DetailDevis?id="+devisId);
			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}
			
		}else if( btnSupprimer != null && devisId != null) {
			if(isInteger(devisId)) {

				devisService.removeDevisById(Integer.valueOf(devisId));
				
				doGet(request, response);
			}else {
				request.setAttribute("Erreur", "Devis ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}
			
			
		}else {
			request.setAttribute("Erreur", "Veuillez saisir un projet.");
			doGet(request, response);
			
		}
	}

}
