package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.GammeHome;
import com.ril.model.Gamme;

public class GammeService {

	public int addGamme(String nom) {
		
		GammeHome dao = new GammeHome();
		
		if(nom != null) {
			
			Gamme gamme = new Gamme(nom);
			
			dao.persist(gamme);
			
			return gamme.getGammeId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editGamme(Gamme gamme) {
		
		GammeHome dao = new GammeHome();
		
		if(gamme != null) {
		
			dao.merge(gamme);
		}
	}
	
	public void removeGammeById(Integer id) {
		
		GammeHome dao = new GammeHome();
		
		if(id != null) {

			Gamme gamme = getGammeById(id);
			
			dao.remove(gamme);
		}
	}
	public void removeGamme(Gamme gamme) {

		GammeHome dao = new GammeHome();
		
		if(gamme != null) {
			
			dao.remove(gamme);
		}
	}
	
	public Gamme getGammeById(Integer id) {
		
		GammeHome dao = new GammeHome();
		
		return dao.findById(id);
	}
	
	public List<Gamme> getAllGammes(){
		
		GammeHome dao = new GammeHome();
		
		return dao.findAll();
	}
}
