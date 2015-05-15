package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Catalogue;

@Stateless(name="modifyCatalogueEJB")
public class ModifyCatalogueEJB {
	private static final Logger LOG = LoggerFactory.getLogger(ModifyCatalogueEJB.class);

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void update(Catalogue catalogue){

		try{
			em.merge(catalogue);
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