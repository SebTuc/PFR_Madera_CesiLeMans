package com.ril.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ril.daoHibernate.DevisHome;
import com.ril.daoHibernate.PieceHome;
import com.ril.daoHibernate.PlanHome;
import com.ril.daoHibernate.ProjetHome;
import com.ril.model.Devis;
import com.ril.model.Image;
import com.ril.model.Piece;
import com.ril.model.Plan;
import com.ril.model.Projet;

public class ProjetService {
	
	public int addProjet(Projet projet) {
		
		ProjetHome dao = new ProjetHome();
		
		if(projet != null) {
			
			dao.persist(projet);
			
			return projet.getProjetId();
			
		} else {
			
			return -1;
		}
	}

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
	
	public int addProjet(String nom,Image image) {
		
		ProjetHome dao = new ProjetHome();
		
		if(nom != null) {
			
			Projet projet = new Projet(nom,image);
			
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
