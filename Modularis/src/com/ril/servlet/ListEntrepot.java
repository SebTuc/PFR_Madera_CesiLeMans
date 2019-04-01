package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Entrepot;
import com.ril.service.EntrepotService;

/**
 * Servlet implementation class ListEntrepot
 */
@WebServlet("/ListEntrepot")
public class ListEntrepot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private EntrepotService entrepotService = new EntrepotService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Entrepot> ListEntrepot = entrepotService.getAllEntrepots();
		request.setAttribute("ListEntrepot", ListEntrepot);
		request.getRequestDispatcher("/jsp/application/Stocks/ListEntrepot.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
