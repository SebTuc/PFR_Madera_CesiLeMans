package com.ril.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "ETAPE_FACTURE", catalog = "ModularisBDD")
public class EtapeFacture {
	
	
	private Integer etapeFactureId;
	
	private String etape;
	
	private int pourcentage;
	
	private int nEtape;
	
	private Set<Facture> factures = new HashSet<Facture>(0);
	
	//CONSTRUCTOR
	public EtapeFacture() {
		
	}
	
	public EtapeFacture(String etape, int pourcentage, int nEtape) {
		this.etape = etape;
		this.pourcentage = pourcentage;
		this.nEtape = nEtape;
	}
	
	public EtapeFacture(String etape, int pourcentage, int nEtape, Set<Facture> factures) {
		this.etape = etape;
		this.pourcentage = pourcentage;
		this.nEtape = nEtape;
		this.factures = factures;
	}

	//GETTER AND SETTER
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ETAPE_FACTURE_ID", unique = true, nullable = false)
	public Integer getEtapeFactureId() {
		return etapeFactureId;
	}

	public void setEtapeFactureId(Integer etapeFactureId) {
		this.etapeFactureId = etapeFactureId;
	}

	@Column(name ="ETAPE" , nullable = false)
	public String getEtape() {
		return etape;
	}

	public void setEtape(String etape) {
		this.etape = etape;
	}

	@Column(name ="POURCENTAGE" , nullable = false)
	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	@Column(name ="N_ETAPE" ,unique = true , nullable = false)
	public int getnEtape() {
		return nEtape;
	}

	public void setnEtape(int nEtape) {
		this.nEtape = nEtape;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "etapeFacture")
	public Set<Facture> getFactures() {
		return factures;
	}

	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}



}
