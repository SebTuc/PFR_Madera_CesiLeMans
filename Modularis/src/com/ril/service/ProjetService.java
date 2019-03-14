package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.ProjetHome;
import com.ril.model.Catalogue;
import com.ril.model.Projet;

public class ProjetService {

public int addProjet(String nom) {
		
		ProjetHome dao = new ProjetHome();
		
		if(nom != null) {
			
			Projet projet = new Projet(nom);
			
			dao.persist(projet);
			
			return projet.getProjetId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editProjet(Projet projet) {
		
		ProjetHome dao = new ProjetHome();
		
		if(projet != null) {
		
			dao.merge(projet);
		}
	}
	
	public void removeProjetById(Integer id) {
		
		ProjetHome dao = new ProjetHome();
		
		if(id != null) {

			Projet projet = getProjetById(id);
			
			dao.remove(projet);
		}
	}
	public void removeProjet(Projet projet) {

		ProjetHome dao = new ProjetHome();
		
		if(projet != null) {
			
			dao.remove(projet);
		}
	}
	
	public Projet getProjetById(Integer id) {
		
		ProjetHome dao = new ProjetHome();
		
		return dao.findById(id);
	}
	
	public List<Projet> getAllProjets(){
		
		ProjetHome dao = new ProjetHome();
		
		return dao.findAll();
	}	
}
