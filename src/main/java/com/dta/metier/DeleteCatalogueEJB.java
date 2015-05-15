package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Catalogue;

@Stateless(name="DeleteCatalogueEJB")
public class DeleteCatalogueEJB {
	private static final Logger LOG = LoggerFactory.getLogger(DeleteCatalogueEJB.class);

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void delete(int catalogueId){
		Catalogue catalogue = em.find(Catalogue.class, catalogueId);

		try{
			em.remove(catalogue);
		} catch(OptimisticLockException e){
			LOG.error("ERROR: ", e);
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}