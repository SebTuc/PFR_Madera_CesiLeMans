package com.ril.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Client;
import com.ril.model.Etat;
import com.ril.model.Module;
import com.ril.model.ModuleXComposant;
import com.ril.model.Piece;
import com.ril.model.Plan;
import com.ril.model.Projet;
import com.ril.model.Utilisateur;
import com.ril.service.ClientService;
import com.ril.service.DevisService;
import com.ril.service.EtatService;
import com.ril.service.ProjetService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class ListProjet
 */
@WebServlet("/ListProjet")
public class ListProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProjetService projetService = new ProjetService();
	private ClientService clientService = new ClientService();
	private DevisService devisService = new DevisService();
	private EtatService etatService = new EtatService();


	public float getPrixTotalDuProjet(Projet projet) {

		float prixTotal = 0.0f;

		for(Plan plan : projet.getPlans()) {
			for(Piece piece : plan.getPieces()) {
				for(Module module : piece.getModules()) {
					if(module.getAngle() != null) {
						prixTotal+= module.getAngle().getPrixUnitaire();
					}
					for(ModuleXComposant modXComp : module.getModuleXComposants()) {
						int quantite = modXComp.getQuantite();
						float prixUnitaire = modXComp.getComposant().getPrixUnitaire();

						prixTotal+=(prixUnitaire * (float)quantite);

					}
				}
			}
		}


		return prixTotal;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Projet> ListProjet = projetService.getAllProjets();
		List<Client> ListClient = clientService.getAllClients();
		List<Projet> List = new ArrayList<Projet>();

		if(ListProjet != null) {
			for(Projet projet : ListProjet) {
				//On ne prend que les projet qui ne sont pas en devis ou qui ne sont pas dans le catalogue
				if(projet.getCatalogue().size() == 0) {
					if(projet.getDevises().size() == 0) {

						List.add(projet);

					}
				}
			}
			if(List.size() == 0) {
				request.setAttribute("isEmptyList", true);
			}else {
				request.setAttribute("isEmptyList", false);
			}

			request.setAttribute("ListProjet", List);
		}else {

			request.setAttribute("isEmptyList", true);
			request.setAttribute("ListProjet", ListProjet);
		}

		request.setAttribute("ListClient", ListClient);

		request.getRequestDispatcher("/jsp/application/DevisProjetFacture/ListProjet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projetId = request.getParameter("radio");
		String btnEditer = request.getParameter("btnEditer");
		String btnSupprimer = request.getParameter("btnSupprimer");
		String btnEditerProjet = request.getParameter("btnEditerProjet");
		String clientId = request.getParameter("clientId");

		if( btnEditer != null && projetId != null) {
			if(MethodeUtile.isInteger(projetId)) {
				response.sendRedirect(request.getContextPath()+ "/DevisFacture/EditProjet?id="+projetId);
			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}

		}else if( btnSupprimer != null && projetId != null) {
			if(MethodeUtile.isInteger(projetId)) {


				projetService.removeProjetById(Integer.valueOf(projetId));

				doGet(request, response);
			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}


		}else if( btnSupprimer==null && btnEditer == null && btnEditerProjet == null && projetId != null && clientId != null) {

			//On Cr�er un devis pour sa on calcul le prix de tout HT et on recup la date du jour et bien sur le projet
			if(MethodeUtile.isInteger(projetId) && MethodeUtile.isInteger(clientId)) {
				Projet projet = projetService.getProjetById(Integer.valueOf(projetId));
				Date date = new Date();
				HttpSession session = request.getSession(false);
				Utilisateur utilisateur = (Utilisateur) session.getAttribute("SessionUtilisateur");
				//OU
				//				Utilisateur utilisateur = session.getAttribute("Utilisateur");
				Client client = clientService.getClientById(Integer.valueOf(clientId));
				Float prixTotal = getPrixTotalDuProjet(projet);
				//Letat devis on le met a brouillon au debut
				Etat etat = etatService.getEtatById(1);
				//On creer le devis avec toute ces info
				try {

					Integer devisId = devisService.addDevis(client, etat, projet, utilisateur, prixTotal, date);

					//on envois sur le devis
					response.sendRedirect(request.getContextPath()+ "/DevisFacture/DetailDevis?id="+devisId);

				}catch(Exception e) {
					request.setAttribute("Erreur", "Creation abort.");
					doGet(request, response);
				}
			}else {
				//On renvois vers le devis
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}

		}else if( btnEditerProjet != null && projetId != null) {
			if(MethodeUtile.isInteger(projetId)) {
				response.sendRedirect(request.getContextPath()+ "/DevisFacture/EditProjetName?id="+projetId);

			}else {
				request.setAttribute("Erreur", "Projet ID n'est pas un chiffre, si le probleme persiste, contacter le support.");
				doGet(request, response);
			}
		}else {
			request.setAttribute("Erreur", "Veuillez saisir un projet.");
			doGet(request, response);

		}


	}

}
