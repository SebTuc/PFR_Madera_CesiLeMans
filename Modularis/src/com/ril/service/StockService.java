package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.StockHome;
import com.ril.model.Composant;
import com.ril.model.Entrepot;
import com.ril.model.Stock;

public class StockService {
	
	public int addStock(Entrepot entrepot, Integer quantite,Composant composant) {
		
		StockHome dao = new StockHome();
		
		if(entrepot != null && quantite != null && !findIfComposantExistInEntrepot(entrepot , composant)) {
			
			Stock stock = new Stock(entrepot, quantite , composant);
			
			dao.persist(stock);
			
			return stock.getStockId();
			
		} else {
			
			return -1;
		}
	}
	
	private boolean findIfComposantExistInEntrepot(Entrepot entrepot , Composant composant) {
		
		for(Stock stock : entrepot.getStocks()) {
			if(stock.getComposant().getComposantId() == composant.getComposantId()) {
				return true;
			}
		}
		return false;
	}
	
	public void editStock(Stock stock) {
		
		StockHome dao = new StockHome();
		
		if(stock != null) {
		
			dao.merge(stock);
		}
	}
	
	public void removeStockById(Integer id) {
		
		StockHome dao = new StockHome();
		
		if(id != null) {

			Stock stock = getStockById(id);
			
			dao.remove(stock);
		}
	}
	public void removeStock(Stock stock) {

		StockHome dao = new StockHome();
		
		if(stock != null) {
			
			dao.remove(stock);
		}
	}
	
	public Stock getStockById(Integer id) {
		
		StockHome dao = new StockHome();
		
		return dao.findById(id);
	}
	
	public List<Stock> getAllStocks(){
		
		StockHome dao = new StockHome();
		
		return dao.findAll();
	}
	
}