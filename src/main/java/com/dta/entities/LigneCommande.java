package com.dta.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class LigneCommande {

	@Id
	@GeneratedValue
	private int ligneCommandeId;
	
	private int quantite;
	
	@ManyToOne
	private Article article; 
	
	@ManyToOne
	private Commande commande;

	
	public LigneCommande() {
		super();
	}

	public LigneCommande(int quantite, Article article, Commande commande) {
		super();
		this.quantite = quantite;
		this.article = article;
		this.commande = commande;
	}

	public int getLigneCommandeId() {
		return ligneCommandeId;
	}

	public void setLigneCommandeId(int ligneCommandeId) {
		this.ligneCommandeId = ligneCommandeId;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}	
}
