package com.dta.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.dta.entities.Article;

@Stateless(name="SearchArticleEJB")
public class SearchArticleEJB extends SearchEntities<Article>{

    private String request;
    
	public SearchArticleEJB() {
		super(Article.class);
	}
			
	public String requestGenerator(Article model, String produit, String catalogue){

		request = "SELECT a FROM Article a ";

		addWhereNom(model);
		addWherePrix(model);
		addWhereStock(model);
		addWhereProduit(model, produit);
		addWhereCatalogue(model, produit, catalogue);
		
		return request;
	}
	
	private void addWherePrix(Article model) {
	    if(Float.floatToRawIntBits(model.getPrix())!=Float.floatToRawIntBits(-1.0f)){
            if(model.getNom()!=null){
                request += "AND a.prix="+model.getPrix()+" ";
            }
            else{
                request += "WHERE a.prix="+model.getPrix()+" ";
            }
        }
	}
	
	private void addWhereNom(Article model) {
	    if(model.getNom()!=null){
            request += "WHERE a.nom LIKE '%"+model.getNom()+"%' ";
        }
	}
	
	private void addWhereStock(Article model) {
	    if(model.getStock()!=-1){
            if(Float.floatToRawIntBits(model.getPrix())!=Float.floatToRawIntBits(-1.0f) || model.getNom() != null){
                request += "AND a.stock="+model.getStock()+" ";
            }else{
                request += "WHERE a.stock="+model.getStock()+" ";
            }
        }
	}
	
	private void addWhereProduit(Article model, String produit) {
	    if(!"".equals(produit)){
            if(Float.floatToRawIntBits(model.getPrix())!=Float.floatToRawIntBits(-1.0f) || model.getNom() != null || model.getStock()!=-1){
                request += "AND a.produit IN (SELECT p.produitId FROM Produit p WHERE p.nom ='"+produit+"') ";
            }else{
                request += "WHERE a.produit IN (SELECT p.produitId FROM Produit p WHERE p.nom ='"+produit+"') ";
            }
        }
    }
	
	private void addWhereCatalogue(Article model, String produit, String catalogue) {
	    if(!"".equals(catalogue)){
            if(Float.floatToRawIntBits(model.getPrix())!=Float.floatToRawIntBits(-1.0f) || model.getNom() != null || model.getStock()!=-1 || !"".equals(produit)){
                request += "AND a.produit IN (SELECT p.produitId FROM Produit p WHERE p.catalogue IN (SELECT c.catalogueId FROM Catalogue c WHERE c.nom ='"+catalogue+"')) ";
            }else{
                request += "WHERE a.produit IN (SELECT p.produitId FROM Produit p WHERE p.catalogue IN (SELECT c.catalogueId FROM Catalogue c WHERE c.nom ='"+catalogue+"')) ";
            }
        }
	}
	
	@Override
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
	public List<Article> findByName(String name){
		Query query = em.createNamedQuery("Article.findByName");
		query.setParameter("name", name);
		return query.getResultList();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Article> findById(int articleId){
		Query query = em.createNamedQuery("Article.findById");
		query.setParameter("id", articleId);
		return query.getResultList();
	}
	

}
