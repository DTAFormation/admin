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
	
	@SuppressWarnings("unchecked")
	public Produit getProduitById(int id) {
		List<Produit> list = em.createQuery("FROM Produit p where p.produitId = :arg")
				.setParameter("arg", id)
				.getResultList();
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Produit> getAllProduits() {		
		return em.createQuery("FROM Produit p")
			.getResultList();
	}
}
