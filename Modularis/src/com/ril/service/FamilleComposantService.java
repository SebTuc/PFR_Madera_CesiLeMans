package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.FamilleComposantHome;
import com.ril.model.FamilleComposant;

public class FamilleComposantService {

	private FamilleComposantHome dao;
	
	public FamilleComposantService() {
		this.dao = new FamilleComposantHome();
	}
	
	public int addFamilleComposant(String libelle) {
		FamilleComposant familleComposant = null;
		if(libelle != null) {
			familleComposant = new FamilleComposant(libelle);
			dao.persist(familleComposant);
			return familleComposant.getFamilleComposantId();			
		}else {
			return -1;
		}
	}
	
	public void editFamilleComposantById(Integer id) {
		if(id != null) {
			FamilleComposant familleComposant = getFamilleComposantById(id);
			dao.merge(familleComposant);
		}
	}
	
	public void editFamilleComposant(FamilleComposant metier) {
		if(metier != null) {
			dao.merge(metier);
		}
	}
	
	public void removeFamilleComposantById(Integer id) {
		if(id != null) {
			FamilleComposant uniteMesure = getFamilleComposantById(id);
			dao.remove(uniteMesure);
		}
	}
	public void removeFamilleComposant(FamilleComposant metier) {
		if(metier != null) {
			dao.remove(metier);
		}
	}
	
	public FamilleComposant getFamilleComposantById(Integer id) {
		return dao.findById(id);
	}
	
	public List<FamilleComposant> getAllFamilleComposant(){
		return dao.findAll();
	}
	
}
