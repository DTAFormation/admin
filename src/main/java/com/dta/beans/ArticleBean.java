package com.dta.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.dta.metier.DeleteArticle;

@ManagedBean
public class ArticleBean {
	
	@EJB
	private DeleteArticle articleProduit;
	
	public void delete(int articleId) {
		articleProduit.delete(articleId);
    }

}
