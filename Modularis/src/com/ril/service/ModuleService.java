package com.ril.service;

import java.util.ArrayList;
import java.util.List;

import com.ril.daoHibernate.ModuleHome;
import com.ril.model.Angle;
import com.ril.model.Gamme;
import com.ril.model.Module;
import com.ril.model.Piece;
import com.ril.model.UniteMesure;

public class ModuleService {

	private PieceService pieceService = new PieceService();
	
	public int addModule(Gamme gamme, String nom , UniteMesure uniteMesure) {
		
		ModuleHome dao = new ModuleHome();
		
		if( gamme != null && nom != null) {
			
			Module module = new Module(gamme, nom , uniteMesure);
			
			dao.persist(module);
			
			return module.getModuleId();
			
		} else {
			
			return -1;
		}
	}
	
	public int addModule(Angle angle ,Gamme gamme, String nom , UniteMesure uniteMesure) {
		
		ModuleHome dao = new ModuleHome();
		
		if(angle!= null && gamme != null && nom != null) {
			
			Module module = new Module(angle,gamme, nom , uniteMesure);
			
			dao.persist(module);
			
			return module.getModuleId();
			
		} else {
			
			return -1;
		}
	}
	
	public boolean moduleInDevisOrCatalogue(Module module) {
		boolean flag = false;
		
		for(Piece piece : module.getPieces()) {
			if(piece.getPlan().getProjet().getCatalogue().size() != 0 || piece.getPlan().getProjet().getDevises().size()!=0) {
				flag=true;
			}
		}
		
		return flag;
	}
	
	public void editModule(Module module) {
		
		ModuleHome dao = new ModuleHome();
		
		if(module != null) {
		
			dao.merge(module);
		}
	}
	
	private void removeListPieceToModule(List<Piece> list , Module module) {
		if(list.size()!=0) {
			//Get the instance hibernate with the java instance object
			Module newModule = getModuleById(module.getModuleId());
			for(Piece piece : list){
				//Get the instance hibernate with the java instance object
				Piece pie = pieceService.getPieceById(piece.getPieceId());
				pieceService.removeModuleToPiece(newModule, pie);
				//reinstance piece
				newModule = getModuleById(module.getModuleId());
			}
		}
	}
	
	public void removeModuleById(Integer id) {
		
		ModuleHome dao = new ModuleHome();
		
		if(id != null) {

			Module module = getModuleById(id);
			//Delete all module x piece
			List<Piece> ListPiece = new ArrayList<Piece>(module.getPieces());
			removeListPieceToModule(ListPiece,module);
			
			module = getModuleById(id);
			
			dao.remove(module);
		}
	}
	public void removeModule(Module module) {

		ModuleHome dao = new ModuleHome();
		
		if(module != null) {
			
			dao.remove(module);
		}
	}
	
	public Module getModuleById(Integer id) {
		
		ModuleHome dao = new ModuleHome();
		
		return dao.findById(id);
	}
	
	public List<Module> getAllModules(){
		
		ModuleHome dao = new ModuleHome();
		
		return dao.findAll();
	}
}
