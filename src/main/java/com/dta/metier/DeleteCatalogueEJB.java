package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Catalogue;

@Stateless(name="DeleteCatalogueEJB")
public class DeleteCatalogueEJB {

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void delete(int catalogueId){
		Catalogue catalogue = em.find(Catalogue.class, catalogueId);
			em.remove(catalogue);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}