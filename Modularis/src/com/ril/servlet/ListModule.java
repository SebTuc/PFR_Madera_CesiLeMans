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
import com.ril.model.Gamme;
import com.ril.model.Module;
import com.ril.service.ComposantService;
import com.ril.service.GammeService;
import com.ril.service.ModuleService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class ListModule
 */
@WebServlet("/ListModule")
public class ListModule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModuleService moduleService = new ModuleService();
	private GammeService gammeService = new GammeService();	
	private ComposantService composantService = new ComposantService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Module> ListMod = moduleService.getAllModules();
		List<Gamme> ListGamme = gammeService.getAllGammes();
		List<Composant> ListComposant = composantService.getAllComposants();

		List<Module> list = new ArrayList<Module>();

		List<Module> ListModule = new ArrayList<Module>();

		if(ListMod!=null) {
			for(Module mod : ListMod) {
				Boolean flag = mod.isDisplay();
				if(flag == null || flag != false) {
					ListModule.add(mod);
				}
			}
		}

		String gamme = request.getParameter("gamme");
		String nomModule = request.getParameter("nomModule");
		//Trie par critere
		if(gamme != null && !(gamme.equals("-1")) || nomModule != null && !(nomModule.equals(""))) {
			if(ListModule != null) {
				if(MethodeUtile.isInteger(gamme)) {
					for(Module module : ListModule) {
						if(!gamme.equals("-1")) {
							if(Integer.valueOf(gamme) == module.getGamme().getGammeId()){
								if(!nomModule.equals("")) {
									if(MethodeUtile.findContains(nomModule,module.getNom())){

										list.add(module);

									}
								}else {
									list.add(module);
								}	
							}
						}else if(!nomModule.equals("")) {
							if(MethodeUtile.findContains(nomModule,module.getNom())){

								list.add(module);

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

			request.setAttribute("ListModule", list);
		}else {
			if(ListModule.size() == 0) {

				request.setAttribute("isEmptyList", true);
			}else {
				request.setAttribute("isEmptyList", false);
			}
			request.setAttribute("ListModule", ListModule);

		}

		if(nomModule != null && gamme != null && !(gamme.equals(""))) {
			if(MethodeUtile.isInteger(gamme)) {
				request.setAttribute("gammeId", gamme);
				request.setAttribute("nomModule", nomModule);
			}
		}


		request.setAttribute("ListGamme", ListGamme);
		request.setAttribute("ListComposant", ListComposant);

		request.getRequestDispatcher("/jsp/application/Configuration/ListModule.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String moduleId = request.getParameter("radio");
		String btnEditer = request.getParameter("btnEditer");
		String btnSupprimer = request.getParameter("btnSupprimer");

		if( btnEditer != null && moduleId != null) {

			response.sendRedirect(request.getContextPath()+ "/Configuration/EditModule?id="+moduleId);

		}else if( btnSupprimer != null && moduleId != null) {
			if(MethodeUtile.isInteger(moduleId)) {
				Module module = moduleService.getModuleById(Integer.valueOf(moduleId));

				//Si le module est dans un catalogue ou devis on le supprime pas on le display false 
				if(moduleService.moduleInDevisOrCatalogue(module)) {

					module.setDisplay(false);
					moduleService.editModule(module);
					//Supprimer le module des projet en cours de cr�ation.
					moduleService.removeAllModuleInProjetEditableById(module.getModuleId());


				}else {
					moduleService.removeModule(module);
				}


				doGet(request, response);
			}else{
				request.setAttribute("Erreur", "Module ID n'est pas un nombre, si le probleme persiste contacter le support.");
				doGet(request, response);

			}
		}else {

			doGet(request, response);
		}
	}

}
