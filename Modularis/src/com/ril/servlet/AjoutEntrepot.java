package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Entrepot;
import com.ril.model.Utilisateur;
import com.ril.service.EntrepotService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutEntrepot
 */
@WebServlet("/AjoutEntrepot")
public class AjoutEntrepot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	private EntrepotService entrepotService = new EntrepotService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		List<Entrepot> ListEntrepot = entrepotService.getAllEntrepots();
		request.setAttribute("ListEntrepot", ListEntrepot);
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutEntrepot.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String entrepotNom = request.getParameter("entrepotNom");	
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
				
				entrepotService.removeEntrepotById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				Entrepot entrepot = entrepotService.getEntrepotById(Integer.valueOf(idValeur));
				entrepot.setLieux(valeur);
				entrepotService.editEntrepot(entrepot);
				
				// Retour de l'objet modifier sous format json
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print("{ \"id\": \""+entrepot.getEntrepotId()+"\", \"valeur\": \""+entrepot.getLieux()+"\" }");
				response.getWriter().flush();
				
			}
		}else if (entrepotNom != null) {						
			if (entrepotNom.trim() != null) {				
				entrepotService.addEntrepot(entrepotNom);

				//Definit la reponse comme "See Other" et redirige
				//Evite la multi-insertion après un refresh de l'utilsateur		
				response.setStatus(303);	
				response.sendRedirect(request.getContextPath()+"/Configuration/AjoutEntrepot");
			}
		}else {
			//Post de null part
		}
						
	}

}
