package com.ril.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Gamme;
import com.ril.model.Module;
import com.ril.service.GammeService;
import com.ril.service.ModuleService;

/**
 * Servlet implementation class ListModule
 */
@WebServlet("/ListModule")
public class ListModule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModuleService moduleService = new ModuleService();
	private GammeService gammeService = new GammeService();	
	
	private boolean findContains(String value, String moduleValue) {
		
		String valueUpper = value.toUpperCase();
		String moduleValueUpper = moduleValue.toUpperCase();
		
		if(moduleValueUpper.contains(valueUpper)) {
			
			return true;
			
		}else {
		 
			return false;
			
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Module> ListModule = moduleService.getAllModules();
		List<Gamme> ListGamme = gammeService.getAllGammes();
		
		List<Module> list = new ArrayList<Module>();
		
		String gamme = request.getParameter("gamme");
		String nomModule = request.getParameter("nomModule");
		//Trie par critere
		if(gamme != null && gamme != "-1" || nomModule != null && nomModule != "") {
			if(ListModule != null) {
				for(Module module : ListModule) {
					if(gamme != "-1") {
						if(Integer.valueOf(gamme) == module.getGamme().getGammeId()){
							if(nomModule != "-1") {
								if(findContains(nomModule,module.getNom())){
			
									list.add(module);
									
								}
							}else {
								list.add(module);
							}	
						}
					}else if(nomModule != "-1") {
						if(findContains(nomModule,module.getNom())){
	
							list.add(module);
							
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
			if(ListModule == null) {
				
				request.setAttribute("isEmptyList", true);
			}else {
				request.setAttribute("isEmptyList", false);
			}
			request.setAttribute("ListModule", ListModule);
			
		}
		
		
		
		request.setAttribute("ListGamme", ListGamme);
		request.setAttribute("ListModule", ListModule);
		
		request.getRequestDispatcher("/jsp/application/Configuration/ListModule.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
