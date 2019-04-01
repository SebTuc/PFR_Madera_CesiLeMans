package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Metier;
import com.ril.model.Utilisateur;
import com.ril.service.MetierService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutMetier
 */
@WebServlet("/AjoutMetier")
public class AjoutMetier extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MetierService metierService = new MetierService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		List<Metier> metiers = metierService.getAllMetiers();
		request.setAttribute("ListMetier", metiers);
		
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutMetier.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String metierNom = request.getParameter("metierNom");	
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		String valeur = request.getParameter("valeur");
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				
				metierService.removeMetierById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				Metier metier = metierService.getMetierById(Integer.valueOf(idValeur));
				metier.setNom(valeur);
				metierService.editMetier(metier);
				
				// Retour de l'objet modifier sous format json
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print("{ \"id\": \""+metier.getMetierId()+"\", \"valeur\": \""+metier.getNom()+"\" }");
				response.getWriter().flush();
				
			}
		}else if (metierNom != null) {						
			if (metierNom.trim() != null) {				
				metierService.addMetier(metierNom);
				
				//Definit la reponse comme "See Other" et redirige
				//Evite la multi-insertion après un refresh de l'utilsateur		
				response.setStatus(303);	
				response.sendRedirect(request.getContextPath()+"/Configuration/AjoutMetier");
			}
		}else {
			//Post de null part
		}
						
	}

}
