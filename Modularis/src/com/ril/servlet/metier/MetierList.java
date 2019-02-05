package com.ril.servlet.metier;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Metier;
import com.ril.service.MetierService;

public class MetierList extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Map<String,String[]> lastPost = null;
	private MetierService metierService = new MetierService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Metier> metiers = metierService.getAllMetiers();
		req.setAttribute("metiers", metiers);
		
		req.getRequestDispatcher("/jsp/application/metier/listMetier.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
			
		String metierNom = req.getParameter("metierNom");		
		
		//Ajout
		if(metierNom.trim() != null) metierService.addMetier(metierNom);					
						
		//Definit la reponse comme "See Other" et redirige
		//Evite la multi-insertion après un refresh de l'utilsateur		
		resp.setStatus(303);	
		resp.sendRedirect(req.getContextPath()+"/MetierList");
	}

}
