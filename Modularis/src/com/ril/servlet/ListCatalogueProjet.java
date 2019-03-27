package com.ril.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Catalogue;
import com.ril.model.Projet;
import com.ril.service.CatalogueService;
import com.ril.service.ProjetService;

/**
 * Servlet implementation class ListComposant
 */
@WebServlet("/ListCatalogueProjet")
public class ListCatalogueProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CatalogueService catalogueService = new CatalogueService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idCatalogue = request.getParameter("idCatalogue");
		
		if(idCatalogue != "" && isInteger(idCatalogue)) {
			Catalogue parentCatalogue = catalogueService.getCatalogueById(Integer.parseInt(idCatalogue));
			List<Projet> listProjet = parentCatalogue.getProjets().stream().collect(Collectors.toList());
			
			if(listProjet != null) {				
				request.setAttribute("Catalogue", parentCatalogue);
				request.setAttribute("ListProjet", listProjet);
				request.getRequestDispatcher("/jsp/application/Catalogue/ListCatalogueProjet.jsp").forward(request, response);
			}else {
				response.sendRedirect("/Modularis/Catalogue/ListCatalogue");
			}
			
		}else {			
			response.sendRedirect("/Modularis/Catalogue/ListCatalogue");
		}
		
	}
						
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private static boolean isFloat(String s) {
	    try { 
	        Float.parseFloat(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	private static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	

}
