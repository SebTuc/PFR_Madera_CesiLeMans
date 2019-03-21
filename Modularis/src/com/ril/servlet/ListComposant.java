package com.ril.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Composant;
import com.ril.model.FamilleComposant;
import com.ril.model.Fournisseur;
import com.ril.model.Materiaux;
import com.ril.service.ComposantService;
import com.ril.service.FamilleComposantService;
import com.ril.service.FournisseurService;
import com.ril.service.MateriauxService;

/**
 * Servlet implementation class ListComposant
 */
@WebServlet("/ListComposant")
public class ListComposant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComposantService composantService = new ComposantService();
	private FamilleComposantService familleComposantService = new FamilleComposantService();
	private FournisseurService fournisseurService = new FournisseurService();
	private MateriauxService materiauxService = new MateriauxService();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Composant> ListComposant= composantService.getAllComposants();
		List<FamilleComposant> ListFamilleComposant = familleComposantService.getAllFamilleComposant();
		List<Fournisseur> ListFournisseur = fournisseurService.getAllFournisseurs();
		List<Materiaux> ListMateriaux = materiauxService.getAllMateriauxs();
		List<Composant> list = new ArrayList<Composant>();
		
		String familleComposant = request.getParameter("familleComposant");
		String materiaux = request.getParameter("materiaux");
		//Trie par critere
		if(familleComposant != null && familleComposant != "-1" || materiaux != null && materiaux != "-1") {
			for(Composant composant : ListComposant) {
				if(familleComposant != "-1") {
					if(Integer.valueOf(familleComposant) == composant.getFamilleComposant().getFamilleComposantId()){
						
						list.add(composant);
						
					}
				}
				if(materiaux != "-1") {
					if(Integer.valueOf(materiaux) == composant.getMateriaux().getMateriauxId()){

						list.add(composant);
						
					}
				}
				
			}
			if(list.size() == 0) {
				request.setAttribute("isEmptyList", true);
			}else {
				request.setAttribute("isEmptyList", false);
			}
			
			request.setAttribute("ListComposant", list);
		}else {
			if(ListComposant == null) {
				
				request.setAttribute("isEmptyList", true);
			}else {
				request.setAttribute("isEmptyList", false);
			}
			request.setAttribute("ListComposant", ListComposant);
			
		}
		
		
		request.setAttribute("ListFamilleComposant", ListFamilleComposant);
		request.setAttribute("ListFournisseur", ListFournisseur);
		request.setAttribute("ListMateriaux", ListMateriaux);
		
		request.getRequestDispatcher("/jsp/application/Configuration/ListComposant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String composantId = request.getParameter("radio");
		String btnEditer = request.getParameter("btnEditer");
		String btnSupprimer = request.getParameter("btnSupprimer");
		
		

		if( btnEditer != null && composantId != null) {

			response.sendRedirect(request.getContextPath()+ "/Configuration/EditComposant?id="+composantId);
			
		}else if( btnSupprimer != null && composantId != null) {
			
			composantService.removeComposantById(Integer.valueOf(composantId));
			doGet(request, response);
			
		}else {
			
			doGet(request, response);
		}
		
		
	}

}
