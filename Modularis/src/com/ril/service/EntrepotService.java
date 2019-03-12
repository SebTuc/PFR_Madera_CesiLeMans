package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.EntrepotHome;
import com.ril.model.Entrepot;

public class EntrepotService {

	public int addEntrepot(String lieu) {
		
		EntrepotHome dao = new EntrepotHome();
		
		if(lieu != null) {
			
			Entrepot entrepot = new Entrepot(lieu);
			
			dao.persist(entrepot);
			
			return entrepot.getEntrepotId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editEntrepot(Entrepot entrepot) {
		
		EntrepotHome dao = new EntrepotHome();
		
		if(entrepot != null) {
		
			dao.merge(entrepot);
		}
	}
	
	public void removeEntrepotById(Integer id) {
		
		EntrepotHome dao = new EntrepotHome();
		
		if(id != null) {

			Entrepot entrepot = getEntrepotById(id);
			
			dao.remove(entrepot);
		}
	}
	public void removeEntrepot(Entrepot entrepot) {

		EntrepotHome dao = new EntrepotHome();
		
		if(entrepot != null) {
			
			dao.remove(entrepot);
		}
	}
	
	public Entrepot getEntrepotById(Integer id) {
		
		EntrepotHome dao = new EntrepotHome();
		
		return dao.findById(id);
	}
	
	public List<Entrepot> getAllEntrepots(){
		
		EntrepotHome dao = new EntrepotHome();
		
		return dao.findAll();
	}
}