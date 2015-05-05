package com.dta.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.dta.entities.Article;
import com.dta.entities.Produit;
import com.dta.metier.AddArticleEJB;

@ManagedBean(name="ajoutArticle")
public class AjoutArticleBean {
	
	Article article;
	
	private String nom;
	private float prix;
	private String prodnom;
	private int stock;
	
	@EJB
	private AddArticleEJB ejb;

	public void save(){
		Produit produit = ejb.getProduitByName(prodnom).get(0);
		article = new Article(nom, prix, produit, stock);
		ejb.save(article);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getProdnom() {
		return prodnom;
	}

	public void setProdnom(String prodnom) {
		this.prodnom = prodnom;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	
	
}