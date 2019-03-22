package com.ril.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Catalogue;
import com.ril.service.CatalogueService;

/**
 * Servlet implementation class AjoutGamme
 */
@WebServlet("/AjoutCatalogue")
public class AjoutCatalogue extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private CatalogueService catalogueService = new CatalogueService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Catalogue> listCatalogue = catalogueService.getAllCatalogues();
		
		request.setAttribute("ListCatalogue", listCatalogue);
		
		request.getRequestDispatcher("/jsp/application/Catalogue/AjoutCatalogue.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String catalogueNom = request.getParameter("catalogueNom");
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		String valeur = request.getParameter("valeur");
				
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				
				catalogueService.removeCatalogueById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				Catalogue catalogue = catalogueService.getCatalogueById(Integer.valueOf(idValeur));
				catalogue.setCatalogueNom(valeur);
				catalogueService.editCatalogue(catalogue);
								
				// Retour de l'objet modifier sous format json
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print("{ \"id\": \""+catalogue.getCatalogueId()+"\", \"valeur\": \""+catalogue.getCatalogueNom()+"\", \"year\": \""+catalogue.getAnnee()+"\" }");
				response.getWriter().flush();
				
			}
		}else if (catalogueNom != null) {						
			if (catalogueNom.trim() != null) {				
				catalogueService.addCatalogue(catalogueNom, LocalDate.now().getYear());
				
				//Definit la reponse comme "See Other" et redirige
				//Evite la multi-insertion apr�s un refresh de l'utilsateur		
				response.setStatus(303);	
				response.sendRedirect(request.getContextPath()+"/Catalogue/AjoutCatalogue");
			}
		}else {
			//Post de null part
		}
						

	}

}
