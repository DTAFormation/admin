package com.dta.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="detailProduit")
@SessionScoped
public class DetailProduitBean{

	private int articleId;
	private String articleNom;
	private float articlePrix;
	private int articleStock;
		
	private int produitId;
	private String produitNom;
	
	private int catalogueId;
	private String catalogueDescription;
	private String catalogueNom;
	
	
	
	
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getArticleNom() {
		return articleNom;
	}
	public void setArticleNom(String articleNom) {
		this.articleNom = articleNom;
	}
	public float getArticlePrix() {
		return articlePrix;
	}
	public void setArticlePrix(float articlePrix) {
		this.articlePrix = articlePrix;
	}
	public int getArticleStock() {
		return articleStock;
	}
	public void setArticleStock(int articleStock) {
		this.articleStock = articleStock;
	}
	public int getProduitId() {
		return produitId;
	}
	public void setProduitId(int produitId) {
		this.produitId = produitId;
	}
	public String getProduitNom() {
		return produitNom;
	}
	public void setProduitNom(String produitNom) {
		this.produitNom = produitNom;
	}
	public int getCatalogueId() {
		return catalogueId;
	}
	public void setCatalogueId(int catalogueId) {
		this.catalogueId = catalogueId;
	}
	public String getCatalogueDescription() {
		return catalogueDescription;
	}
	public void setCatalogueDescription(String catalogueDescription) {
		this.catalogueDescription = catalogueDescription;
	}
	public String getCatalogueNom() {
		return catalogueNom;
	}
	public void setCatalogueNom(String catalogueNom) {
		this.catalogueNom = catalogueNom;
	}
}
