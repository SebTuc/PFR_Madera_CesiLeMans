package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.EtatHome;
import com.ril.model.Etat;

public class EtatService {

	public int addEtat(String nom) {
		
		EtatHome dao = new EtatHome();
		
		if(nom != null) {
			
			Etat etat = new Etat(nom);
			
			dao.persist(etat);
			
			return etat.getEtatId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editEtat(Etat etat) {
		
		EtatHome dao = new EtatHome();
		
		if(etat != null) {
		
			dao.merge(etat);
		}
	}
	
	public void removeEtatById(Integer id) {
		
		EtatHome dao = new EtatHome();
		
		if(id != null) {

			Etat etat = getEtatById(id);
			
			dao.remove(etat);
		}
	}
	public void removeEtat(Etat etat) {

		EtatHome dao = new EtatHome();
		
		if(etat != null) {
			
			dao.remove(etat);
		}
	}
	
	public Etat getEtatById(Integer id) {
		
		EtatHome dao = new EtatHome();
		
		return dao.findById(id);
	}
	
	public List<Etat> getAllEtats(){
		
		EtatHome dao = new EtatHome();
		
		return dao.findAll();
	}
}