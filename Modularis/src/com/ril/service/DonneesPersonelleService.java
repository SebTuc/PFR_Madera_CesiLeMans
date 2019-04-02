package com.ril.service;

import com.ril.daoHibernate.DonneesPersonelleHome;
import com.ril.model.DonneesPersonelle;

public class DonneesPersonelleService {

	public int addDonneesPersonelle(String nom, String prenom, String email, String adresse, String telephone, String codePostal, String ville) {
		
		DonneesPersonelleHome dao = new DonneesPersonelleHome();
		
		if(nom != null && prenom != null && email != null && adresse != null && telephone != null && codePostal != null && ville !=null) {
			
			DonneesPersonelle donneesPersonelle = new DonneesPersonelle();
			
			donneesPersonelle.setNom(nom);
			donneesPersonelle.setPrenom(prenom);
			donneesPersonelle.setEmail(email);
			donneesPersonelle.setAdresse(adresse);
			donneesPersonelle.setTelephone(telephone);
			donneesPersonelle.setCodePostal(codePostal);
			donneesPersonelle.setVille(ville);
			
			dao.persist(donneesPersonelle);
			
			return donneesPersonelle.getDonneesPersonelleId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editDonneesPersonelle(DonneesPersonelle donneesPersonelle) {
		
		DonneesPersonelleHome dao = new DonneesPersonelleHome();
		
		if(donneesPersonelle != null) {
		
			dao.merge(donneesPersonelle);
		}
	}
	
	public void removeDonneesPersonelleById(Integer id) {
		
		DonneesPersonelleHome dao = new DonneesPersonelleHome();
		
		if(id != null) {

			DonneesPersonelle donneesPersonelle = getDonneesPersonelleById(id);
			
			dao.remove(donneesPersonelle);
		}
	}
	public void removeDonneesPersonelle(DonneesPersonelle donneesPersonelle) {

		DonneesPersonelleHome dao = new DonneesPersonelleHome();
		
		if(donneesPersonelle != null) {
			
			dao.remove(donneesPersonelle);
		}
	}
	
	public DonneesPersonelle getDonneesPersonelleById(Integer id) {
		
		DonneesPersonelleHome dao = new DonneesPersonelleHome();
		
		return dao.findById(id);
	}
}
