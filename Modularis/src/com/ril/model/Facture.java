package com.ril.model;
// Generated 9 janv. 2019 10:36:15 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Facture generated by hbm2java
 */
@Entity
@Table(name = "facture", catalog = "modularisbdd")
public class Facture implements java.io.Serializable {

	private Integer factureId;
	private Devis devis;
	private String etape;
	private int pourcentage;

	public Facture() {
	}

	public Facture(Devis devis, String etape, int pourcentage) {
		this.devis = devis;
		this.etape = etape;
		this.pourcentage = pourcentage;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "FACTURE_ID", unique = true, nullable = false)
	public Integer getFactureId() {
		return this.factureId;
	}

	public void setFactureId(Integer factureId) {
		this.factureId = factureId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVIS_ID", nullable = false)
	public Devis getDevis() {
		return this.devis;
	}

	public void setDevis(Devis devis) {
		this.devis = devis;
	}

	@Column(name = "ETAPE", nullable = false, length = 100)
	public String getEtape() {
		return this.etape;
	}

	public void setEtape(String etape) {
		this.etape = etape;
	}

	@Column(name = "POURCENTAGE", nullable = false)
	public int getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

}
