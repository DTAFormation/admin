package com.dta.beans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.dta.entities.Article;
import com.dta.metier.SearchArticle;

@ManagedBean
public class DetailProduitBean{
	
	
	@EJB
	private SearchArticle searchArticle;
	
		
	public Article showDetailArticle(int id) {
		Article monArticle = searchArticle.findById(id).get(0);
		return monArticle;
    }
	
	
}
