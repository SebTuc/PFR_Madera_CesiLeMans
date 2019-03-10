package com.ril.servlet.metier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Metier;
import com.ril.service.MetierService;

public class MetierEdit extends HttpServlet {

	private MetierService metierService = new MetierService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String metierId = req.getParameter("buttonEdit");			
		
		if(metierId != null) {
			Metier metier = metierService.getMetierById(Integer.parseInt(metierId));
			req.setAttribute("metier", metier);
			req.getRequestDispatcher("/jsp/application/metier/editMetier.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("/jsp/application/common/error.jsp").forward(req, resp);
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String metierId = req.getParameter("metierId");
		String metierNom = req.getParameter("metierNom");
		
		if (metierId !=null && metierNom != null) {
			Metier metier = metierService.getMetierById(Integer.parseInt(metierId));
			metier.setNom(metierNom);
			metierService.editMetier(metier);
		}
		
		resp.sendRedirect(req.getContextPath() + "/Configuration/MetierList");
	}
}
