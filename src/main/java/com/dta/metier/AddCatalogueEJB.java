package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Catalogue;

@Stateless(name="AddCatalogueEJB")
public class AddCatalogueEJB {
	private static final Logger LOG = LoggerFactory.getLogger(AddCatalogueEJB.class);
	
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void save(Catalogue catalogue){
		if(!isCatalogueNameExists(catalogue.getNom())){
			try{
				em.persist(catalogue);	
			} catch(OptimisticLockException e){
				LOG.error("ERROR: ", e);
			}
		}
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