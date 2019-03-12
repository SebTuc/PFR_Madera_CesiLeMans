package com.ril.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.UniteMesure;
import com.ril.service.UniteMesureService;

public class UniteMesureEdit extends HttpServlet {

	private UniteMesureService uniteMesureService = new UniteMesureService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uniteId = req.getParameter("buttonEdit");			
		
		if(uniteId != null) {
			UniteMesure uniteMesure = uniteMesureService.getUniteMesureById(Integer.parseInt(uniteId));
			req.setAttribute("uniteMesure", uniteMesure);
			req.getRequestDispatcher("/jsp/application/Configuration/editUniteMesure.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("/jsp/application/common/error.jsp").forward(req, resp);
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uniteId = req.getParameter("uniteId");
		String uniteNom = req.getParameter("uniteNom");
		
		if (uniteId !=null && uniteNom != null) {
			UniteMesure uniteMesure = uniteMesureService.getUniteMesureById(Integer.parseInt(uniteId));
			uniteMesure.setNomUnite(uniteNom);
			uniteMesureService.editUniteMesure(uniteMesure);
		}
		
		resp.sendRedirect(req.getContextPath() + "/Configuration/UniteMesureList");
	}
}
