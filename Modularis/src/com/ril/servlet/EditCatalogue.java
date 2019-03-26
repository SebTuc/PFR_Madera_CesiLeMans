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
 * Servlet implementation class EditComposant
 */
@WebServlet("/EditCatalogue")
public class EditCatalogue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CatalogueService catalogueService = new CatalogueService();
	private ProjetService projetService = new ProjetService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("JsonProjet", this.getJsonSerializedProjets());
		request.setAttribute("JsonCatalogue", this.getJsonSerializedCatalogues());
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

	private String getJsonSerializedProjets() {
		List<Projet> listProjet = projetService.getAllProjets();

		String jsonData = "[";

		if (listProjet != null) {
			int listProjetSize = listProjet.size();

			if (listProjetSize > 0) {
				for (int i = 0; i < listProjetSize - 1; i++) {
					Projet projet = listProjet.get(i);
					jsonData += "{\"text\": \"" + projet.getNom() + "\"," + "\"metadata\": { id: \""
							+ projet.getProjetId() + "\" }," + "\"type\": \"item\"},";
				}

				Projet projet = listProjet.get(listProjetSize - 1);
				jsonData += "{\"text\": \"" + projet.getNom() + "\"," + "\"metadata\": { id: \"" + projet.getProjetId()
						+ "\" }," + "\"type\": \"item\"}";

			}
		}
		jsonData += "]";
		return jsonData;

	}

	private String getJsonSerializedCatalogues() {
		List<Catalogue> listCatalogue = catalogueService.getAllCatalogues();

		String jsonData = "[";

		if (listCatalogue != null) {
			int listCatalogueSize = listCatalogue.size();

			if (listCatalogueSize > 0) {
				for (int i = 0; i < listCatalogueSize - 1; i++) {
					Catalogue catalogue = listCatalogue.get(i);
					List<Projet> projetsCatalogue = catalogue.getProjets().stream().collect(Collectors.toList());
					int listProjetCatalogueSize = projetsCatalogue.size();
					jsonData += "{\"text\": \"" + catalogue.getCatalogueNom() + " - " + catalogue.getAnnee() + "\"," + "\"metadata\": { id: \""
							+ catalogue.getCatalogueId() + "\" }," + "\"type\": \"category\"," + "\"children\":"
							+ this.getJsonSerializedProjetsOfCatalogue(catalogue) + "},";
				}

				Catalogue catalogue = listCatalogue.get(listCatalogueSize - 1);
				jsonData += "{\"text\": \"" + catalogue.getCatalogueNom() + " - " + catalogue.getAnnee() + "\"," + "\"metadata\": { id: \""
						+ catalogue.getCatalogueId() + "\" }," + "\"type\": \"category\"," + "\"children\":"
						+ this.getJsonSerializedProjetsOfCatalogue(catalogue) + "}";

			}
		}

		jsonData += "]";
		return jsonData;
	}

	private String getJsonSerializedProjetsOfCatalogue(Catalogue catalogue) {
		List<Projet> projetsCatalogue = catalogue.getProjets().stream().collect(Collectors.toList());

		String jsonData = "[";

		if (projetsCatalogue != null) {
			int listProjetCatalogueSize = projetsCatalogue.size();

			if (listProjetCatalogueSize > 0) {
				for (int j = 0; j < listProjetCatalogueSize - 1; j++) {
					Projet projetCatalogue = projetsCatalogue.get(j);
					jsonData += "{ \"text\": \"" + projetCatalogue.getNom() + "\"," + "\"metadata\": {\"id\": \""
							+ projetCatalogue.getProjetId() + "\" }," + "\"type\": \"item\"},";
				}

				Projet projetCatalogue = projetsCatalogue.get(listProjetCatalogueSize - 1);
				jsonData += "{ \"text\": \"" + projetCatalogue.getNom() + "\"," + "\"metadata\": {\"id\": \""
						+ projetCatalogue.getProjetId() + "\" }," + "\"type\": \"item\"}";

			}
		}
		jsonData += "]";
		return jsonData;
	}

}
