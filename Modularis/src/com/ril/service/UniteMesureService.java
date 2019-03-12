package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.UniteMesureHome;
import com.ril.model.UniteMesure;

public class UniteMesureService {
		
	public int addUniteMesure(String libelle) {

		UniteMesureHome dao = new UniteMesureHome();
		
		if(libelle != null) {
			
			UniteMesure uniteMesure = new UniteMesure(libelle);
			
			dao.persist(uniteMesure);
			
			return uniteMesure.getUniteId();			
			
		} else {
			
			return -1;
		}
	}
	
	public void editUniteMesure(UniteMesure uniteMesure) {
		
		UniteMesureHome dao = new UniteMesureHome();
		
		if(uniteMesure != null) {
		
			dao.merge(uniteMesure);
		}
	}
	
	public void removeUniteMesureById(Integer id) {

		UniteMesureHome dao = new UniteMesureHome();
		
		if(id != null) {
			
			UniteMesure uniteMesure = getUniteMesureById(id);
			
			dao.remove(uniteMesure);
		}
	}
	public void removeUniteMesure(UniteMesure uniteMesure) {
		
		UniteMesureHome dao = new UniteMesureHome();
		
		if(uniteMesure != null) {
		
			dao.remove(uniteMesure);
		}
	}
	
	public UniteMesure getUniteMesureById(Integer id) {

		UniteMesureHome dao = new UniteMesureHome();
		
		return dao.findById(id);
	}
	
	public List<UniteMesure> getAllUniteMesures(){

		UniteMesureHome dao = new UniteMesureHome();
		
		return dao.findAll();
	}
}
