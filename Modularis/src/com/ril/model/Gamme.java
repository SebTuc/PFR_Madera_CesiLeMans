package com.ril.model;
// Generated 9 janv. 2019 10:36:15 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Gamme generated by hbm2java
 */
@Entity
@Table(name = "gamme", catalog = "modularisbdd")
public class Gamme implements java.io.Serializable {

	private Integer gammeId;
	private String nom;
	private Set<Module> modules = new HashSet<Module>(0);

	public Gamme() {
	}

	public Gamme(String nom) {
		this.nom = nom;
	}

	public Gamme(String nom, Set<Module> modules) {
		this.nom = nom;
		this.modules = modules;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "GAMME_ID", unique = true, nullable = false)
	public Integer getGammeId() {
		return this.gammeId;
	}

	public void setGammeId(Integer gammeId) {
		this.gammeId = gammeId;
	}

	@Column(name = "NOM", nullable = false, length = 100)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gamme")
	public Set<Module> getModules() {
		return this.modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

}