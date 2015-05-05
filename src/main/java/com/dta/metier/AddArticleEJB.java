package com.dta.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public List<Produit> getProduitByName(String name) {
		return em.createQuery("FROM Produit p where p.nom = :arg")
			.setParameter("arg", name)
			.getResultList();
	}
}
