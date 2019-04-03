package com.ril.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Entrepot;
import com.ril.model.Metier;
import com.ril.model.Utilisateur;
import com.ril.service.EntrepotService;
import com.ril.service.MetierService;
import com.ril.service.UtilisateurService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutUtilisateur
 */
@WebServlet("/EditUtilisateur")
public class EditUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtilisateurService utilisateurService = new UtilisateurService();
	private MetierService metierService = new MetierService(); 
	private EntrepotService entrepotService = new EntrepotService(); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idUtilisateur = request.getParameter("id");
		Utilisateur utilisateurConnected;
		Utilisateur utilisateurToEdit = null;

		String error = "";
		boolean isAdmin = false;

		
		HttpSession session = request.getSession(false);
		if(session != null) {
			utilisateurConnected = (Utilisateur)session.getAttribute("SessionUtilisateur");
			if (utilisateurConnected.getMetier().getNom().equals("Moderateur"))	{
				isAdmin = true;
			}
		
		request.setAttribute("Utilisateur", utilisateurConnected);
		

		if(idUtilisateur != null) {	
			if(MethodeUtile.isInteger(idUtilisateur)) {
				if (isAdmin || utilisateurConnected.getUtilisateurId() == Integer.parseInt(idUtilisateur)){						
					utilisateurToEdit = utilisateurService.getUtilisateurById(Integer.parseInt(idUtilisateur));
				} else { error = "Vous n'avez pas l'autorisation d'acceder à cette page"; }
			} else {	error = "Id non valide"; }
		} else {
			utilisateurToEdit = utilisateurService.getUtilisateurById(utilisateurConnected.getUtilisateurId());
		}

		if (!error.isEmpty()) {
			request.setAttribute("Error", error);
			request.getRequestDispatcher("/jsp/common/error.jsp").forward(request, response);
			return;
		}

		List<Entrepot> ListEntrepot = entrepotService.getAllEntrepots();

		List<Metier> ListMetier = null;

		if(isAdmin) {
			ListMetier = metierService.getAllMetiers();
		}else {
			ListMetier = metierService.getAllMetiers().stream()
					.filter(e -> !e.getNom().equals("Moderateur"))
					.collect(Collectors.toList());
		}

		
		request.setAttribute("UtilisateurSelected", utilisateurToEdit);
		request.setAttribute("ListMetier", ListMetier);
		request.setAttribute("ListEntrepot", ListEntrepot);

		request.getRequestDispatcher("/jsp/application/Annuaire/EditUtilisateur.jsp").forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"Modularis/");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String adresse = request.getParameter("adresse");	
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String telephone = request.getParameter("telephone");	
		String email = request.getParameter("email");
		String metier = request.getParameter("metier");
		String entrepot = request.getParameter("entrepot");
		String login = request.getParameter("login");
		String updatePassword = request.getParameter("updatePassword");		
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");


		HttpSession session = request.getSession();
		request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("SessionUtilisateur"));


		if (MethodeUtile.isInteger(id)) {

			// rajouter verife du reste
			if (nom != null) {						
				if (nom.trim() != null) {	
					if(telephone == null)telephone = "";
					if(adresse == null)adresse = "";
					if(email == null)email = "";
					if(codePostal == null)codePostal = "";
					if(ville == null)ville = "";
					if(login == null)login = "";
					if(prenom == null)prenom = "";

					Utilisateur userToUpdate = utilisateurService.getUtilisateurById(Integer.parseInt(id));

					if (userToUpdate != null) {

						userToUpdate.getDonneesPersonelle().setNom(nom);
						userToUpdate.getDonneesPersonelle().setPrenom(prenom);
						userToUpdate.getDonneesPersonelle().setTelephone(telephone);
						userToUpdate.getDonneesPersonelle().setAdresse(adresse);
						userToUpdate.getDonneesPersonelle().setEmail(email);
						userToUpdate.getDonneesPersonelle().setCodePostal(codePostal);
						userToUpdate.getDonneesPersonelle().setVille(ville);

						userToUpdate.setLogin(login);


						if(metier != null && MethodeUtile.isInteger(metier)) {
							Metier metierFound = metierService.getMetierById(Integer.parseInt(metier));
							userToUpdate.setMetier(metierFound);
						}

						if(entrepot != null && MethodeUtile.isInteger(entrepot)) {
							Entrepot entrepotFound = entrepotService.getEntrepotById(Integer.parseInt(entrepot));
							userToUpdate.setEntrepot(entrepotFound);
						}

						if(updatePassword != null) {
							if (!password.equals("") && !confirmPassword.equals("") && password != null && confirmPassword != null){

								if(!password.equals(confirmPassword)) {

									//retour sur la meme page mais remettre les valeur saisie et signaler que la saisie est incorrect
									request.setAttribute("Erreur", "Le mot de passe ne correspont pas.");
									doGet(request, response);
								}else {
									userToUpdate.setPassword(password);
								}
							} else {
								//retour sur la meme page mais remettre les valeur saisie et signaler que la saisie est incorrect
								request.setAttribute("Erreur", "Les mot de passe sont vides");
								doGet(request, response);
							}

						}

						utilisateurService.editUtilisateur(userToUpdate);
					}

					//Definit la reponse comme "See Other" et redirige
					//Evite la multi-insertion aprï¿½s un refresh de l'utilsateur		
					response.sendRedirect("/Modularis/Annuaire");

				}
			}
		}
	}

}
