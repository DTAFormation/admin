package com.dta.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Catalogue;
import com.dta.entities.Produit;

@Stateless(name="AddProduitEJB")
public class AddProduitEJB {
	
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void save(Produit produit){
		em.persist(produit);
	}
	
	public Catalogue getCatalogueById(int id) {
		return em.createNamedQuery("Catalogue.findById", Catalogue.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	public List<Catalogue> getAllCatalogues() {		
		return em.createNamedQuery("Catalogue.findAll", Catalogue.class)
			.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}
}