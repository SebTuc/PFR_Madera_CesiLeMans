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
@Table(name = "image", catalog = "modularisbdd")
public class Image {

	private Integer imageId;
	
	private byte[] photo;
	
	private Set<Projet> projet = new HashSet<Projet>();
	
	public Image() {
	}

	
	public Image(byte[] photo) {
		this.photo = photo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PROJET_ID", unique = true, nullable = false)
	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "image")
	public Set<Projet> getProjet() {
		return projet;
	}

	public void setProjet(Set<Projet> projet) {
		this.projet = projet;
	}

	
}
