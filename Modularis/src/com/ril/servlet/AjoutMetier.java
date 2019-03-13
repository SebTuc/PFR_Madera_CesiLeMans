package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Metier;
import com.ril.service.MetierService;

/**
 * Servlet implementation class AjoutMetier
 */
@WebServlet("/AjoutMetier")
public class AjoutMetier extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MetierService metierService = new MetierService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Metier> metiers = metierService.getAllMetiers();
		request.setAttribute("ListMetier", metiers);
		
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutMetier.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String metierNom = request.getParameter("metierNom");	
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		String valeur = request.getParameter("valeur");
				
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				
				metierService.removeMetierById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				Metier metier = metierService.getMetierById(Integer.valueOf(idValeur));
				metier.setNom(valeur);
				metierService.editMetier(metier);
				
			}
		}else if (metierNom != null) {						
			if (metierNom.trim() != null) {				
				metierService.addMetier(metierNom);
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
