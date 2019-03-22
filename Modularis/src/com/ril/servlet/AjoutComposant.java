package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.FamilleComposant;
import com.ril.model.Fournisseur;
import com.ril.model.Materiaux;
import com.ril.service.ComposantService;
import com.ril.service.FamilleComposantService;
import com.ril.service.FournisseurService;
import com.ril.service.MateriauxService;

/**
 * Servlet implementation class AjoutComposant
 */
@WebServlet("/AjoutComposant")
public class AjoutComposant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ComposantService composantService = new ComposantService();
	private FamilleComposantService familleComposantService = new FamilleComposantService();
	private FournisseurService fournisseurService = new FournisseurService();
	private MateriauxService materiauxService = new MateriauxService();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<FamilleComposant> ListFamilleComposant = familleComposantService.getAllFamilleComposant();
		List<Fournisseur> ListFournisseur = fournisseurService.getAllFournisseurs();
		List<Materiaux> ListMateriaux = materiauxService.getAllMateriauxs();
		
		
		request.setAttribute("ListFamilleComposant", ListFamilleComposant);
		request.setAttribute("ListFournisseur", ListFournisseur);
		request.setAttribute("ListMateriaux", ListMateriaux);
		
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutComposant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");	
		String prixUnitaire = request.getParameter("prixUnitaire");	
		String familleComposant = request.getParameter("familleComposant");
		String materiaux = request.getParameter("materiaux");	
		String fournisseur = request.getParameter("fournisseur");	
		
		if (nom != null && prixUnitaire != null && familleComposant != null && materiaux != null && fournisseur != null) {						
			if (nom.trim() != null && prixUnitaire.trim() != null && familleComposant.trim() != null && materiaux.trim() != null && fournisseur.trim() != null) {	
				
				//Ajout verif /!\
				FamilleComposant familleCompo = familleComposantService.getFamilleComposantById(Integer.valueOf(familleComposant));
				Fournisseur fourni = fournisseurService.getFournisseurById(Integer.valueOf(fournisseur));
				Materiaux mater = materiauxService.getMateriauxById(Integer.valueOf(materiaux));
				
				
				composantService.addComposant(familleCompo, fourni, mater, nom, Float.valueOf(prixUnitaire));

				//Definit la reponse comme "See Other" et redirige
				//Evite la multi-insertion après un refresh de l'utilsateur		
				response.sendRedirect("/Modularis/Configuration/ListComposant");
			}
		}
	}

}
