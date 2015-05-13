package com.dta.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Article.findById", query="SELECT a FROM Article a WHERE a.articleId = :id"),
	@NamedQuery(name="Article.findByName", query="SELECT a FROM Article a WHERE a.nom = :name"),
	@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a"),
	@NamedQuery(name="Article.deleteById", query="DELETE FROM Article a WHERE a.articleId = :id")
}) 
public class Article {
	
	public static final String FIND_BY_ID = "Article.findById";
	public static final String FIND_BY_NAME = "Article.findByName";

	@Id
	@GeneratedValue
	@Column(name="article_id", length=19)
	private int articleId;
	@Column(name="nom", unique=true, length=255)
	private String nom;
	@Column(name="prix")
	private float prix;
	@ManyToOne
	private Produit produit;
	@Column(name="stock", length=19)
	private int stock;
	
	public Article() {
		
	}
	
	public Article(String nom, float prix, Produit produit, int stock) {
		this.nom = nom;
		this.prix = prix;
		this.produit = produit;
		this.stock = stock;
	}

	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
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
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", nom=" + nom + ", prix="
				+ prix + ", produit=" + produit + ", stock=" + stock + "]";
	}


}
