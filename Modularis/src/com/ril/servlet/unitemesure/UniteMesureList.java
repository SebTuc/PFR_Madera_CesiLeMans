package com.ril.servlet.unitemesure;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Metier;
import com.ril.model.UniteMesure;
import com.ril.service.UniteMesureService;

public class UniteMesureList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UniteMesureService uniteMesureService = new UniteMesureService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<UniteMesure> uniteMesures = uniteMesureService.getAllUniteMesures();
		req.setAttribute("uniteMesures", uniteMesures);
		
		req.getRequestDispatcher("/jsp/application/uniteMesure/listUniteMesure.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
			
		String uniteMesureNom = req.getParameter("uniteNom");		
		
		//Ajout
		if(uniteMesureNom.trim() != "") uniteMesureService.addUniteMesure(uniteMesureNom);					
						
		//Definit la reponse comme "See Other" et redirige
		//Evite la multi-insertion après un refresh de l'utilsateur		
		resp.setStatus(303);	
		resp.sendRedirect(req.getContextPath()+"/UniteMesureList");
	}

}
