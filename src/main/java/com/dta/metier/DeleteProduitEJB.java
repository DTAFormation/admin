package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import com.dta.entities.Produit;

@Stateless(name="DeleteProduitEJB")
public class DeleteProduitEJB {

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void delete(int produitId){
		Produit produit = em.find(Produit.class, produitId);

		try{
			em.remove(produit);
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