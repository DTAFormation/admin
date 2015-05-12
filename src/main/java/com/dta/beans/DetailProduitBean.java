package com.dta.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.dta.entities.Article;

import com.dta.metier.SearchArticleEJB;
import com.dta.metier.ModifyArticleEJB;


@ManagedBean
@ViewScoped
public class DetailProduitBean{
	
	private Article article;
		
	@EJB
	private ModifyArticleEJB ejb;	
	
	@EJB
	private SearchArticleEJB searchArticle;
			
	public Article showDetailArticle(int id) {		
		article = searchArticle.findById(id).get(0);
		return article;
    }
	
	public void saveDetailArticle(){
		ejb.update(article);
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}	
}
