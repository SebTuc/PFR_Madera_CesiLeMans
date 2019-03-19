package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Fournisseur;
import com.ril.service.FournisseurService;

/**
 * Servlet implementation class ListFournisseur
 */
@WebServlet("/ListFournisseur")
public class ListFournisseur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private FournisseurService fournisseurService = new FournisseurService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Fournisseur> ListFournisseur = fournisseurService.getAllFournisseurs();
		request.setAttribute("ListFournisseur", ListFournisseur);
		request.getRequestDispatcher("/jsp/application/Annuaire/ListFournisseur.jsp").forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
