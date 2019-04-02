package com.ril.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Utilisateur;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class Configuration
 */
@WebServlet("/Configuration")
public class Configuration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("SessionUtilisateur"));
		}
		request.getRequestDispatcher("/jsp/application/index/Configuration.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}
		doGet(request, response);
	}

}
