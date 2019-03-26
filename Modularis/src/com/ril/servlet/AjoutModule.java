package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Angle;
import com.ril.model.Module;
import com.ril.model.Composant;
import com.ril.model.Gamme;
import com.ril.model.UniteMesure;
import com.ril.service.AngleService;
import com.ril.service.ComposantService;
import com.ril.service.GammeService;
import com.ril.service.ModuleService;
import com.ril.service.ModuleXComposantService;
import com.ril.service.UniteMesureService;

/**
 * Servlet implementation class AjoutModule
 */
@WebServlet("/AjoutModule")
public class AjoutModule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModuleService moduleSerivce = new ModuleService();
	private ModuleXComposantService moduleXComposantSerivce = new ModuleXComposantService();
	private GammeService gammeService = new GammeService();	
	private ComposantService composantService = new ComposantService();
	private AngleService angleService = new AngleService();
	private UniteMesureService uniteMesureService = new UniteMesureService();
	
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
		
		request.setAttribute("ListGamme", ListGamme);
		request.setAttribute("ListUniteMesure", ListUniteMesure);
		request.setAttribute("ListComposant", ListComposant);
		request.setAttribute("ListAngle", ListAngle);
		
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutModule.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String[] ListComposant = request.getParameterValues("ListComposant[]");
		String gammeId = request.getParameter("gamme");
		String nomModule = request.getParameter("nomModule");
		String typeAngle = request.getParameter("typeAngle");
		String uniteMesureId = request.getParameter("uniteMesure");
		String[] ListQuantite = request.getParameterValues("ListQuantite[]");
		
		
		
		if(gammeId != null && nomModule != null && uniteMesureId != null) {
			if(ListComposant.length == ListQuantite.length && ListComposant.length > 0) {
				if(typeAngle != null) {
					//Verifier que gamme type angle et unite mesure sont bien des Integer
					if(isInteger(gammeId) && isInteger(uniteMesureId) && isInteger(typeAngle)) {
						Angle angle = angleService.getAngleById(Integer.valueOf(typeAngle));
						UniteMesure uniteMesure = uniteMesureService.getUniteMesureById(Integer.valueOf(uniteMesureId));
						Gamme gamme = gammeService.getGammeById(Integer.valueOf(gammeId));
						Integer moduleId = moduleSerivce.addModule(angle, gamme, nomModule, uniteMesure);
						
						Module module = moduleSerivce.getModuleById(moduleId);
						
						for(int i=0 ; i < ListComposant.length ; i++) {
							//Verifier que se sois bien des integer
							String composantId = ListComposant[i];
							String quantites = ListQuantite[i];
							if(isInteger(composantId) && isInteger(quantites)) {
								
								Composant composant = composantService.getComposantById(Integer.valueOf(composantId));
								Integer quantite = Integer.valueOf(quantites);
								
								moduleXComposantSerivce.addModuleXComposant(quantite, module, composant);
								
							}else {
								//on remove le module et les liaison
								moduleSerivce.removeModule(module);
								request.setAttribute("Erreur", "Erreur lors de la mise en place des composants.");
								doGet(request, response);
								
							}
						}
					}
				}else {
					//Verifier que gamme et unite mesure sont bien des Integer
					if(isInteger(gammeId) && isInteger(uniteMesureId)) {
						UniteMesure uniteMesure = uniteMesureService.getUniteMesureById(Integer.valueOf(uniteMesureId));
						Gamme gamme = gammeService.getGammeById(Integer.valueOf(gammeId));
						Integer moduleId = moduleSerivce.addModule(gamme, nomModule, uniteMesure);
						
						//Après on ajoute les liaison
						Module module = moduleSerivce.getModuleById(moduleId);
						
						for(int i=0 ; i < ListComposant.length ; i++) {
							//Verifier que se sois bien des integer
							String composantId = ListComposant[i];
							String quantites = ListQuantite[i];
							if(isInteger(composantId) && isInteger(quantites)) {
								
								Composant composant = composantService.getComposantById(Integer.valueOf(composantId));
								Integer quantite = Integer.valueOf(quantites);
								
								moduleXComposantSerivce.addModuleXComposant(quantite, module, composant);
								
							}else {
								//on remove le module et les liaison
								moduleSerivce.removeModule(module);
								request.setAttribute("Erreur", "Erreur lors de la mise en place des composants.");
								doGet(request, response);
								
							}
						}
					}
					
					
				}
			}else {
				request.setAttribute("Erreur", "List quantite et composant ne sont pas identique.");
				doGet(request, response);
			}
		}else {
			request.setAttribute("Erreur", "Vous n'avez pas saisie toute les valeurs.");
			doGet(request, response);
		}
		response.setStatus(200);
		response.setContentType("application/json");
		response.getWriter().print("{ \"html\": \"/Modularis/Configuration/ListModule\"}");
		response.getWriter().flush();
	}

}
