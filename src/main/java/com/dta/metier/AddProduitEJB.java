package com.dta.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Produit;
import com.dta.entities.Catalogue;

@Stateless(name="AddProduitEJB")
public class AddProduitEJB {
	
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void save(Produit produit){
		em.persist(produit);
	}
	
	@SuppressWarnings("unchecked")
	public List<Catalogue> getCatalogueById(int id) {
		return em.createQuery("FROM Catalogue c where c.catalogueId = :arg")
			.setParameter("arg", id)
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Catalogue> getAllCatalogues() {		
		return em.createQuery("FROM Catalogue c")
			.getResultList();
	}
}
