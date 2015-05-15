package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Produit;

@Stateless(name="DeleteProduitEJB")
public class DeleteProduitEJB {
	private static final Logger LOG = LoggerFactory.getLogger(DeleteProduitEJB.class);

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void delete(int produitId){
		Produit produit = em.find(Produit.class, produitId);

		try{
			em.remove(produit);
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