package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Catalogue;

@Stateless(name="AddCatalogueEJB")
public class AddCatalogueEJB {
	
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void save(Catalogue catalogue){
		em.persist(catalogue);
	}
	
	public EntityManager getEm() {
		return em;
	}
}
