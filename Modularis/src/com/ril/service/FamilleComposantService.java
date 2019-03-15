package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.FamilleComposantHome;
import com.ril.model.FamilleComposant;

public class FamilleComposantService {

	public int addFamilleComposant(String libelle) {
		
		FamilleComposantHome dao = new FamilleComposantHome();
		
		if(libelle != null) {
		
			FamilleComposant familleComposant = new FamilleComposant(libelle);
			
			dao.persist(familleComposant);
			
			return familleComposant.getFamilleComposantId();			
		
		} else {
			
			return -1;
		}
	}
	
	public void editFamilleComposant(FamilleComposant metier) {
		
		FamilleComposantHome dao = new FamilleComposantHome();
		
		if(metier != null) {
		
			dao.merge(metier);
		}
	}
	
	public void removeFamilleComposantById(Integer id) {
		
		FamilleComposantHome dao = new FamilleComposantHome();
		
		if(id != null) {
		
			FamilleComposant familleComposant = getFamilleComposantById(id);
			
			dao.remove(familleComposant);
		}
	}
	public void removeFamilleComposant(FamilleComposant metier) {
		
		FamilleComposantHome dao = new FamilleComposantHome();
		
		if(metier != null) {
		
			dao.remove(metier);
		}
	}
	
	public FamilleComposant getFamilleComposantById(Integer id) {

		FamilleComposantHome dao = new FamilleComposantHome();
		
		return dao.findById(id);
	}
	
	public List<FamilleComposant> getAllFamilleComposant(){

		FamilleComposantHome dao = new FamilleComposantHome();
		
		return dao.findAll();
	}	
}