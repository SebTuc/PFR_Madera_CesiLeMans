package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Angle;
import com.ril.model.Utilisateur;
import com.ril.service.AngleService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class AjoutAngle
 */
@WebServlet("/AjoutAngle")
public class AjoutAngle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AngleService angleService = new AngleService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
		
		List<Angle> ListAngle = angleService.getAllAngles();
		request.setAttribute("ListAngle", ListAngle);
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutAngle.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String angleNom = request.getParameter("angleNom");	
		String anglePrix = request.getParameter("anglePrix");
		String action = request.getParameter("action");
		String idValeur = request.getParameter("id");
		String typeAngle = request.getParameter("typeAngle");
		String prixUnitaire = request.getParameter("prixUnitaire");
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("Utilisateur"));
		}
				
		// Ajout ou Delete
		if(action != null) {
			if(action.equals("Delete")) {
				
				angleService.removeAngleById(Integer.valueOf(idValeur));
				
			}else if(action.equals("Edition")) {
				
				//Faire les verifs :)
				
				Angle angle = angleService.getAngleById(Integer.valueOf(idValeur));
				angle.setTypeAngle(typeAngle);
				angle.setPrixUnitaire(Float.valueOf(prixUnitaire));
				angleService.editAngle(angle);
				
				// Retour de l'objet modifier sous format json
				response.setStatus(200);
				response.setContentType("application/json");
				response.getWriter().print("{ \"id\": \""+angle.getAngleId()+"\", \"typeAngle\": \""+angle.getTypeAngle()+"\" , \"prixUnitaire\": \""+angle.getPrixUnitaire()+"\" }");
				response.getWriter().flush();
				
			}
		}else if (angleNom != null && anglePrix !=null) {						
			if (angleNom.trim() != null && anglePrix.trim() != null) {	
				if(MethodeUtile.isFloat(anglePrix)) {
					
					angleService.addAngle(angleNom,Float.valueOf(anglePrix));

					//Definit la reponse comme "See Other" et redirige
					//Evite la multi-insertion apr�s un refresh de l'utilsateur		
					response.setStatus(303);	
					response.sendRedirect(request.getContextPath()+"/Configuration/AjoutAngle");
				}else {
				
					request.setAttribute("Erreur" , "Veuillez saisir un chiffre.");
					doGet(request ,response);
					
				}
			}else {
				request.setAttribute("Erreur" , "Veuillez saisir toutes les informations.");
				doGet(request ,response);
			}
		}else {
			
		}
	}

}
