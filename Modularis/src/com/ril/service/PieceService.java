package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.PieceHome;
import com.ril.model.Module;
import com.ril.model.Piece;
import com.ril.model.Plan;

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
	
	public Piece getPieceByNomAndPlan(String nom,Plan plan) {
		
		PieceHome dao = new PieceHome();
		
		return dao.findByNomAndPlan(nom, plan);
	}
	
	/*
	 * Return false si la liaison module / piece exist OU si module ou piece est vide
	 */
	public boolean addModuleToPiece(Module module, Piece piece) {
		
		boolean flag = false;
		
		PieceHome dao = new PieceHome();

		if(module != null && piece !=null) 
		{
			if(!module.getPieces().contains(piece) && !piece.getModules().contains(module)) {				
				dao.persistJoin(module, piece);
				flag = true;
			}
		}
		return flag;
	}
	
	/*
	 * Return false si la liaison module / piece n'exist pas OU si module ou piece est vide
	 */
	public boolean removeModuleToPiece(Module module, Piece piece) {

		boolean flag=false;
		
		PieceHome dao = new PieceHome();

		if(module != null && piece !=null) 
		{
			if(module.getPieces().contains(piece) && piece.getModules().contains(module)) {			
				dao.removeJoin(module, piece);
				flag = true;
			}
		}
		return flag;
	}
	
	public List<Piece> getAllPieces(){
		
		PieceHome dao = new PieceHome();
		
		return dao.findAll();
	}
}
