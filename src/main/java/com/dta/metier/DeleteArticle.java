package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Article;

@Stateless
public class DeleteArticle {

	@PersistenceContext(unitName="ecommercedb")
	protected EntityManager em;
	
	public void delete(int articleId){
		Article article = em.find(Article.class, articleId);
		System.out.println(article.toString());
		em.remove(article);		
	}
	
}
