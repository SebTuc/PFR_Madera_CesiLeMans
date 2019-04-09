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

		String nomFournisseur = request.getParameter("nomFournisseur");
		String idValeur = request.getParameter("id");
		String adresseFournisseur = request.getParameter("adresseFournisseur");
		String codePostalFournisseur = request.getParameter("codePostalFournisseur");
		String telephoneFournisseur = request.getParameter("telephoneFournisseur");
		String emailFournisseur = request.getParameter("emailFournisseur");
		String action = request.getParameter("action");

		if(action != null) {

			if(action.equals("Edition")) {

				Fournisseur fournisseur = fournisseurService.getFournisseurById(Integer.valueOf(idValeur));

				//pour le reste c'est une erreur ! 
				if(telephoneFournisseur == null) telephoneFournisseur = "";
				if(emailFournisseur == null) emailFournisseur = "";

				fournisseur.setNom(nomFournisseur);
				fournisseur.setAdresse(adresseFournisseur);
				fournisseur.setCodePostal(codePostalFournisseur);

				fournisseur.setTelephone(telephoneFournisseur);
				fournisseur.setEmail(emailFournisseur);

				fournisseurService.editFournisseur(fournisseur);

				// Retour de l'objet modifier sous format json
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print("{ \"id\": \""+fournisseur.getFournisseurId()+"\", \"nomFournisseur\": \""+fournisseur.getNom()+"\", \"adresseFournisseur\": \""+fournisseur.getAdresse()+"\", \"codePostalFournisseur\": \""+fournisseur.getCodePostal()+"\", \"telephoneFournisseur\": \""+fournisseur.getTelephone()+"\", \"emailFournisseur\": \""+fournisseur.getEmail()+"\" }");
				response.getWriter().flush();

			}
		}
	}

}
