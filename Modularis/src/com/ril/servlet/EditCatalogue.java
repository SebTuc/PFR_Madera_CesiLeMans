package com.ril.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Catalogue;
import com.ril.model.Projet;
import com.ril.model.Utilisateur;
import com.ril.service.CatalogueService;
import com.ril.service.ProjetService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class EditComposant
 */
@WebServlet("/EditCatalogue")
public class EditCatalogue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CatalogueService catalogueService = new CatalogueService();
	private ProjetService projetService = new ProjetService();
	
	private List<Catalogue> listCatalogue;
	private List<Projet> listProjet;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		// Premierer recuperation de tous les projets et catalogues
		this.listCatalogue =  catalogueService.getAllCatalogues();
		List<Projet> ListProjet = projetService.getAllProjets();
		List<Projet> ListTemp = new ArrayList<Projet>();
		//On affiche pas les projet clonner ? parce que c'est trop le bazar ou alors ceux qui on des nom different ?
		if(ListProjet!=null) {
			for(Projet projet : ListProjet) {
				Boolean flag = projet.isClone();
				if(flag == null || flag != true) {
					ListTemp.add(projet);
				}
				
			}
		}
		
		this.listProjet = ListTemp;
		request.setAttribute("JsonProjet", this.getJsonSerializedProjets().toString());
		request.setAttribute("JsonCatalogue", this.getJsonSerializedCatalogues().toString());
		request.getRequestDispatcher("/jsp/application/Catalogue/EditCatalogue.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idProjet = request.getParameter("idProjet");
		String idCatalogue = request.getParameter("idCatalogue");
		String action = request.getParameter("action");
		String error = null;
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		if (action != null && action != "" ) {
			if(isInteger(idProjet)) {
				if(isInteger(idCatalogue)) {
															
					// Recuperation du catalogue et du projet puis link du projet dans catalogue
					Catalogue catalogue = catalogueService.getCatalogueById(Integer.parseInt(idCatalogue));
					Projet projet = projetService.getProjetById(Integer.parseInt(idProjet));
	
					if(projet != null) {
						if (catalogue != null) {
												
							switch (action) {
								case "Push":
									
									catalogueService.addProjetToCatalogue(catalogue, projet);
									break;
									
								case "Withdraw":					
									
									catalogueService.removeProjetFromCatalogue(catalogue, projet);
									break;
					
								default:
									break;
							}
						}else {error = "Aucun catalogue trouvé avec l'id "+idCatalogue;}
					}else {error = "Aucun projet trouvé avec l'id "+idProjet;}
						
					// Retour des projets
					response.setStatus(200);
					response.setContentType("application/json");
					response.getWriter().print(this.getJsonSerializedProjets().toString());
					response.getWriter().flush();	
					
				} else {error = "l'id du catalogue non n'est pas un entier";}
			} else {error = "l'id du projet non n'est pas un entier";}
		} else {error = "Action inconnue";}

		
		// Renvoir d'une erreur
		if (error != null) {			
			response.setStatus(400);
			response.setContentType("application/json");
			response.getWriter().print("[{ \"error\": \""+error+"\"}]");
			response.getWriter().flush();
		}
	}

	private JsonArray getJsonSerializedProjets() {
		
		// Recuperation des projets sans catalogue
		JsonArrayBuilder jsonProjets = Json.createArrayBuilder();
		
		if (this.listProjet != null && this.listProjet.size() > 0) {			
			for (Projet projet : this.listProjet) {
				jsonProjets
					.add(Json.createObjectBuilder()
							.add("text", projet.getNom())
							.add("metadata", Json.createObjectBuilder()
									.add("id", projet.getProjetId()))
							.add("type","item"));
			}					
		}
		
		return jsonProjets.build();
	}

	private JsonArray getJsonSerializedCatalogues() {
		JsonArrayBuilder jsonCatalogues = Json.createArrayBuilder();
		
		if (listCatalogue != null && listCatalogue.size() > 0) {
			for (Catalogue catalogue : listCatalogue) {
				jsonCatalogues
				.add(Json.createObjectBuilder()
						.add("text", catalogue.getCatalogueNom() + " - " + catalogue.getAnnee())
						.add("metadata", Json.createObjectBuilder()
								.add("id", catalogue.getCatalogueId()))
						.add("type","category")
						.add("children", this.getJsonSerializedProjetsOfCatalogue(catalogue)));
			}
		}
		
		return jsonCatalogues.build();
	}

	private JsonArray getJsonSerializedProjetsOfCatalogue(Catalogue catalogue) {
		List<Projet> listProjetsCatalogue = catalogue.getProjets().stream().collect(Collectors.toList());
		JsonArrayBuilder jsonProjetsCatalogue = Json.createArrayBuilder();
		
		if(listProjetsCatalogue != null && listProjetsCatalogue.size() > 0) {
			for (Projet projetCatalogue : listProjetsCatalogue) {
				jsonProjetsCatalogue
				.add(Json.createObjectBuilder()
						.add("text", projetCatalogue.getNom())
						.add("metadata", Json.createObjectBuilder()
								.add("id", projetCatalogue.getProjetId()))
						.add("type","item"));
			}
		}
		
		return jsonProjetsCatalogue.build();				
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
