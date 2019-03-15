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
 * Angle generated by hbm2java
 */
@Entity
@Table(name = "angle", catalog = "modularisbdd")
public class Angle implements java.io.Serializable {

	private Integer angleId;
	private String typeAngle;
	private float prixUnitaire;
	private Set<Module> modules = new HashSet<Module>(0);

	public Angle() {
	}

	public Angle(String typeAngle, float prixUnitaire) {
		this.typeAngle = typeAngle;
		this.prixUnitaire = prixUnitaire;
	}

	public Angle(String typeAngle, float prixUnitaire, Set<Module> modules) {
		this.typeAngle = typeAngle;
		this.prixUnitaire = prixUnitaire;
		this.modules = modules;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ANGLE_ID", unique = true, nullable = false)
	public Integer getAngleId() {
		return this.angleId;
	}

	public void setAngleId(Integer angleId) {
		this.angleId = angleId;
	}

	@Column(name = "TYPE_ANGLE", nullable = false, length = 25)
	public String getTypeAngle() {
		return this.typeAngle;
	}

	public void setTypeAngle(String typeAngle) {
		this.typeAngle = typeAngle;
	}

	@Column(name = "PRIX_UNITAIRE", nullable = false, precision = 12, scale = 0)
	public float getPrixUnitaire() {
		return this.prixUnitaire;
	}

	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "angle")
	public Set<Module> getModules() {
		return this.modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

}