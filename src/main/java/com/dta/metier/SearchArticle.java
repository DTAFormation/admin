package com.dta.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.dta.entities.Article;

@Stateless
public class SearchArticle extends SearchEntities<Article>{

	public SearchArticle() {
		super(Article.class);

	}
	
	@SuppressWarnings("unchecked")
	public List<Article> findByName(String name){
		Query query = em.createQuery("SELECT a FROM Article a WHERE a.nom = :name");
		query.setParameter("name", name);
		return query.getResultList();
		
	}
	
}
