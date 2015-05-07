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
		Query query = em.createNamedQuery("Article.findByName");
		query.setParameter("name", name);
		return query.getResultList();
	}
		
	public String requestGenerator(Article model, String produit, String catalogue){

		String request = "SELECT a FROM Article a WHERE ";

		if(model.getNom()!=null){
			request += "a.nom LIKE '%"+model.getNom()+"%' ";
		}
		if(Float.floatToRawIntBits(model.getPrix())!=Float.floatToRawIntBits(-1.0f)){
			if(model.getNom()!=null){
				request += "AND a.prix="+model.getPrix()+" ";
			}
			else{
				request += "a.prix="+model.getPrix()+" ";
			}
		}
		if(model.getStock()!=-1){
			if(Float.floatToRawIntBits(model.getPrix())!=Float.floatToRawIntBits(-1.0f) || model.getNom() != null){
				request += "AND a.stock="+model.getStock()+" ";
			}else{
				request += "a.stock="+model.getStock()+" ";
			}
		}
		if(!"".equals(produit)){
			if(Float.floatToRawIntBits(model.getPrix())!=Float.floatToRawIntBits(-1.0f) || model.getNom() != null || model.getStock()!=-1){
				request += "AND a.produit IN (SELECT p.produitId FROM Produit p WHERE p.nom ='"+produit+"') ";
			}else{
				request += "a.produit IN (SELECT p.produitId FROM Produit p WHERE p.nom ='"+produit+"') ";
			}
		}
		if(!"".equals(catalogue)){
			if(Float.floatToRawIntBits(model.getPrix())!=Float.floatToRawIntBits(-1.0f) || model.getNom() != null || model.getStock()!=-1 || !"".equals(produit)){
				request += "AND a.produit IN (SELECT p.produitId FROM Produit p WHERE p.catalogue IN (SELECT c.catalogueId FROM Catalogue c WHERE c.nom ='"+catalogue+"')) ";
			}else{
				request += "a.produit IN (SELECT p.produitId FROM Produit p WHERE p.catalogue IN (SELECT c.catalogueId FROM Catalogue c WHERE c.nom ='"+catalogue+"')) ";
			}
		}
		System.out.println("\n \n \n \n \n  requete: " + request);
		return request;
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> findAll(){
		Query query = em.createNamedQuery("Article.findAll");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> findDetail (Article article, String produit, String catalogue){
		String requete = requestGenerator(article, produit, catalogue);
		Query query = em.createQuery(requete);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> findById(int articleId){
		Query query = em.createNamedQuery("Article.findById");
		query.setParameter("id", articleId);
		return query.getResultList();
	}
	
	public void deleteArticle(int articleId){
		Query query = em.createQuery("DELETE FROM Article a WHERE articleId = "+articleId);
		query.getFirstResult();
	}
}
