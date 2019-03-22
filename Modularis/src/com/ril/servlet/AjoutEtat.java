package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Etat;
import com.ril.service.EtatService;

/**
 * Servlet implementation class AjoutGamme
 */
@WebServlet("/AjoutGamme")
public class AjoutEtat extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private EtatService etatService = new EtatService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Etat> listEtat = etatService.getAllEtats();
		
		request.setAttribute("ListEtat", listEtat);
		
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutEtat.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String etatNom = request.getParameter("etatNom");
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		String valeur = request.getParameter("valeur");
				
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				
				etatService.removeEtatById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				Etat etat = etatService.getEtatById(Integer.valueOf(idValeur));
				etat.setNom(valeur);
				etatService.editEtat(etat);
								
				// Retour de l'objet modifier sous format json
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print("{ \"id\": \""+etat.getEtatId()+"\", \"valeur\": \""+etat.getNom()+"\" }");
				response.getWriter().flush();
				
			}
		}else if (etatNom != null) {						
			if (etatNom.trim() != null) {				
				etatService.addEtat(etatNom);
				
				//Definit la reponse comme "See Other" et redirige
				//Evite la multi-insertion après un refresh de l'utilsateur		
				response.setStatus(303);	
				response.sendRedirect(request.getContextPath()+"/Configuration/AjoutEtat");
			}
		}else {
			//Post de null part
		}
						

	}

}
