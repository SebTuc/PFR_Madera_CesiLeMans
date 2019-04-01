package com.ril.model;

public class EntrepotBean {
	
	private Entrepot entrepot;
	
	private Integer notification;

	public EntrepotBean() {
		
	}
	
	public EntrepotBean(Entrepot entrepot, Integer notification) {
		this.entrepot = entrepot;
		this.notification = notification;
	}

	public Entrepot getEntrepot() {
		return entrepot;
	}

	public void setEntrepot(Entrepot entrepot) {
		this.entrepot = entrepot;
	}

	public Integer getNotification() {
		return notification;
	}

	public void setNotification(Integer notification) {
		this.notification = notification;
	}
	

}
