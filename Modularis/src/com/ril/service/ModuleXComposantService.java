package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.ModuleXComposantHome;
import com.ril.model.Composant;
import com.ril.model.Module;
import com.ril.model.ModuleXComposant;
import com.ril.model.ModuleXComposantId;

public class ModuleXComposantService {
	
	public int addModuleXComposant(int quantite, Module module, Composant composant) {
		
		ModuleXComposantHome dao = new ModuleXComposantHome();
		
		if(quantite != 0 && module != null && composant != null) {
			
			ModuleXComposantId moduleXComposantId = new ModuleXComposantId(composant.getComposantId(),module.getModuleId());
			
			ModuleXComposant moduleXComposant = new ModuleXComposant(moduleXComposantId,composant, module, quantite);
			
			dao.persist(moduleXComposant);
			
			return module.getModuleId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editModuleXComposant(ModuleXComposant module) {
		
		ModuleXComposantHome dao = new ModuleXComposantHome();
		
		if(module != null) {
		
			dao.merge(module);
		}
	}
	
	public void removeModuleXComposantById(Integer composantId, Integer moduleId) {
		
		ModuleXComposantHome dao = new ModuleXComposantHome();
		
		if(composantId != null && moduleId != null  ) {

			ModuleXComposant module = getModuleXComposantById(composantId,moduleId);
			
			dao.remove(module);
		}
	}
	public void removeModuleXComposant(ModuleXComposant module) {

		ModuleXComposantHome dao = new ModuleXComposantHome();
		
		if(module != null) {
			
			dao.remove(module);
		}
	}
	
	public ModuleXComposant getModuleXComposantById(Integer composantId, Integer moduleId) {
		
		ModuleXComposantId moduleXComposantId = new ModuleXComposantId(composantId,moduleId);
		
		ModuleXComposantHome dao = new ModuleXComposantHome();
		
		return dao.findById(moduleXComposantId);
	}
	
	
	public List<ModuleXComposant> getAllModuleXComposants(){
		
		ModuleXComposantHome dao = new ModuleXComposantHome();
		
		return dao.findAll();
	}
	
	public List<ModuleXComposant> getAllModuleXComposantByModule(Module module){
		
		
		ModuleXComposantHome dao = new ModuleXComposantHome();
		
		return dao.findAllByModule(module);
		
	}

}
