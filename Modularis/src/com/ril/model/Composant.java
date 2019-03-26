package com.ril.model;
// Generated 9 janv. 2019 13:51:31 by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Composant generated by hbm2java
 */
@Entity
@Table(name = "composant", catalog = "modularisbdd")
public class Composant implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer composantId;
	private FamilleComposant familleComposant;
	private Fournisseur fournisseur;
	private Materiaux materiaux;
	private String nom;
	private float prixUnitaire;
	private Set<Stock> stocks = new HashSet<Stock>(0);
	private Set<ModuleXComposant> moduleXComposants = new HashSet<ModuleXComposant>(0);

	public Composant() {
	}

	public Composant(FamilleComposant familleComposant, Fournisseur fournisseur, Materiaux materiaux,
			UniteMesure uniteMesure, String nom, float prixUnitaire) {
		this.familleComposant = familleComposant;
		this.fournisseur = fournisseur;
		this.materiaux = materiaux;
		this.nom = nom;
		this.prixUnitaire = prixUnitaire;
	}

	public Composant(FamilleComposant familleComposant, Fournisseur fournisseur, Materiaux materiaux,
			UniteMesure uniteMesure, String nom, float prixUnitaire, Set<Stock> stocks,
			Set<ModuleXComposant> moduleXComposants) {
		this.familleComposant = familleComposant;
		this.fournisseur = fournisseur;
		this.materiaux = materiaux;
		this.nom = nom;
		this.prixUnitaire = prixUnitaire;
		this.stocks = stocks;
		this.moduleXComposants = moduleXComposants;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "COMPOSANT_ID", unique = true, nullable = false)
	public Integer getComposantId() {
		return this.composantId;
	}

	public void setComposantId(Integer composantId) {
		this.composantId = composantId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FAMILLE_COMPOSANT_ID", nullable = false)
	public FamilleComposant getFamilleComposant() {
		return this.familleComposant;
	}

	public void setFamilleComposant(FamilleComposant familleComposant) {
		this.familleComposant = familleComposant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FOURNISSEUR_ID", nullable = false)
	public Fournisseur getFournisseur() {
		return this.fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MATERIAUX_ID", nullable = false)
	public Materiaux getMateriaux() {
		return this.materiaux;
	}

	public void setMateriaux(Materiaux materiaux) {
		this.materiaux = materiaux;
	}

	@Column(name = "NOM", nullable = false, length = 100)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "PRIX_UNITAIRE", nullable = false, precision = 12, scale = 0)
	public float getPrixUnitaire() {
		return this.prixUnitaire;
	}

	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "composant_x_stock", catalog = "modularisbdd", joinColumns = {
			@JoinColumn(name = "COMPOSANT_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "STOCK_ID", nullable = false, updatable = false) })
	public Set<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "composant")
	public Set<ModuleXComposant> getModuleXComposants() {
		return this.moduleXComposants;
	}

	public void setModuleXComposants(Set<ModuleXComposant> moduleXComposants) {
		this.moduleXComposants = moduleXComposants;
	}

}
