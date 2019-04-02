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

import com.ril.model.Entrepot;
import com.ril.model.EntrepotBean;
import com.ril.model.Stock;
import com.ril.model.Utilisateur;
import com.ril.service.EntrepotService;
import com.ril.utils.MethodeUtile;

/**
 * Servlet implementation class ListEntrepot
 */
@WebServlet("/ListEntrepot")
public class ListEntrepot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private EntrepotService entrepotService = new EntrepotService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("SessionUtilisateur"));
		}
		List<Entrepot> ListEntre = entrepotService.getAllEntrepots();
		List<EntrepotBean> ListEntrepot = new ArrayList<EntrepotBean>();
		Boolean flag = false;
		for(Entrepot entrepot : ListEntre) {
			Integer notification=0;
			for(Stock stock : entrepot.getStocks()) {
				if(stock.getQuantite() <= 0) {
					notification++;
					flag = true;
				}
				
			}
			EntrepotBean entrepotBean = new EntrepotBean(entrepot,notification);
			ListEntrepot.add(entrepotBean);
		}
		
	
		request.setAttribute("MessageAlert", flag);
		
		
		request.setAttribute("ListEntrepot", ListEntrepot);
		request.getRequestDispatcher("/jsp/application/Stocks/ListEntrepot.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!MethodeUtile.isConnected(response , request)) {
			response.sendRedirect(request.getContextPath()+"/Connexion");
			return;
		}else {
			HttpSession session = request.getSession();
			request.setAttribute("Utilisateur", (Utilisateur)session.getAttribute("SessionUtilisateur"));
		}
		doGet(request, response);
	}

}
