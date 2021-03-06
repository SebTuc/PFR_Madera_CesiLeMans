package com.ril.service;

import java.util.ArrayList;
import java.util.List;

import com.ril.daoHibernate.ComposantHome;
import com.ril.model.Composant;
import com.ril.model.Entrepot;
import com.ril.model.FamilleComposant;
import com.ril.model.Fournisseur;
import com.ril.model.Materiaux;
import com.ril.model.ModuleXComposant;
import com.ril.model.Piece;
import com.ril.model.Stock;

public class ComposantService {

public int addComposant(FamilleComposant familleComposant, Fournisseur fournisseur, Materiaux materiaux, String nom, Float prixUnitaire) {
		
		ComposantHome dao = new ComposantHome();
		
		if(familleComposant != null && fournisseur != null && materiaux != null  && nom != null && prixUnitaire != null) {
			
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

	public boolean composantInDevisOrCatalogue(Composant composant) {
		boolean flag = false;
		
		for(ModuleXComposant modXComp : composant.getModuleXComposants()) {
			if(flag!= true) {
				for(Piece piece : modXComp.getModule().getPieces()) {
					if(piece.getPlan().getProjet().getDevises().size()!=0 || piece.getPlan().getProjet().getCatalogue().size()!=0) {
						
						flag = true;
						
					}
				}
			}
		}
		
		return flag;
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
	
	public List<Composant> getAllComposantNotInEntrepot(Entrepot entrepot){
		List<Composant> List = getAllComposants();
		List<Composant> ListComposant = new ArrayList<Composant>();
		
		for(Composant composant : List) {
			boolean flag = false;
			for(Stock stock : entrepot.getStocks()) {
				if(stock.getComposant().getComposantId() == composant.getComposantId()) {
					flag = true;
				}
			}
			if(flag == false) {
				ListComposant.add(composant);
			}
		}

		return ListComposant;
	}
}