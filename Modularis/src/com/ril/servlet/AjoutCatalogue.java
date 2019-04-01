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
import javax.servlet.http.HttpSession;

import com.ril.model.Catalogue;
import com.ril.model.Utilisateur;
import com.ril.service.CatalogueService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutGamme
 */
@WebServlet("/AjoutCatalogue")
public class AjoutCatalogue extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private int actualYear = LocalDate.now().getYear();
	private int[] availableYears = new int[] {actualYear+5,actualYear+4,actualYear+3,actualYear+2,actualYear+1,actualYear,actualYear-1,actualYear-2};
	
	private CatalogueService catalogueService = new CatalogueService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		List<Catalogue> listCatalogue = catalogueService.getAllCatalogues();
		
		request.setAttribute("ListCatalogue", listCatalogue);
		request.setAttribute("years", availableYears);
		
		request.getRequestDispatcher("/jsp/application/Catalogue/AjoutCatalogue.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String catalogueNom = request.getParameter("catalogueNom");
		String catalogueAnnee = request.getParameter("catalogueAnnee");
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		String valeur = request.getParameter("valeur");
		String year = request.getParameter("year");
				
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				
				catalogueService.removeCatalogueById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				if (valeur != "" && isInteger(year)) {					
					Catalogue catalogue = catalogueService.getCatalogueById(Integer.valueOf(idValeur));
					catalogue.setCatalogueNom(valeur);
					catalogue.setAnnee(Integer.parseInt(year));
					catalogueService.editCatalogue(catalogue);
					
					// Retour de l'objet modifier sous format json
					response.setStatus(200);
					response.setContentType("application/json");
					response.getWriter().print("{ \"id\": \""+catalogue.getCatalogueId()+"\", \"valeur\": \""+catalogue.getCatalogueNom()+"\", \"year\": \""+catalogue.getAnnee()+"\" }");
					response.getWriter().flush();
				}
				
			}
		}else if (catalogueNom != null) {						
			if (catalogueNom.trim() != null && isInteger(catalogueAnnee)) {				
				catalogueService.addCatalogue(catalogueNom, Integer.parseInt(catalogueAnnee));
				
				//Definit la reponse comme "See Other" et redirige
				//Evite la multi-insertion apr�s un refresh de l'utilsateur		
				response.setStatus(303);	
				response.sendRedirect(request.getContextPath()+"/Catalogue/AjoutCatalogue");
			}
		}else {
			//Post de null part
		}
						

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
