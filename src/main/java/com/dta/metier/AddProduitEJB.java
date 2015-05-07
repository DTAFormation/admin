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
		if(!isProduitNameExists(produit.getNom()))
			em.persist(produit);		
	}
	
	public boolean isProduitNameExists(String name) {
		return !em.createNamedQuery("Produit.findByName", Produit.class)
				.setParameter("name", name)
				.getResultList()
				.isEmpty();
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
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
}