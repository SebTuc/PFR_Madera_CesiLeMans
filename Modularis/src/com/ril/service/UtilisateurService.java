package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.UtilisateurHome;
import com.ril.model.DonneesPersonelle;
import com.ril.model.Entrepot;
import com.ril.model.Metier;
import com.ril.model.Utilisateur;

public class UtilisateurService {

	public int addUtilisateur(DonneesPersonelle donneesPersonelle, Metier metier, String login, String password,Entrepot entrepot) {
		
		UtilisateurHome dao = new UtilisateurHome();
		
		if(donneesPersonelle != null && metier != null && login !=null && password !=null) {
			
			Utilisateur utilisateur = new Utilisateur(donneesPersonelle, metier, login, password,entrepot);
			
			dao.persist(utilisateur);
			
			return utilisateur.getUtilisateurId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editUtilisateur(Utilisateur utilisateur) {
		
		UtilisateurHome dao = new UtilisateurHome();
		
		if(utilisateur != null) {
		
			dao.merge(utilisateur);
		}
	}
	
	public void removeUtilisateurById(Integer id) {
		
		UtilisateurHome dao = new UtilisateurHome();
		
		if(id != null) {

			Utilisateur utilisateur = getUtilisateurById(id);
			
			dao.remove(utilisateur);
		}
	}
	public void removeUtilisateur(Utilisateur utilisateur) {

		UtilisateurHome dao = new UtilisateurHome();
		
		if(utilisateur != null) {
			
			dao.remove(utilisateur);
		}
	}
	
	public Utilisateur getUtilisateurById(Integer id) {
		
		UtilisateurHome dao = new UtilisateurHome();
		
		return dao.findById(id);
	}
	
	public List<Utilisateur> getAllUtilisateurs(){
		
		UtilisateurHome dao = new UtilisateurHome();
		
		return dao.findAll();
	}
}