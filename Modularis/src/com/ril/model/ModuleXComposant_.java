package com.ril.model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

public class ModuleXComposant_ {

	public static volatile SingularAttribute<ModuleXComposant , ModuleXComposantId> id;
	public static volatile SingularAttribute<ModuleXComposant , Composant> composant;
	public static volatile SingularAttribute<ModuleXComposant , Module> module;
	public static volatile SetAttribute<ModuleXComposant , Integer> quantite;
	
}
