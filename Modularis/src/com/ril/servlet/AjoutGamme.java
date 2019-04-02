package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Gamme;
import com.ril.service.GammeService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutGamme
 */
@WebServlet("/AjoutGamme")
public class AjoutGamme extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private GammeService gammeService = new GammeService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Gamme> listGamme = gammeService.getAllGammes();
		
		request.setAttribute("ListGamme", listGamme);
		
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutGamme.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gammeNom = request.getParameter("gammeNom");
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		String valeur = request.getParameter("valeur");

		
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				
				gammeService.removeGammeById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				if(MethodeUtile.isInteger(idValeur)) {
					Gamme gamme = gammeService.getGammeById(Integer.valueOf(idValeur));
					gamme.setNom(valeur);
					gammeService.editGamme(gamme);
									
					// Retour de l'objet modifier sous format json
					response.setStatus(200);
					response.setContentType("application/json");
					response.getWriter().print("{ \"id\": \""+gamme.getGammeId()+"\", \"valeur\": \""+gamme.getNom()+"\" }");
					response.getWriter().flush();
				}else {
					response.setStatus(400);
				}
				
				
			}
		}else if (gammeNom != null) {						
			if (!gammeNom.equals("")) {				
				
				gammeService.addGamme(gammeNom);
				
				//Definit la reponse comme "See Other" et redirige
				//Evite la multi-insertion apr�s un refresh de l'utilsateur		
				response.setStatus(303);	
				response.sendRedirect(request.getContextPath()+"/Configuration/AjoutGamme");
			}else {
				request.setAttribute("Erreur", "Veuillez saisir un nom.");
				doGet(request,response);
			}
		}else {
			doGet(request,response);
		}
						

	}

}
