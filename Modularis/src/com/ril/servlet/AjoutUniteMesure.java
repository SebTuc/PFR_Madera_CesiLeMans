package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.UniteMesure;
import com.ril.model.Utilisateur;
import com.ril.service.UniteMesureService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutUniteMesure
 */
@WebServlet("/AjoutUniteMesure")
public class AjoutUniteMesure extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UniteMesureService uniteMesureService = new UniteMesureService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("SessionUtilisateur"));
		}
		List<UniteMesure> uniteMesure = uniteMesureService.getAllUniteMesures();
		
		request.setAttribute("ListUniteMesure", uniteMesure);
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutUniteMesure.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uniteMesureNom = request.getParameter("uniteMesureNom");
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		String valeur = request.getParameter("valeur");
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("SessionUtilisateur"));
		}
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				
				uniteMesureService.removeUniteMesureById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				UniteMesure uniteMesure = uniteMesureService.getUniteMesureById(Integer.valueOf(idValeur));
				uniteMesure.setNomUnite(valeur);
				uniteMesureService.editUniteMesure(uniteMesure);
								
				// Retour de l'objet modifier sous format json
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print("{ \"id\": \""+uniteMesure.getUniteId()+"\", \"valeur\": \""+uniteMesure.getNomUnite()+"\" }");
				response.getWriter().flush();
				
			}
		}else if (uniteMesureNom != null) {						
			if (uniteMesureNom.trim() != null) {				
				uniteMesureService.addUniteMesure(uniteMesureNom);
				
				//Definit la reponse comme "See Other" et redirige
				//Evite la multi-insertion apr�s un refresh de l'utilsateur		
				response.setStatus(303);	
				response.sendRedirect(request.getContextPath()+"/Configuration/AjoutUniteMesure");
			}
		}else {
			//Post de null part
		}
						
	}

}
