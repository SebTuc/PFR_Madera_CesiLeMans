package com.ril.service;

import com.ril.daoHibernate.FactureHome;
import com.ril.model.Devis;
import com.ril.model.Facture;

public class FactureService {

	public int addFacture(Devis devis, String etape, Integer pourcentage) {
		
		FactureHome dao = new FactureHome();
		
		if(devis != null && etape != null && pourcentage != null) {
			
			Facture facture = new Facture();
			
			facture.setDevis(devis);
			facture.setEtape(etape);
			facture.setPourcentage(pourcentage);
			
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
}