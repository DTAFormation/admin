package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.dta.entities.Produit;

@Stateless
public class SearchProduit extends SearchEntities<Produit> {

	public SearchProduit() {
		super(Produit.class);
	}
	
	public Produit findByName(String name){
		Query query = em.createQuery("Produit.findByName");
		query.setParameter("name", name);
		return (Produit) query.getSingleResult();	
	}
		
}