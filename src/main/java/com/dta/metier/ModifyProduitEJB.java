package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Produit;

@Stateless(name="modifyProduitEJB")
public class ModifyProduitEJB {
	
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void update(Produit produit){
		em.merge(produit);		
	}
	
	public EntityManager getEm() {
		return em;
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
}