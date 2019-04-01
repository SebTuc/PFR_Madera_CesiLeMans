package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Materiaux;
import com.ril.service.MateriauxService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutMateriaux
 */
@WebServlet("/AjoutMateriaux")
public class AjoutMateriaux extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MateriauxService materiauxService = new MateriauxService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}
		List<Materiaux> ListMateriaux = materiauxService.getAllMateriauxs();
		request.setAttribute("ListMateriaux", ListMateriaux);
		
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutMateriaux.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String materiauxNom = request.getParameter("materiauxNom");	
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		String valeur = request.getParameter("valeur");
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}	
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				
				materiauxService.removeMateriauxById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				Materiaux materiaux = materiauxService.getMateriauxById(Integer.valueOf(idValeur));
				materiaux.setNom(valeur);
				materiauxService.editMateriaux(materiaux);
				
				// Retour de l'objet modifier sous format json
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print("{ \"id\": \""+materiaux.getMateriauxId()+"\", \"valeur\": \""+materiaux.getNom()+"\" }");
				response.getWriter().flush();
				
			}
		}else if (materiauxNom != null) {						
			if (materiauxNom.trim() != null) {				
				materiauxService.addMateriaux(materiauxNom);
				
				//Definit la reponse comme "See Other" et redirige
				//Evite la multi-insertion après un refresh de l'utilsateur		
				response.setStatus(303);	
				response.sendRedirect(request.getContextPath()+"/Configuration/AjoutMateriaux");
			}
		}else {
			//Post de null part
		}
						
	}

}
