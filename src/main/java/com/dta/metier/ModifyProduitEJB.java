package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Produit;

@Stateless(name="ModifyProduitEJB")
public class ModifyProduitEJB {
	
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void update(Produit produit){
		if(isProduitExists(produit.getProduitId()))
			em.persist(produit);		
	}
	
	public boolean isProduitExists(int id) {
		return !em.createNamedQuery("Produit.findById", Produit.class)
				.setParameter("produitId", id)
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