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
	public List<Catalogue> getCatalogueByName(String name) {
		return em.createQuery("FROM Catalogue c where c.nom = :arg")
			.setParameter("arg", name)
			.getResultList();
	}
}
