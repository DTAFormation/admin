package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Article;

@Stateless(name="DeleteArticleEJB")
public class DeleteArticleEJB {

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void delete(int articleId){
		Article article = em.find(Article.class, articleId);
		em.remove(article);		
	}

	protected EntityManager getEm() {
		return em;
	}

	protected void setEm(EntityManager em) {
		this.em = em;
	}
		
}
