package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Article;

@Stateless(name="ModifyArticleEJB")
public class ModifyArticleEJB {

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;	
	
	public void update(Article article){
		em.merge(article);
	}
		
	public EntityManager getEm() {
		return em;
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}	
}
