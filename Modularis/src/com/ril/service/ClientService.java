package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.ClientHome;
import com.ril.model.Client;
import com.ril.model.DonneesPersonelle;

public class ClientService {
	
	public int addClient(DonneesPersonelle donneesPersonelle) {
		
		ClientHome dao = new ClientHome();
		
		if(donneesPersonelle != null) {
			
			Client client = new Client();
			
			client.setDonneesPersonelle(donneesPersonelle);
			
			dao.persist(client);
			
			return client.getClientId();
			
		} else {
			
			return -1;
		}
	}
	
	public void editClient(Client client) {
		
		ClientHome dao = new ClientHome();
		
		if(client != null) {
		
			dao.merge(client);
		}
	}
	
	public void removeClientById(Integer id) {
		
		ClientHome dao = new ClientHome();
		
		if(id != null) {

			Client client = getClientById(id);
			
			dao.remove(client);
		}
	}
	public void removeClient(Client client) {

		ClientHome dao = new ClientHome();
		
		if(client != null) {
			
			dao.remove(client);
		}
	}
	
	public Client getClientById(Integer id) {
		
		ClientHome dao = new ClientHome();
		
		return dao.findById(id);
	}
	
	public List<Client> getAllClients(){
		
		ClientHome dao = new ClientHome();
		
		return dao.findAll();
	}
}