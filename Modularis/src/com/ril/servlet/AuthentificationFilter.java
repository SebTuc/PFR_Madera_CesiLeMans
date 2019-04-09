package com.ril.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ril.model.Utilisateur;
import com.ril.service.UtilisateurService;
import com.ril.utils.MethodeUtile;



@WebFilter("/AuthentificationFilter")
public class AuthentificationFilter implements Filter {
	
	public ServletContext context;
	private UtilisateurService utilisateurService = new UtilisateurService();
	
	public void init(FilterConfig fConfig) throws ServletException {
		
		this.context = fConfig.getServletContext();
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String loginURI = req.getContextPath() + "/Connexion";
		
		String uri = req.getRequestURI();
//		this.context.log("Requested Resource::"+uri);
		
		HttpSession session = req.getSession(false);
		
		
		boolean loggedIn = session != null && session.getAttribute("SessionUtilisateur") != null;
		boolean loginRequest = req.getRequestURI().equals(loginURI) || req.getRequestURI().contains("resources");
		Utilisateur utilisateur=null;
		//Si pas de session actif on cherche dans les cookies
		if(!loggedIn) {
			String UtilisateurCookie = getCookieValue(req,"UtlisateurLoginPasswordId");
			
			if(UtilisateurCookie != null) {
				String[] SplitDataCookie = UtilisateurCookie.split("\\.");
				String login = SplitDataCookie[0];
				String password = SplitDataCookie[1];
				String id = SplitDataCookie[2];
				if(MethodeUtile.isInteger(id)) {
					
					utilisateur = utilisateurService.getUtilisateurById(Integer.valueOf(id));
					//Verification des information par rapport a la BDD
					if(utilisateur.getPassword().equals(password) && utilisateur.getLogin().equals(login)) {
						
						loggedIn = true;
					}
				}
				
			}
		}
		
		if(!loginRequest){
			if(!loggedIn) {
				res.sendRedirect(loginURI);
			}else {
				if(loggedIn) {
					//On set dans la request Utilisateur
					if(session != null ) {
						if(session.getAttribute("SessionUtilisateur")!=null) {
							
							//connected grace a une session actif
							utilisateur = (Utilisateur)session.getAttribute("SessionUtilisateur");
							utilisateur = utilisateurService.getUtilisateurById(utilisateur.getUtilisateurId());
						}else {
							//connected grace au cookie
							session.setAttribute("SessionUtilisateur", utilisateur);
						}
					}else {
						//Connected grace au cookie on créer une session car aucune session de créer
						session = req.getSession();
						session.setAttribute("SessionUtilisateur", utilisateur);
					}
					request.setAttribute("Utilisateur", utilisateur);
				}
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);							
		}
		
		
	}

	public void destroy() {
		//close any resources here
	}
	
	private static String getCookieValue( HttpServletRequest request, String nom ) {
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ) {
            for ( Cookie cookie : cookies ) {
                if ( cookie != null && nom.equals( cookie.getName() ) ) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


}
