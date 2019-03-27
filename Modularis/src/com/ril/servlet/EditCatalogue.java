package com.ril.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.*;
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
 * Servlet implementation class EditComposant
 */
@WebServlet("/EditCatalogue")
public class EditCatalogue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CatalogueService catalogueService = new CatalogueService();
	private ProjetService projetService = new ProjetService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		
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
	}

	private JsonArray getJsonSerializedProjets() {
		
		// Recuperation des projets sans catalogue
		List<Projet> listProjet = 
				projetService.getAllProjets().stream()
								.filter(p -> p.getCatalogue() == null).collect(Collectors.toList());

		JsonArrayBuilder jsonProjets = Json.createArrayBuilder();
		
		if (listProjet != null && listProjet.size() > 0) {			
			for (Projet projet : listProjet) {
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
		List<Catalogue> listCatalogue = catalogueService.getAllCatalogues();
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

}
