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
	
	public static String requestGenerator(Article model){

		String request = "SELECT article p FROM Article WHERE ";

		if(model.getNom()!=null){
			request += "p.name='"+model.getNom()+"' ";
		}
		if(model.getPrix()!=-1){
			if(model.getNom()!=null){
				request += "AND p.price='"+model.getPrix()+"' ";
			}else{
				request += "p.price='"+model.getPrix()+"' ";
			}
		}
		if(model.getStock()!=-1){
			if(model.getPrix()!=-1){
				request += "AND p.stock='"+model.getStock()+"' ";
			}else{
				request += "p.stock='"+model.getStock()+"' ";
			}
		}
		return request;
	}
	
}
