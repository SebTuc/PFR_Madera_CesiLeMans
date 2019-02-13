package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.MetierHome;
import com.ril.daoHibernate.UniteMesureHome;
import com.ril.model.UniteMesure;

public class UniteMesureService {
	private UniteMesureHome dao;
	
	public UniteMesureService() {
		this.dao = new UniteMesureHome();
	}
	
	public int addUniteMesure(String libelle) {
		UniteMesure uniteMesure = null;
		if(libelle != null) {
			uniteMesure = new UniteMesure(libelle);
			dao.persist(uniteMesure);
			return uniteMesure.getUniteId();			
		}else {
			return -1;
		}
	}
	
	public void editUniteMesureById(Integer id) {
		if(id != null) {
			UniteMesure metier = getUniteMesureById(id);
			dao.merge(metier);
		}
	}
	
	public void editUniteMesure(UniteMesure uniteMesure) {
		if(uniteMesure != null) {
			dao.merge(uniteMesure);
		}
	}
	
	public void removeUniteMesureById(Integer id) {
		if(id != null) {
			UniteMesure uniteMesure = getUniteMesureById(id);
			dao.remove(uniteMesure);
		}
	}
	public void removeUniteMesure(UniteMesure uniteMesure) {
		if(uniteMesure != null) {
			dao.remove(uniteMesure);
		}
	}
	
	public UniteMesure getUniteMesureById(Integer id) {
		return dao.findById(id);
	}
	
	public List<UniteMesure> getAllUniteMesures(){
		return dao.findAll();
	}
}
