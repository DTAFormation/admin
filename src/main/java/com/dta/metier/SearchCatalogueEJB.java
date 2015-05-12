package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.dta.entities.Catalogue;


@Stateless(name="SearchCatalogueEJB")
public class SearchCatalogueEJB extends SearchEntities<Catalogue> {

	public SearchCatalogueEJB() {
		super(Catalogue.class);
	}
	
	public Catalogue findByName(String name){
		Query query = em.createQuery("Catalogue.findByName");
		query.setParameter("name", name);
		return (Catalogue) query.getSingleResult();	
	}
		
}