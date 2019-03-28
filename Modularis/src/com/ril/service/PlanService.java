package com.ril.service;

import com.ril.daoHibernate.PlanHome;
import com.ril.model.Plan;
import com.ril.model.Projet;

public class PlanService {
	
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
	
	public void removePlanById(Integer id) {
		
		PlanHome dao = new PlanHome();
		
		if(id != null) {

			Plan plan = getPlanById(id);
			
			dao.remove(plan);
		}
	}
	public void removePlan(Plan plan) {

		PlanHome dao = new PlanHome();
		
		if(plan != null) {
			
			dao.remove(plan);
		}
	}
	
	public Plan getPlanById(Integer id) {
		
		PlanHome dao = new PlanHome();
		
		return dao.findById(id);
	}
}