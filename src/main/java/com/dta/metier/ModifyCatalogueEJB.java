package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Catalogue;

@Stateless(name="ModifyCatalogueEJB")
public class ModifyCatalogueEJB {

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void update(Catalogue catalogue){
		if(isCatalogueExists(catalogue.getCatalogueId()))
			em.persist(catalogue);
	}

	public boolean isCatalogueExists(int id) {
		return !em.createNamedQuery("Catalogue.findById", Catalogue.class)
				.setParameter("catalogueId", id)
				.getResultList()
				.isEmpty();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}