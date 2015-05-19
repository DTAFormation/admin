package com.dta.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.dta.entities.Article;
import com.dta.entities.Produit;
import com.dta.metier.AddArticleEJB;

@ManagedBean(name = "ajoutArticle")
public class AjoutArticleBean {

	Article article;

	private String nom;
	private float prix;
	private int stock;
	private String image;
	private int produitId;
	private Produit produit;

	@EJB
	private AddArticleEJB ejb;

	public void save() {
		produit = ejb.getProduitById(produitId);
		article = new Article(nom, prix, produit, stock, image);
		ejb.save(article);
		notifyAjout();
		cleanAndUpdate();
	}

	private void notifyAjout() {
		FacesMessage msg = new FacesMessage("Article enregistré");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateElement("ajoutArticleForm:msgs");
	}

	private void cleanAndUpdate() {
		nom = null;
		prix = 0f;
		stock = 0;
		image = null;
		produitId = 0;
		produit = null;
		updateElement("ajoutArticleForm");
	}

	private void updateElement(String elementId) {
		RequestContext.getCurrentInstance().update(elementId);
	}

	public List<Produit> getAllProduits() {
		return ejb.getAllProduits();
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

	public int getProduitId() {
		return produitId;
	}

	public void setProduitId(int produitId) {
		this.produitId = produitId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}