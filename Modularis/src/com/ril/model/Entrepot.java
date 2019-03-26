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
 * Entrepot generated by hbm2java
 */
@Entity
@Table(name = "entrepot", catalog = "modularisbdd")
public class Entrepot implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer entrepotId;
	private String lieux;
	private Set<Stock> stocks = new HashSet<Stock>(0);

	public Entrepot() {
	}

	public Entrepot(String lieux) {
		this.lieux = lieux;
	}

	public Entrepot(String lieux, Set<Stock> stocks) {
		this.lieux = lieux;
		this.stocks = stocks;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ENTREPOT_ID", unique = true, nullable = false)
	public Integer getEntrepotId() {
		return this.entrepotId;
	}

	public void setEntrepotId(Integer entrepotId) {
		this.entrepotId = entrepotId;
	}

	@Column(name = "LIEUX", nullable = false)
	public String getLieux() {
		return this.lieux;
	}

	public void setLieux(String lieux) {
		this.lieux = lieux;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entrepot")
	public Set<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

}
