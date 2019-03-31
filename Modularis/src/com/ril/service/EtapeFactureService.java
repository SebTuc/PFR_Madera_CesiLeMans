package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.EtapeFactureHome;
import com.ril.model.EtapeFacture;

public class EtapeFactureService {
	
public int addEtapeFacture(Integer nEtape , String etape , Integer pourcentage) {
		
		EtapeFactureHome dao = new EtapeFactureHome();
		
		if(nEtape != null && etape != null && pourcentage != null) {
			if(!findIfEtapeExist(nEtape)) {
				EtapeFacture etapeFacture = new EtapeFacture();
				
				etapeFacture.setEtape(etape);
				etapeFacture.setnEtape(nEtape);
				etapeFacture.setPourcentage(pourcentage);
				
				dao.persist(etapeFacture);
				
				return etapeFacture.getEtapeFactureId();
			}else {
				return -1;
			}
			
			
		} else {
			
			return -1;
		}
	}

	public boolean findIfEtapeExist(Integer nEtape) {
		boolean flag=false;
		
		for(EtapeFacture etapeFacture : getAllEtapeFacture()) {
			if(etapeFacture.getnEtape() == nEtape) {
				
				flag = true;
			}
			
		}
		return flag;
	}
	
	public boolean findIfEtapeExist(EtapeFacture etapeFacture) {
		boolean flag=false;
		
		for(EtapeFacture etapeFact : getAllEtapeFacture()) {
			if(etapeFact.getnEtape() == etapeFacture.getnEtape()) {
				
				flag = true;
			}
			
		}
		return flag;
	}

	public boolean editEtapeFacture(EtapeFacture etapeFacture) {
		
		EtapeFactureHome dao = new EtapeFactureHome();
		
		if(etapeFacture != null) {
			if(!findIfEtapeExist(etapeFacture)) {
				dao.merge(etapeFacture);
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	public EtapeFacture findFirstEtape() {
		int minValue = 9999;
		EtapeFacture first=null;
		for(EtapeFacture etapeFacture : getAllEtapeFacture()) {
			if(etapeFacture.getnEtape() < minValue) {
				first = etapeFacture;
				minValue = etapeFacture.getnEtape();
			}
		}
		
		return first;
	}
	
	public EtapeFacture findNextEtape(EtapeFacture etapeFacture) {
		
		int minValue = 9999;
		EtapeFacture next=null;
		for(EtapeFacture etapeFact : getAllEtapeFacture()) {
			if(etapeFact.getnEtape() < minValue && etapeFact.getnEtape() > etapeFacture.getnEtape() && etapeFact.getnEtape() != etapeFacture.getnEtape()) {
				next = etapeFact;
				minValue = etapeFact.getnEtape();
			}
		}
		
		return next;
	}
	
	public void removeEtapeFactureById(Integer id) {
		
		EtapeFactureHome dao = new EtapeFactureHome();
		
		if(id != null) {

			EtapeFacture etapeFacture = getEtapeFactureById(id);
			
			dao.remove(etapeFacture);
		}
	}
	public void removeEtapeFacture(EtapeFacture facture) {

		EtapeFactureHome dao = new EtapeFactureHome();
		
		if(facture != null) {
			
			dao.remove(facture);
		}
	}
	
	public EtapeFacture getEtapeFactureById(Integer id) {
		
		EtapeFactureHome dao = new EtapeFactureHome();
		
		return dao.findById(id);
	}
	

	public List<EtapeFacture> getAllEtapeFacture(){
		
		EtapeFactureHome dao = new EtapeFactureHome();
		
		return dao.findAll();
	}

}
