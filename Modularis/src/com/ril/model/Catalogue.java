package com.ril.model;
// Generated 9 janv. 2019 10:36:15 by Hibernate Tools 4.3.5.Final

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
import javax.persistence.Table;

/**
 * Catalogue generated by hbm2java
 */
@Entity
@Table(name = "catalogue", catalog = "modularisbdd")
public class Catalogue implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer catalogueId;
	private int annee;
	private String catalogueNom;
	private Set<Projet> projets = new HashSet<Projet>(0);

	public Catalogue() {
	}

	public Catalogue(int annee) {
		this.annee = annee;
	}

	public Catalogue(int annee, Set<Projet> projets) {
		this.annee = annee;
		this.projets = projets;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CATALOGUE_ID", unique = true, nullable = false)
	public Integer getCatalogueId() {
		return this.catalogueId;
	}

	public void setCatalogueId(Integer catalogueId) {
		this.catalogueId = catalogueId;
	}

	@Column(name = "ANNEE", nullable = false)
	public int getAnnee() {
		return this.annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	@Column(name = "CATALOGUE_NOM", nullable = false)
	public String getCatalogueNom() {
		return catalogueNom;
	}

	public void setCatalogueNom(String catalogueNom) {
		this.catalogueNom = catalogueNom;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PROJET_X_CATALOGUE", catalog = "modularisbdd", joinColumns = {
			@JoinColumn(name = "CATALOGUE_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "PROJET_ID", nullable = false, updatable = false) })
	public Set<Projet> getProjets() {
		return this.projets;
	}

	public void setProjets(Set<Projet> projets) {
		this.projets = projets;
	}

}
