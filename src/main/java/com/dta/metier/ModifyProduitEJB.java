package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Produit;

@Stateless(name="modifyProduitEJB")
public class ModifyProduitEJB {
	private static final Logger LOG = LoggerFactory.getLogger(ModifyProduitEJB.class);

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void update(Produit produit){
		try{
			em.merge(produit);
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