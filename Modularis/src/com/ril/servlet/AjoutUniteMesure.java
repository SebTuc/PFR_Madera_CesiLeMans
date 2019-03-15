package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.UniteMesure;
import com.ril.service.UniteMesureService;

/**
 * Servlet implementation class AjoutUniteMesure
 */
@WebServlet("/AjoutUniteMesure")
public class AjoutUniteMesure extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UniteMesureService uniteMesureService = new UniteMesureService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<UniteMesure> uniteMesure = uniteMesureService.getAllUniteMesures();
		
		request.setAttribute("ListUniteMesure", uniteMesure);
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutUniteMesure.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uniteMesureNom = request.getParameter("uniteMesureNom");
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		String valeur = request.getParameter("valeur");
				
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				
				uniteMesureService.removeUniteMesureById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				UniteMesure uniteMesure = uniteMesureService.getUniteMesureById(Integer.valueOf(idValeur));
				uniteMesure.setNomUnite(valeur);
				uniteMesureService.editUniteMesure(uniteMesure);
				
			}
		}else if (uniteMesureNom != null) {						
			if (uniteMesureNom.trim() != null) {				
				uniteMesureService.addUniteMesure(uniteMesureNom);
			}
		}else {
			//Post de null part
		}
						
		//Definit la reponse comme "See Other" et redirige
		//Evite la multi-insertion après un refresh de l'utilsateur		
		response.setStatus(303);	
		response.sendRedirect(request.getContextPath()+"/Configuration/AjoutUniteMesure");
	}

}
