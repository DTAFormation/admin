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
		if(!isCatalogueNameExists(catalogue.getNom()))
			em.persist(catalogue);
	}

	public boolean isCatalogueNameExists(String name) {
		return !em.createNamedQuery("Catalogue.findByName", Catalogue.class)
				.setParameter("name", name)
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