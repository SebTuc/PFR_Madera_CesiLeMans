package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.EtapeFacture;
import com.ril.service.EtapeFactureService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutEtapeFacture
 */
@WebServlet("/AjoutEtapeFacture")
public class AjoutEtapeFacture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EtapeFactureService etapeFactureService = new EtapeFactureService();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<EtapeFacture> ListEtapeFacture = etapeFactureService.getAllEtapeFacture();
		request.setAttribute("ListEtapeFacture", ListEtapeFacture);
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutEtapeFacture.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String insertNEtape = request.getParameter("insertNEtape");
		String insertEtape = request.getParameter("insertEtape");
		String insertPourcentage = request.getParameter("insertPourcentage");
		
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		
		String nEtape = request.getParameter("nEtape");
		String etape = request.getParameter("etape");
		String pourcentage = request.getParameter("pourcentage");
				
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				if(MethodeUtile.isInteger(idValeur)) {
					etapeFactureService.removeEtapeFactureById(Integer.valueOf(idValeur));
				}else {
					request.setAttribute("Erreur", "Erreur lors de la suppresion");
				}
				
				
			}else if(action.equals("Edition")) {
				
				if(MethodeUtile.isInteger(idValeur) && MethodeUtile.isInteger(nEtape) && MethodeUtile.isInteger(pourcentage)) {
					EtapeFacture etapeFacture = etapeFactureService.getEtapeFactureById(Integer.valueOf(idValeur));
					
					etapeFacture.setnEtape(Integer.valueOf(nEtape));
					etapeFacture.setEtape(etape);
					etapeFacture.setPourcentage(Integer.valueOf(pourcentage));
					
					
					if(etapeFactureService.editEtapeFacture(etapeFacture)) {
						response.setStatus(200);
						response.setContentType("application/json");
						response.getWriter().print("{ \"id\": \""+etapeFacture.getEtapeFactureId()+"\", \"nEtape\": \""+etapeFacture.getnEtape()+"\" , \"etape\": \""+etapeFacture.getEtape()+"\", \"pourcentage\": \""+etapeFacture.getPourcentage()+"\" }");
						response.getWriter().flush();
					}else {
						
						response.setStatus(400);
						response.setContentType("application/json");
						response.getWriter().print("{ \"id\": \""+etapeFacture.getEtapeFactureId()+"\", \"nEtape\": \""+nEtape+"\" , \"etape\": \""+etape+"\", \"pourcentage\": \""+pourcentage+"\" }");
						response.getWriter().flush();
					}
					
					
				}else {
					response.setStatus(400);	
				}
				
				
			}
		}else if (insertNEtape != null) {						
			if (!insertNEtape.equals("") && !insertEtape.equals("") && !insertPourcentage.equals("")) {				
				if(MethodeUtile.isInteger(insertNEtape) && MethodeUtile.isInteger(insertPourcentage)) {
					
					//-1 si pas de creation car etape ou pourcentage incorect
					Integer idFacture = etapeFactureService.addEtapeFacture(Integer.valueOf(insertNEtape), insertEtape, Integer.valueOf(insertPourcentage));
					
					if(idFacture != -1) {
						
						response.setStatus(303);	
						response.sendRedirect(request.getContextPath()+"/Configuration/AjoutEtapeFacture");
					}else {
						request.setAttribute("Erreur", "Les informations saisie son incoherente");
						doGet(request,response);
					}
				
					
				}else {
					
					request.setAttribute("Erreur", "Veuillez saisir des chiffres pour pourcentage et nEtape");
					doGet(request,response);
					
				}
				
				
				
			}
		}else {
			//Post de null part
		}
						
	
	}

}
