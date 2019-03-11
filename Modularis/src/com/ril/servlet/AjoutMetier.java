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
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Metier> metiers = metierService.getAllMetiers();
		req.setAttribute("metiers", metiers);
		
		req.getRequestDispatcher("/jsp/application/metier/AjoutMetier.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String metierId = req.getParameter("metierId");
		String metierNom = req.getParameter("metierNom");	
				
		// Ajout ou Delete
		if (metierNom != null) {						
			if (metierNom.trim() != null) {				
				metierService.addMetier(metierNom);
			}
		} else if (metierId != null) {
			metierService.removeMetierById(Integer.parseInt(metierId));
		}	
						
		//Definit la reponse comme "See Other" et redirige
		//Evite la multi-insertion après un refresh de l'utilsateur		
		resp.setStatus(303);	
		resp.sendRedirect(req.getContextPath()+"/Configuration/AjoutMetier");
	}

}
