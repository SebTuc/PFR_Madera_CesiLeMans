package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.FournisseurHome;
import com.ril.model.Fournisseur;

public class FournisseurService {

	public int addFournisseur(String nom, String adresse, String codePostal, String telephone, String email) {
		
		FournisseurHome dao = new FournisseurHome();
		
		if(nom != null && adresse != null && codePostal != null && telephone != null && email != null) {
			
			Fournisseur fournisseur = new Fournisseur(nom,adresse,codePostal,telephone,email);
			
			dao.persist(fournisseur);
			
			return fournisseur.getFournisseurId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editFournisseur(Fournisseur fournisseur) {
		
		FournisseurHome dao = new FournisseurHome();
		
		if(fournisseur != null) {
		
			dao.merge(fournisseur);
		}
	}
	
	public void removeFournisseurById(Integer id) {
		
		FournisseurHome dao = new FournisseurHome();
		
		if(id != null) {

			Fournisseur fournisseur = getFournisseurById(id);
			
			dao.remove(fournisseur);
		}
	}
	public void removeFournisseur(Fournisseur fournisseur) {

		FournisseurHome dao = new FournisseurHome();
		
		if(fournisseur != null) {
			
			dao.remove(fournisseur);
		}
	}
	
	public Fournisseur getFournisseurById(Integer id) {
		
		FournisseurHome dao = new FournisseurHome();
		
		return dao.findById(id);
	}
	
	public List<Fournisseur> getAllFournisseurs(){
		
		FournisseurHome dao = new FournisseurHome();
		
		return dao.findAll();
	}
}