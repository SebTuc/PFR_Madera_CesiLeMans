package com.ril.service;

import java.util.Date;
import java.util.List;

import com.ril.daoHibernate.FactureHome;
import com.ril.model.Devis;
import com.ril.model.EtapeFacture;
import com.ril.model.Facture;

public class FactureService {

	public int addFacture(Devis devis, EtapeFacture etapeFacture ) {
		
		FactureHome dao = new FactureHome();
		
		if(devis != null && etapeFacture != null) {
			
			Facture facture = new Facture();
			
			facture.setDevis(devis);
			facture.setEtapeFacture(etapeFacture);
			
			dao.persist(facture);
			
			return facture.getFactureId();
			
		} else {
			
			return -1;
		}
	}
	
	public int addFacture(Devis devis, EtapeFacture etapeFacture,Date dateModification ) {
		
		FactureHome dao = new FactureHome();
		
		if(devis != null && etapeFacture != null) {
			
			Facture facture = new Facture();
			
			facture.setDevis(devis);
			facture.setEtapeFacture(etapeFacture);
			facture.setDateModification(dateModification);
			
			dao.persist(facture);
			
			return facture.getFactureId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editFacture(Facture facture) {
		
		FactureHome dao = new FactureHome();
		
		if(facture != null) {
		
			dao.merge(facture);
		}
	}
	
	public void removeFactureById(Integer id) {
		
		FactureHome dao = new FactureHome();
		
		if(id != null) {

			Facture facture = getFactureById(id);
			
			dao.remove(facture);
		}
	}
	public void removeFacture(Facture facture) {

		FactureHome dao = new FactureHome();
		
		if(facture != null) {
			
			dao.remove(facture);
		}
	}
	
	public Facture getFactureById(Integer id) {
		
		FactureHome dao = new FactureHome();
		
		return dao.findById(id);
	}
	

	public List<Facture> getAllFacture(){
		
		FactureHome dao = new FactureHome();
		
		return dao.findAll();
	}
	
}