package com.dta.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Article;
import com.dta.entities.Produit;

@Stateless(name="AddArticleEJB")
public class AddArticleEJB {
	
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void save(Article article){
		em.persist(article);
	}

	public Produit getProduitById(int id) {
		return em.createNamedQuery("Produit.findById", Produit.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	public List<Produit> getAllProduits() {		
		return em.createNamedQuery("Produit.findAll", Produit.class)
			.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}
}