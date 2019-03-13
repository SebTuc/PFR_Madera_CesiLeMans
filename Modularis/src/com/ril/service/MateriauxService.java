package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.MateriauxHome;
import com.ril.model.Materiaux;

public class MateriauxService {

	public int addMateriaux(String nom) {
		
		MateriauxHome dao = new MateriauxHome();
		
		if(nom != null) {
			
			Materiaux materiaux = new Materiaux(nom);
			
			dao.persist(materiaux);
			
			return materiaux.getMateriauxId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editMateriaux(Materiaux materiaux) {
		
		MateriauxHome dao = new MateriauxHome();
		
		if(materiaux != null) {
		
			dao.merge(materiaux);
		}
	}
	
	public void removeMateriauxById(Integer id) {
		
		MateriauxHome dao = new MateriauxHome();
		
		if(id != null) {

			Materiaux materiaux = getMateriauxById(id);
			
			dao.remove(materiaux);
		}
	}
	public void removeMateriaux(Materiaux materiaux) {

		MateriauxHome dao = new MateriauxHome();
		
		if(materiaux != null) {
			
			dao.remove(materiaux);
		}
	}
	
	public Materiaux getMateriauxById(Integer id) {
		
		MateriauxHome dao = new MateriauxHome();
		
		return dao.findById(id);
	}
	
	public List<Materiaux> getAllMateriauxs(){
		
		MateriauxHome dao = new MateriauxHome();
		
		return dao.findAll();
	}
}
