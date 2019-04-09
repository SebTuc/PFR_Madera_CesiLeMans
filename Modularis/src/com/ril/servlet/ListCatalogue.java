package com.ril.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Catalogue;
import com.ril.service.CatalogueService;

/**
 * Servlet implementation class ListComposant
 */
@WebServlet("/ListCatalogue")
public class ListCatalogue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CatalogueService catalogueService = new CatalogueService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Catalogue> listCatalogue= catalogueService.getAllCatalogues();

		//N'afficher que les catalogue qui continne des projet.
		List<Catalogue> list = new ArrayList<Catalogue>();
		if(listCatalogue.size() != 0) {
			for(Catalogue cat : listCatalogue) {
				if(cat.getProjets().size()!=0) {
					list.add(cat);
				}
			}

			if(list.size() == 0) {
				request.setAttribute("isEmptyList", true);
			}else {
				request.setAttribute("isEmptyList", false);
			}
			request.setAttribute("ListCatalogue", list);
		}else {

			request.setAttribute("isEmptyList", true);

		}





		request.getRequestDispatcher("/jsp/application/Catalogue/ListCatalogue.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



}
