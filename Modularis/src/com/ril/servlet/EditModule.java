package com.ril.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Angle;
import com.ril.model.Composant;
import com.ril.model.Gamme;
import com.ril.model.Module;
import com.ril.model.ModuleXComposant;
import com.ril.model.ModuleXComposantId;
import com.ril.model.UniteMesure;
import com.ril.service.AngleService;
import com.ril.service.ComposantService;
import com.ril.service.GammeService;
import com.ril.service.ModuleService;
import com.ril.service.ModuleXComposantService;
import com.ril.service.UniteMesureService;

/**
 * Servlet implementation class EditModule
 */
@WebServlet("/EditModule")
public class EditModule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModuleService moduleSerivce = new ModuleService();
	private GammeService gammeService = new GammeService();	
	private ComposantService composantService = new ComposantService();
	private AngleService angleService = new AngleService();
	private UniteMesureService uniteMesureService = new UniteMesureService();
	private ModuleXComposantService moduleXComposantService = new ModuleXComposantService();
	
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Gamme> ListGamme = gammeService.getAllGammes();
		List<Composant> ListComposant = composantService.getAllComposants();
		List<Angle> ListAngle = angleService.getAllAngles();
		List<UniteMesure> ListUniteMesure = uniteMesureService.getAllUniteMesures();
		
		String moduleId = request.getParameter("id");
		
		if(moduleId != null && isInteger(moduleId)) {
			Module module = moduleSerivce.getModuleById(Integer.valueOf(moduleId));
			request.setAttribute("Module", module);
			
		}
		
		request.setAttribute("ListGamme", ListGamme);
		request.setAttribute("ListUniteMesure", ListUniteMesure);
		request.setAttribute("ListComposant", ListComposant);
		request.setAttribute("ListAngle", ListAngle);
		
		request.getRequestDispatcher("/jsp/application/Configuration/EditModule.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ListComposant = request.getParameterValues("ListComposant[]");
		String gammeId = request.getParameter("gamme");
		String nomModule = request.getParameter("nomModule");
		String moduleId = request.getParameter("moduleId");
		String typeAngle = request.getParameter("typeAngle");
		String uniteMesureId = request.getParameter("uniteMesure");
		String[] ListQuantite = request.getParameterValues("ListQuantite[]");
		String sendSubmit = request.getParameter("sendSubmit");
		String returnValue = "Ok"; 
		
		if(sendSubmit != null) {
			if(sendSubmit.equals("Ok")) {
				
				response.sendRedirect("/Modularis/Configuration/ListModule");
				
			}else {
				
				request.setAttribute("Erreur", sendSubmit);
				doGet(request, response);
				
			}
			
			
		}else {
			if(gammeId != null && nomModule != null && uniteMesureId != null && moduleId != null) {
				if(ListComposant.length == ListQuantite.length && ListComposant.length > 0) {
					if(typeAngle != null) {
						//Verifier que gamme type angle et unite mesure sont bien des Integer
						if(isInteger(gammeId) && isInteger(uniteMesureId) && isInteger(typeAngle) && isInteger(moduleId)) {
							Angle angle = angleService.getAngleById(Integer.valueOf(typeAngle));
							UniteMesure uniteMesure = uniteMesureService.getUniteMesureById(Integer.valueOf(uniteMesureId));
							Gamme gamme = gammeService.getGammeById(Integer.valueOf(gammeId));

							Module module = moduleSerivce.getModuleById(Integer.valueOf(moduleId));
							
							module.setAngle(angle);
							module.setGamme(gamme);
							module.setNom(nomModule);
							module.setUniteMesure(uniteMesure);
							
							
							
							Set<ModuleXComposant> list = new HashSet<ModuleXComposant>();
							Set<ModuleXComposant> ListForTest = module.getModuleXComposants();
							
							for(int i=0 ; i < ListComposant.length ; i++) {
								//Verifier que se sois bien des integer
								String composantId = ListComposant[i];
								String quantites = ListQuantite[i];
								if(isInteger(composantId) && isInteger(quantites)) {
									
									Composant composant = composantService.getComposantById(Integer.valueOf(composantId));
									Integer quantite = Integer.valueOf(quantites);
									ModuleXComposantId moduleXComposantId = new ModuleXComposantId(composant.getComposantId(),module.getModuleId());
									ModuleXComposant moduleXcompo = new ModuleXComposant(moduleXComposantId,composant,module,quantite);
									list.add(moduleXcompo);
									
									
								}else {
									returnValue = "Erreur lors de la mise en place des composants.";
								}
							}
							if(returnValue.equals("Ok")) {
								
								module.setModuleXComposants(list);
								moduleSerivce.editModule(module);
								//On supprime le surplus
								for(ModuleXComposant mod : ListForTest) {
									boolean test = false;
									for(ModuleXComposant modXComp : list) {
										if(modXComp.getId() == mod.getId()) {
											test = true;
										}
									}
									if(test != true) {
										moduleXComposantService.removeModuleXComposant(mod);
									}
								}
							}
						}
					}else {
						//Verifier que gamme et unite mesure sont bien des Integer
						if(isInteger(gammeId) && isInteger(uniteMesureId)) {
							UniteMesure uniteMesure = uniteMesureService.getUniteMesureById(Integer.valueOf(uniteMesureId));
							Gamme gamme = gammeService.getGammeById(Integer.valueOf(gammeId));
							Module module = moduleSerivce.getModuleById(Integer.valueOf(moduleId));
							
							module.setGamme(gamme);
							module.setNom(nomModule);
							module.setUniteMesure(uniteMesure);
							module.setAngle(null);
							
							Set<ModuleXComposant> list = new HashSet<ModuleXComposant>();
							Set<ModuleXComposant> ListForTest = module.getModuleXComposants();
							
							for(int i=0 ; i < ListComposant.length ; i++) {
								//Verifier que se sois bien des integer
								String composantId = ListComposant[i];
								String quantites = ListQuantite[i];
								if(isInteger(composantId) && isInteger(quantites)) {
									
									Composant composant = composantService.getComposantById(Integer.valueOf(composantId));
									Integer quantite = Integer.valueOf(quantites);
									ModuleXComposantId moduleXComposantId = new ModuleXComposantId(composant.getComposantId(),module.getModuleId());
									ModuleXComposant moduleXcompo = new ModuleXComposant(moduleXComposantId,composant,module,quantite);
									
									list.add(moduleXcompo);
									
									
									
									
								}else {
									returnValue = "Erreur lors de la mise en place des composants.";
								}
							}
							if(returnValue.equals("Ok")) {
							
								module.setModuleXComposants(list);
								moduleSerivce.editModule(module);
								//On supprime le surplus
								for(ModuleXComposant mod : ListForTest) {
									boolean test = false;
									for(ModuleXComposant modXComp : list) {
										if(modXComp.getId() == mod.getId()) {
											test = true;
										}
									}
									if(test != true) {
										moduleXComposantService.removeModuleXComposant(mod);
									}
								}
							}
						}
						
						
					}
				}else {
					returnValue = "List quantite et composant ne sont pas identique.";
					
				}
			}else {
				returnValue = "Vous n'avez pas saisie toute les valeurs.";
				
			}
			response.setStatus(200);
			response.setContentType("application/json");
			response.getWriter().print("{ \"retour\": \""+returnValue+"\"}");
			response.getWriter().flush();
		}
		
	}

}