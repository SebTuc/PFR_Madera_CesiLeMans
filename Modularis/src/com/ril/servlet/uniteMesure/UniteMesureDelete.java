package com.ril.servlet.uniteMesure;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Metier;
import com.ril.model.UniteMesure;
import com.ril.service.MetierService;
import com.ril.service.UniteMesureService;

public class UniteMesureDelete extends HttpServlet {

	private UniteMesureService uniteMesureService = new UniteMesureService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uniteId = req.getParameter("buttonDelete");			
		
		if(uniteId != null) {
			UniteMesure uniteMesure = uniteMesureService.getUniteMesureById(Integer.parseInt(uniteId));
			req.setAttribute("uniteMesure", uniteMesure);
			req.getRequestDispatcher("/jsp/application/uniteMesure/deleteUniteMesure.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("/jsp/application/common/error.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uniteId = req.getParameter("uniteId");
		
		//Delete
		if(uniteId != null) {				
			uniteMesureService.removeUniteMesureById(Integer.parseInt(uniteId));
		}
		
		resp.sendRedirect(req.getContextPath() + "/UniteMesureList");
	}

	
}
