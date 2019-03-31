package com.ril.service;

import java.util.ArrayList;
import java.util.List;

import com.ril.daoHibernate.ProjetHome;
import com.ril.model.Image;
import com.ril.model.Module;
import com.ril.model.Piece;
import com.ril.model.Plan;
import com.ril.model.Projet;

public class ProjetService {
	
	private PieceService pieceService = new PieceService();
	private ModuleService moduleService = new ModuleService();
	
	public int addProjet(Projet projet) {
		
		ProjetHome dao = new ProjetHome();
		
		if(projet != null) {
			
			dao.persist(projet);
			
			return projet.getProjetId();
			
		} else {
			
			return -1;
		}
	}

	public int addProjet(String nom) {
		
		ProjetHome dao = new ProjetHome();
		
		if(nom != null) {
			
			Projet projet = new Projet(nom);
			
			dao.persist(projet);
			
			return projet.getProjetId();
			
		} else {
			
			return -1;
		}
	}
	
	public int addProjet(String nom,Image image) {
		
		ProjetHome dao = new ProjetHome();
		
		if(nom != null) {
			
			Projet projet = new Projet(nom,image);
			
			dao.persist(projet);
			
			return projet.getProjetId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editProjet(Projet projet) {
		
		ProjetHome dao = new ProjetHome();
		
		if(projet != null) {
		
			dao.merge(projet);
		}
	}
	
	private void removeListModuleToPiece(List<Module> list , Piece piece) {
		if(list.size()!=0) {
			//Get the instance hibernate with the java instance object
			Piece newPiece = pieceService.getPieceById(piece.getPieceId());
			for(Module module : list){
				//Get the instance hibernate with the java instance object
				Module mod = moduleService.getModuleById(module.getModuleId());
				pieceService.removeModuleToPiece(mod, newPiece);
				//reinstance piece
				newPiece = pieceService.getPieceById(piece.getPieceId());
			}
		}
	}
	
	private void removeAllModuleInProjet(Projet projet) {

		for(Plan plan : projet.getPlans()) {
			for(Piece piece : plan.getPieces()) {
				List<Module> ListModule = new ArrayList<Module>(piece.getModules());
				removeListModuleToPiece(ListModule,piece);
			}
		}
	}
	
	public void removeProjetById(Integer id) {
		
		ProjetHome dao = new ProjetHome();
		
		if(id != null) {
			
			Projet projet = getProjetById(id);
			//Supprimer tout les liaison module avant pour eviter le cascade remove dessus
			removeAllModuleInProjet(projet);
			
			projet = getProjetById(projet.getProjetId());
			
			dao.remove(projet);
		}
	}
	public void removeProjet(Projet projet) {

		ProjetHome dao = new ProjetHome();
		
		if(projet != null) {
			
			dao.remove(projet);
		}
	}
	
		
	public Projet getProjetById(Integer id) {
		
		ProjetHome dao = new ProjetHome();
		
		return dao.findById(id);
	}
	
	public List<Projet> getAllProjets(){
		
		ProjetHome dao = new ProjetHome();
		
		return dao.findAll();
	}	
}
