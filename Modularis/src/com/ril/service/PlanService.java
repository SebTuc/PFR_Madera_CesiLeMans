package com.ril.service;

import java.util.ArrayList;
import java.util.List;

import com.ril.daoHibernate.PlanHome;
import com.ril.model.Module;
import com.ril.model.Piece;
import com.ril.model.Plan;
import com.ril.model.Projet;

public class PlanService {
	
	private PieceService pieceService = new PieceService();
	private ModuleService moduleService = new ModuleService();
	
	public int addPlan(Plan plan) {
		
		PlanHome dao = new PlanHome();
		
		if(plan != null) {
			
			dao.persist(plan);
			
			return plan.getPlanId();
			
		} else {
			
			return -1;
		}
	}

	public int addPlan(Projet projet, String nom) {
		
		PlanHome dao = new PlanHome();
		
		if(projet != null && nom != null) {
			
			Plan plan = new Plan(projet, nom);
			
			dao.persist(plan);
			
			return plan.getPlanId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editPlan(Plan plan) {
		
		PlanHome dao = new PlanHome();
		
		if(plan != null) {
		
			dao.merge(plan);
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
	
	
	public void removePlanById(Integer id) {
		
		PlanHome dao = new PlanHome();
		
		if(id != null) {

			Plan plan = getPlanById(id);
			
			for(Piece piece : plan.getPieces()) {
				if(piece.getModules().size()!=0) {
					List<Module> list = new ArrayList<Module>(piece.getModules());
					removeListModuleToPiece(list,piece);
				}
			}
			
			plan = getPlanById(plan.getPlanId());
			
			dao.remove(plan);
		}
	}
	public void removePlan(Plan plan) {

		PlanHome dao = new PlanHome();
		
		if(plan != null) {
			
			for(Piece piece : plan.getPieces()) {
				if(piece.getModules().size()!=0) {
					List<Module> list = new ArrayList<Module>(piece.getModules());
					removeListModuleToPiece(list,piece);
					plan = getPlanById(plan.getPlanId());
				}
			}
			
			dao.remove(plan);
		}
	}
	
	public Plan getPlanById(Integer id) {
		
		PlanHome dao = new PlanHome();
		
		return dao.findById(id);
	}
}