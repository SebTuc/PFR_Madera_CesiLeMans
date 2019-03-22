package com.ril.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ril.model.Composant;
import com.ril.model.Fournisseur;
import com.ril.model.Utilisateur;
import com.ril.service.UtilisateurService;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }
    private UtilisateurService user = new UtilisateurService();
    private String wok;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public String getwOK() {
        return wok;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/jsp/application/Compte/Connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Utilisateur> ListUser = user.getAllUtilisateurs();
		
		String login = request.getParameter("login");
		String pw	= request.getParameter("password");
		
		for(Utilisateur utilisateur : ListUser) {
			if(login != "") {
				if(login == utilisateur.getLogin()){
					if(pw != "") {
						if(pw == utilisateur.getPassword()){
							wok = "Connexion réussie.";
							response.sendRedirect("/Modularis");
						}else {
							wok = "Mauvais mot de passe pour cet utilisateur.";
						}
					}else{
						wok = "Veuillez saisir un mot de passe.";
					}
				}
			}else{
				wok = "Veuillez saisir un login.";
			}
		}
		System.out.println(wok);
		doGet(request, response);
	}
}
