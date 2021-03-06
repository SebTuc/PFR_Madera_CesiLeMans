package com.ril.model;
// Generated 9 janv. 2019 10:36:15 by Hibernate Tools 4.3.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ModuleXComposant generated by hbm2java
 */
@Entity
@Table(name = "MODULE_X_COMPOSANT", catalog = "ModularisBDD")
public class ModuleXComposant implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private ModuleXComposantId id;
	private Composant composant;
	private Module module;
	private int quantite;

	public ModuleXComposant() {
	}

	public ModuleXComposant(ModuleXComposantId id, Composant composant, Module module, int quantite) {
		this.id = id;
		this.composant = composant;
		this.module = module;
		this.quantite = quantite;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "composantId", column = @Column(name = "COMPOSANT_ID", nullable = false)),
			@AttributeOverride(name = "moduleId", column = @Column(name = "MODULE_ID", nullable = false)) })
	public ModuleXComposantId getId() {
		return this.id;
	}

	public void setId(ModuleXComposantId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPOSANT_ID", nullable = false, insertable = false, updatable = false)
	public Composant getComposant() {
		return this.composant;
	}

	public void setComposant(Composant composant) {
		this.composant = composant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false, insertable = false, updatable = false)
	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@Column(name = "QUANTITE", nullable = false)
	public int getQuantite() {
		return this.quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
