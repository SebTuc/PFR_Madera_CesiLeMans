package com.ril.service;

import java.util.List;

import com.ril.daoHibernate.CatalogueHome;
import com.ril.model.Catalogue;

public class CatalogueService {

	public int addCatalogue(Integer annee) {

		CatalogueHome dao = new CatalogueHome();

		if(annee != null) {

			Catalogue catalogue = new Catalogue();

			catalogue.setAnnee(annee);

			dao.persist(catalogue);

			return catalogue.getCatalogueId();

		} else {

			return -1;
		}
	}

	public void editCatalogue(Catalogue catalogue) {

		CatalogueHome dao = new CatalogueHome();

		if(catalogue != null) {

			dao.merge(catalogue);
		}
	}

	public void removeCatalogueById(Integer id) {

		CatalogueHome dao = new CatalogueHome();

		if(id != null) {

			Catalogue catalogue = getCatalogueById(id);

			dao.remove(catalogue);
		}
	}
	public void removeCatalogue(Catalogue catalogue) {

		CatalogueHome dao = new CatalogueHome();

		if(catalogue != null) {

			dao.remove(catalogue);
		}
	}

	public Catalogue getCatalogueById(Integer id) {

		CatalogueHome dao = new CatalogueHome();

		return dao.findById(id);
	}

	public List<Catalogue> getAllCatalogues(){

		CatalogueHome dao = new CatalogueHome();

		return dao.findAll();
	}
}
