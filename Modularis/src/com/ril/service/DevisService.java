package com.ril.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ril.daoHibernate.DevisHome;
import com.ril.model.Client;
import com.ril.model.Devis;
import com.ril.model.Etat;
import com.ril.model.Module;
import com.ril.model.Piece;
import com.ril.model.Plan;
import com.ril.model.Projet;
import com.ril.model.Utilisateur;


public class DevisService {
	private PieceService pieceService = new PieceService();
	private ModuleService moduleService = new ModuleService();

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
	
	private void removeListModuleHiddenToPiece(List<Module> list , Piece piece) {
		if(list.size()!=0) {
			//Get the instance hibernate with the java instance object
			Piece newPiece = pieceService.getPieceById(piece.getPieceId());
			for(Module module : list){
				//Get the instance hibernate with the java instance object
				Module mod = moduleService.getModuleById(module.getModuleId());
				Boolean hidden = mod.isDisplay();
				if(hidden != null) {
					pieceService.removeModuleToPiece(mod, newPiece);
					//reinstance piece
					newPiece = pieceService.getPieceById(piece.getPieceId());
				}
				
			}
		}
	}
	
	private void removeAllModuleHiddenInProjet(Projet projet) {
		
		for(Plan plan : projet.getPlans()) {
			for(Piece piece : plan.getPieces()) {
				List<Module> ListModule = new ArrayList<Module>(piece.getModules());
				removeListModuleHiddenToPiece(ListModule,piece);
			}
		}
	}
	
	public void removeDevisById(Integer id) {
		
		DevisHome dao = new DevisHome();
		
		if(id != null) {

			Devis devis = getDevisById(id);
			
			removeAllModuleHiddenInProjet(devis.getProjet());
			
			devis = getDevisById(devis.getDevisId());
			
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