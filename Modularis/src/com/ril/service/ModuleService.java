package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.ModuleHome;
import com.ril.model.Angle;
import com.ril.model.Gamme;
import com.ril.model.Module;
import com.ril.model.UniteMesure;

public class ModuleService {

	public int addModule(Angle angle, Gamme gamme, String nom , UniteMesure uniteMesure) {
		
		ModuleHome dao = new ModuleHome();
		
		if(angle != null && gamme != null && nom != null) {
			
			Module module = new Module(angle, gamme, nom , uniteMesure);
			
			dao.persist(module);
			
			return module.getModuleId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editModule(Module module) {
		
		ModuleHome dao = new ModuleHome();
		
		if(module != null) {
		
			dao.merge(module);
		}
	}
	
	public void removeModuleById(Integer id) {
		
		ModuleHome dao = new ModuleHome();
		
		if(id != null) {

			Module module = getModuleById(id);
			
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
