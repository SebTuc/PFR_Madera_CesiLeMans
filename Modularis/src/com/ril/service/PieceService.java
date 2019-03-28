package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.PieceHome;
import com.ril.model.Piece;
import com.ril.model.Plan;
import com.ril.model.Module;

public class PieceService {
	
	public int addPiece(Piece piece) {
		
		PieceHome dao = new PieceHome();
		
		if(piece != null) {
			
			dao.persist(piece);
			
			return piece.getPieceId();
			
		} else {
			
			return -1;
		}
	}

	public int addPiece(Plan plan, String nom, Float surface) {
		
		PieceHome dao = new PieceHome();
		
		if(plan != null && nom != null && surface != null) {
			
			Piece piece = new Piece(plan, nom, surface);
			
			dao.persist(piece);
			
			return piece.getPieceId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editPiece(Piece piece) {
		
		PieceHome dao = new PieceHome();
		
		if(piece != null) {
		
			dao.merge(piece);
		}
	}
	
	public void removePieceById(Integer id) {
		
		PieceHome dao = new PieceHome();
		
		if(id != null) {

			Piece piece = getPieceById(id);
			
			dao.remove(piece);
		}
	}
	/*
	 * Return false si le module n'a pas etait supprimer ou n'exister pas
	 */
	public boolean removeModuleInPiece(Piece piece,Module module) {
		boolean flag=false;
		PieceHome dao = new PieceHome();
		
		if(piece != null && module != null) {
			
			if(moduleExistInPiece(piece,module)) {
				piece.getModules().remove(module);
				flag=true;
			}
			dao.merge(piece);
		}
		return flag;
	}
	
	public void removePiece(Piece piece) {

		PieceHome dao = new PieceHome();
		
		if(piece != null) {
			
			dao.remove(piece);
		}
	}
	
	public Piece getPieceById(Integer id) {
		
		PieceHome dao = new PieceHome();
		
		return dao.findById(id);
	}
	
	public boolean moduleExistInPiece(Piece piece , Module module) {
		
		for(Module mod : piece.getModules()) {
			if(mod.getModuleId() == module.getModuleId()) {
				return true;
			}
		}
		
		return false;
	}
	
	public List<Piece> getAllPieces(){
		
		PieceHome dao = new PieceHome();
		
		return dao.findAll();
	}
}
