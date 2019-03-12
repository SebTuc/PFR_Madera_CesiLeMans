package com.ril.service;

import java.util.Date;
import java.util.List;

import com.ril.daoHibernate.DevisHome;
import com.ril.model.Client;
import com.ril.model.Devis;
import com.ril.model.Etat;
import com.ril.model.Projet;
import com.ril.model.Utilisateur;

public class DevisService {

public int addDevis(Client client, Etat etat, Projet projet, Utilisateur utilisateur, Float prixHt, Date dateCreation) {
		
		DevisHome dao = new DevisHome();
		
		if(client != null && etat != null && projet != null && utilisateur != null && prixHt != null && dateCreation != null) {
			
			Devis devis = new Devis();
			
			devis.setClient(client);
			devis.setEtat(etat);
			devis.setProjet(projet);
			devis.setUtilisateur(utilisateur);
			devis.setPrixHt(prixHt);
			devis.setDateCreation(dateCreation);
			
			dao.persist(devis);
			
			return devis.getDevisId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editDevis(Devis devis) {
		
		DevisHome dao = new DevisHome();
		
		if(devis != null) {
		
			dao.merge(devis);
		}
	}
	
	public void removeDevisById(Integer id) {
		
		DevisHome dao = new DevisHome();
		
		if(id != null) {

			Devis devis = getDevisById(id);
			
			dao.remove(devis);
		}
	}
	public void removeDevis(Devis devis) {

		DevisHome dao = new DevisHome();
		
		if(devis != null) {
			
			dao.remove(devis);
		}
	}
	
	public Devis getDevisById(Integer id) {
		
		DevisHome dao = new DevisHome();
		
		return dao.findById(id);
	}
	
	public List<Devis> getAllDeviss(){
		
		DevisHome dao = new DevisHome();
		
		return dao.findAll();
	}
}