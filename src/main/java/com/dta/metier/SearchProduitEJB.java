package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.dta.entities.Produit;

@Stateless(name="SearchProduitEJB")
public class SearchProduitEJB extends SearchEntities<Produit> {

	public SearchProduitEJB() {
		super(Produit.class);
	}
	
	public Produit findByName(String name){
		Query query = em.createQuery("Produit.findByName");
		query.setParameter("name", name);
		return (Produit) query.getSingleResult();	
	}
		
}