package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import com.dta.entities.Catalogue;

@Stateless(name="modifyCatalogueEJB")
public class ModifyCatalogueEJB {

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void update(Catalogue catalogue){

		try{
			em.merge(catalogue);
		} catch(OptimisticLockException e){
			e.printStackTrace();
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}