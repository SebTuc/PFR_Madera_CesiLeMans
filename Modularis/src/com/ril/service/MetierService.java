package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.MetierHome;
import com.ril.model.Metier;

public class MetierService {
		
	public int addMetier(String libelle) {
		
		MetierHome dao = new MetierHome();
		
		if(libelle != null) {
			
			Metier metier = new Metier(libelle);
			
			dao.persist(metier);
			
			return metier.getMetierId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editMetier(Metier metier) {
		
		MetierHome dao = new MetierHome();
		
		if(metier != null) {
		
			dao.merge(metier);
		}
	}
	
	public void removeMetierById(Integer id) {
		
		MetierHome dao = new MetierHome();
		
		if(id != null) {

			Metier metier = getMetierById(id);
			
			dao.remove(metier);
		}
	}
	public void removeMetier(Metier metier) {

		MetierHome dao = new MetierHome();
		
		if(metier != null) {
			
			dao.remove(metier);
		}
	}
	
	public Metier getMetierById(Integer id) {
		
		MetierHome dao = new MetierHome();
		
		return dao.findById(id);
	}
	
	public List<Metier> getAllMetiers(){
		
		MetierHome dao = new MetierHome();
		
		return dao.findAll();
	}
}