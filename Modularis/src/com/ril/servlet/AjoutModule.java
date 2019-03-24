package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Angle;
import com.ril.model.Composant;
import com.ril.model.Gamme;
import com.ril.service.AngleService;
import com.ril.service.ComposantService;
import com.ril.service.GammeService;

/**
 * Servlet implementation class AjoutModule
 */
@WebServlet("/AjoutModule")
public class AjoutModule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GammeService gammeService = new GammeService();	
	private ComposantService composantService = new ComposantService();
	private AngleService angleService = new AngleService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		List<Gamme> ListGamme = gammeService.getAllGammes();
		List<Composant> ListComposant = composantService.getAllComposants();
		List<Angle> ListAngle = angleService.getAllAngles();
		
		request.setAttribute("ListGamme", ListGamme);
		request.setAttribute("ListComposant", ListComposant);
		request.setAttribute("ListAngle", ListAngle);
		
		request.getRequestDispatcher("/jsp/application/Configuration/AjoutModule.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
