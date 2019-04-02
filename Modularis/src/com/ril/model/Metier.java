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
 * Metier generated by hbm2java
 */
@Entity
@Table(name = "metier", catalog = "modularisbdd")
public class Metier implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer metierId;
	private String nom;
	private Set<Utilisateur> utilisateurs = new HashSet<Utilisateur>(0);

	public Metier() {
	}

	public Metier(String nom) {
		this.nom = nom;
	}

	public Metier(String nom, Set<Utilisateur> utilisateurs) {
		this.nom = nom;
		this.utilisateurs = utilisateurs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "METIER_ID", unique = true, nullable = false)
	public Integer getMetierId() {
		return this.metierId;
	}

	public void setMetierId(Integer metierId) {
		this.metierId = metierId;
	}

	@Column(name = "NOM", nullable = false, length = 50)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "metier")
	public Set<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
