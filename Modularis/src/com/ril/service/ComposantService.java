package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.ComposantHome;
import com.ril.model.Composant;
import com.ril.model.FamilleComposant;
import com.ril.model.Fournisseur;
import com.ril.model.Materiaux;
import com.ril.model.UniteMesure;

public class ComposantService {

public int addComposant(FamilleComposant familleComposant, Fournisseur fournisseur, Materiaux materiaux, UniteMesure uniteMesure, String nom, Float prixUnitaire) {
		
		ComposantHome dao = new ComposantHome();
		
		if(familleComposant != null && fournisseur != null && materiaux != null && uniteMesure != null && nom != null && prixUnitaire != null) {
			
			Composant composant = new Composant();
			
			composant.setFamilleComposant(familleComposant);
			composant.setFournisseur(fournisseur);
			composant.setMateriaux(materiaux);
			composant.setNom(nom);
			composant.setPrixUnitaire(prixUnitaire);			
			
			dao.persist(composant);
			
			return composant.getComposantId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editComposant(Composant composant) {
		
		ComposantHome dao = new ComposantHome();
		
		if(composant != null) {
		
			dao.merge(composant);
		}
	}
	
	public void removeComposantById(Integer id) {
		
		ComposantHome dao = new ComposantHome();
		
		if(id != null) {

			Composant composant = getComposantById(id);
			
			dao.remove(composant);
		}
	}
	public void removeComposant(Composant composant) {

		ComposantHome dao = new ComposantHome();
		
		if(composant != null) {
			
			dao.remove(composant);
		}
	}
	
	public Composant getComposantById(Integer id) {
		
		ComposantHome dao = new ComposantHome();
		
		return dao.findById(id);
	}
	
	public List<Composant> getAllComposants(){
		
		ComposantHome dao = new ComposantHome();
		
		return dao.findAll();
	}
}