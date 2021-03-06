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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Etat generated by hbm2java
 */
@Entity
@Table(name = "ETAT", catalog = "ModularisBDD")
public class Etat implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Integer etatId;
	private String nom;
	private Set<Devis> devises = new HashSet<Devis>(0);

	public Etat() {
	}

	public Etat(String nom) {
		this.nom = nom;
	}

	public Etat(String nom, Set<Devis> devises) {
		this.nom = nom;
		this.devises = devises;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ETAT_ID", unique = true, nullable = false)
	public Integer getEtatId() {
		return this.etatId;
	}

	public void setEtatId(Integer etatId) {
		this.etatId = etatId;
	}

	@Column(name = "NOM", nullable = false, length = 25)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "etat")
	public Set<Devis> getDevises() {
		return this.devises;
	}

	public void setDevises(Set<Devis> devises) {
		this.devises = devises;
	}

}
