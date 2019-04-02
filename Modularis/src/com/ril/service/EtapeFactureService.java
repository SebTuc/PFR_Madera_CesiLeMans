package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.EtapeFactureHome;
import com.ril.model.EtapeFacture;

public class EtapeFactureService {
	
	public int addEtapeFacture(Integer nEtape , String etape , Integer pourcentage) {
		
		EtapeFactureHome dao = new EtapeFactureHome();
		
		if(nEtape != null && etape != null && pourcentage != null) {
			if(findifEtapeAndPercentageIsLogical(nEtape , pourcentage)) {
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
	

	private boolean findifEtapeAndPercentageIsLogical(Integer nEtape , Integer percentage) {
		boolean flag = false;
		if(!findIfEtapeExist(nEtape)) {
			EtapeFacture etapeAfter = findNextEtapeBynEtape(nEtape);
			EtapeFacture etapeBefore = findBeforeEtapeBynEtape(nEtape);
			
			if(etapeAfter != null && etapeBefore != null) {
				//Millieu
				if(etapeAfter.getPourcentage() > percentage && etapeBefore.getPourcentage() < percentage) {
					flag = true;
				}
			}else if(etapeAfter != null) {
				//Dernier
				if(etapeAfter.getPourcentage() > percentage) {
					flag = true;
					
				}
				
			}else if(etapeBefore != null) {
				//Premier
				if(etapeBefore.getPourcentage() < percentage) {
					flag=true;
				}
				
			}else {
				//Aucune autre valeur donc c'est bon
				flag = true;
			}
		}
		
		return flag;
	}
	
	private boolean PercentageIsLogical(Integer nEtape , Integer percentage) {
		boolean flag = false;
		EtapeFacture etapeFacture = findNextEtapeBynEtape(nEtape);
		if(etapeFacture.getPourcentage() > percentage) {
			flag = true;
		}
		return flag;
	}

	
	private boolean findIfEtapeExist(Integer nEtape) {
		boolean flag=false;
		
		for(EtapeFacture etapeFacture : getAllEtapeFacture()) {
			if(etapeFacture.getnEtape() == nEtape) {
				
				flag = true;
			}
			
		}
		return flag;
	}
	
	private boolean findIfEtapeExistWithoutThisEtape(EtapeFacture etapeFacture) {
		boolean flag=false;
		
		for(EtapeFacture etapeFact : getAllEtapeFacture()) {
			if(etapeFact.getnEtape() == etapeFacture.getnEtape() && etapeFact.getEtapeFactureId() != etapeFacture.getEtapeFactureId()) {
				
				flag = true;
			}
			
		}
		return flag;
	}
	

	public boolean editEtapeFacture(EtapeFacture etapeFacture) {
		
		EtapeFactureHome dao = new EtapeFactureHome();
		
		if(etapeFacture != null) {
			if(!findIfEtapeExistWithoutThisEtape(etapeFacture)) {
				if(PercentageIsLogical(etapeFacture.getnEtape(),etapeFacture.getPourcentage())) {
					dao.merge(etapeFacture);
					return true;
				}else {
					return false;
				}
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
	
	public EtapeFacture findNextEtapeBynEtape(Integer nEtape) {
		
		int minValue = 9999;
		EtapeFacture next=null;
		for(EtapeFacture etapeFact : getAllEtapeFacture()) {
			if(etapeFact.getnEtape() < minValue && etapeFact.getnEtape() > nEtape && etapeFact.getnEtape() != nEtape) {
				next = etapeFact;
				minValue = etapeFact.getnEtape();
			}
		}
		
		return next;
	}
	
	public EtapeFacture findBeforeEtape(EtapeFacture etapeFacture) {
		
		int maxValue = -9999;
		EtapeFacture before=null;
		for(EtapeFacture etapeFact : getAllEtapeFacture()) {
			if(etapeFact.getnEtape() > maxValue && etapeFact.getnEtape() < etapeFacture.getnEtape() && etapeFact.getnEtape() != etapeFacture.getnEtape()) {
				before = etapeFact;
				maxValue = etapeFact.getnEtape();
			}
		}
		
		return before;
	}
	
	public EtapeFacture findBeforeEtapeBynEtape(Integer nEtape) {
		
		int maxValue = -9999;
		EtapeFacture before=null;
		for(EtapeFacture etapeFact : getAllEtapeFacture()) {
			if(etapeFact.getnEtape() > maxValue && etapeFact.getnEtape() < nEtape && etapeFact.getnEtape() != nEtape) {
				before = etapeFact;
				maxValue = etapeFact.getnEtape();
			}
		}
		
		return before;
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
