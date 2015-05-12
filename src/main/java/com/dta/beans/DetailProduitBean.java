package com.dta.beans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.dta.entities.Article;
import com.dta.metier.SearchArticleEJB;

@ManagedBean
public class DetailProduitBean{
	
	
	@EJB
	private SearchArticleEJB searchArticle;
	
		
	public Article showDetailArticle(int id) {
		Article monArticle = searchArticle.findById(id).get(0);
		return monArticle;
    }
	
	
}
