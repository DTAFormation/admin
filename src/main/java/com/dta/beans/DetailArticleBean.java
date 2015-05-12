package com.dta.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.dta.entities.Article;
import com.dta.metier.ModifyArticleEJB;
import com.dta.metier.ModifyCatalogueEJB;
import com.dta.metier.ModifyProduitEJB;
import com.dta.metier.SearchArticleEJB;

@ManagedBean
@ViewScoped
public class DetailArticleBean {

	private Article article;

	@EJB
	private ModifyArticleEJB ejbArticle;
	
	@EJB
	private ModifyProduitEJB ejbProduit;
	
	@EJB
	private ModifyCatalogueEJB ejbCatalogue;

	@EJB
	private SearchArticleEJB searchArticle;

	public Article showDetailArticle(int id) {
		return searchArticle.findById(id).get(0);
	}

	public void initArticle(int id) {
		article = searchArticle.findById(id).get(0);
	}

	public void saveDetailsArticle() {
		ejbArticle.update(article);
		ejbProduit.update(article.getProduit());
		ejbCatalogue.update(article.getProduit().getCatalogue());		
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}
