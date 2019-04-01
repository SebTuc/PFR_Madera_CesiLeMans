package com.ril.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Composant;
import com.ril.model.FamilleComposant;
import com.ril.model.Fournisseur;
import com.ril.model.Materiaux;
import com.ril.model.Utilisateur;
import com.ril.service.ComposantService;
import com.ril.service.FamilleComposantService;
import com.ril.service.FournisseurService;
import com.ril.service.MateriauxService;
import com.ril.utils.MethodeUtile;

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
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		List<Composant> ListCompo= composantService.getAllComposants();
		List<FamilleComposant> ListFamilleComposant = familleComposantService.getAllFamilleComposant();
		List<Fournisseur> ListFournisseur = fournisseurService.getAllFournisseurs();
		List<Materiaux> ListMateriaux = materiauxService.getAllMateriauxs();
		List<Composant> list = new ArrayList<Composant>();
		
		String familleComposant = request.getParameter("familleComposant");
		String materiaux = request.getParameter("materiaux");
		//On affiche que les composant display true
		List<Composant> ListComposant = new ArrayList<Composant>();
		if(ListCompo!=null) {
			for(Composant compo : ListCompo) {
				Boolean flag = compo.isDisplay();
				if(flag == null || flag != false) {
					ListComposant.add(compo);
				}
			}
		}
		
		
		//Trie par critere
		if(familleComposant != null && !(familleComposant.equals("-1")) || materiaux != null && !(materiaux.equals("-1"))) {
			if(ListComposant != null) {
				if(MethodeUtile.isInteger(materiaux) && MethodeUtile.isInteger(familleComposant)) {
					for(Composant composant : ListComposant) {
						if(!familleComposant.equals("-1")) {
							if(Integer.valueOf(familleComposant) == composant.getFamilleComposant().getFamilleComposantId()){
								if(!materiaux.equals("-1")) {
									if(Integer.valueOf(materiaux) == composant.getMateriaux().getMateriauxId()){
				
										list.add(composant);
										
									}
								}else {
									list.add(composant);
								}	
							}
						}else if(!materiaux.equals("-1")) {
							if(Integer.valueOf(materiaux) == composant.getMateriaux().getMateriauxId()){
		
								list.add(composant);
								
							}
						}
						
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
		
		if(familleComposant != null && !(familleComposant.equals("")) && materiaux != null && !(materiaux.equals(""))) {
			if(MethodeUtile.isInteger(materiaux) && MethodeUtile.isInteger(familleComposant)) {
				request.setAttribute("familleComposantId", familleComposant);
				request.setAttribute("materiauxId", materiaux);
			}
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
		
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}

		if( btnEditer != null && composantId != null) {

			response.sendRedirect(request.getContextPath()+ "/Configuration/EditComposant?id="+composantId);
			
		}else if( btnSupprimer != null && composantId != null) {
			
			//On supprime uniquement si il n'est pas dans un catalogue ou un devis
			if(MethodeUtile.isInteger(composantId)) {
				Composant composant = composantService.getComposantById(Integer.valueOf(composantId));
				if(!composantService.composantInDevisOrCatalogue(composant)) {
					
					composantService.removeComposant(composant);
					
				}else {
					//Empecher la suppresion ????
					request.setAttribute("Erreur", "Impossible de supprimer se composant, le composant est utilisé dans un ou plusieur devis/catalogue.");
					doGet(request, response);
//					composant.setDisplay(false);
//					composantService.editComposant(composant);
					
				}
				
			}else {
				
				request.setAttribute("Erreur", "L'id est incorrect, si le problème persiste contacter le support.");
			}
			
			doGet(request, response);
			
		}else {
			
			doGet(request, response);
		}
		
		
	}

}
