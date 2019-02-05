package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.MetierHome;
import com.ril.model.Metier;

public class MetierService {
	
	private MetierHome dao;
	
	public MetierService() {
		this.dao = new MetierHome();
	}
	
	public int addMetier(String libelle) {
		Metier metier = null;
		if(libelle != null) {
			metier = new Metier(libelle);
			dao.persist(metier);
			return metier.getMetierId();			
		}else {
			return -1;
		}
	}
	
	public void editMetierById(Integer id) {
		if(id != null) {
			Metier metier = getMetierById(id);
			dao.merge(metier);
		}
	}
	
	public void editMetier(Metier metier) {
		if(metier != null) {
			dao.merge(metier);
		}
	}
	
	public void removeMetierById(Integer id) {
		if(id != null) {
			Metier uniteMesure = getMetierById(id);
			dao.remove(uniteMesure);
		}
	}
	public void removeMetier(Metier metier) {
		if(metier != null) {
			dao.remove(metier);
		}
	}
	
	public Metier getMetierById(Integer id) {
		return dao.findById(id);
	}
	
	public List<Metier> getAllMetiers(){
		return dao.findAll();
	}
	
}
