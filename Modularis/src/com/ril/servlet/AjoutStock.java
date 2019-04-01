package com.ril.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Entrepot;
import com.ril.model.Stock;
import com.ril.service.ComposantService;
import com.ril.service.EntrepotService;
import com.ril.service.StockService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutStock
 */
@WebServlet("/AjoutStock")
public class AjoutStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EntrepotService entrepotService 	= new EntrepotService();
	private ComposantService composantService 	= new ComposantService();
	private StockService stockService			= new StockService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String entrepotId = request.getParameter("id");
		if(MethodeUtile.isInteger(entrepotId)) {
			 Entrepot entrepot = entrepotService.getEntrepotById(Integer.valueOf(entrepotId));
			 
		        request.setAttribute("Entrepot", entrepot);
				request.getRequestDispatcher("/jsp/application/Stocks/AjoutStock.jsp").forward(request, response);
		}else {
			response.sendRedirect("/Modularis/GestionStocks/ListEntrepot");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
