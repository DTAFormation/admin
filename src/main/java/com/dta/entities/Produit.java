package com.dta.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name="Produit.findAll", query="SELECT p FROM Produit p"),
	@NamedQuery(name="Produit.findById", query="SELECT p FROM Produit p WHERE p.produitId = :id"),
	@NamedQuery(name="Produit.findByName", query="SELECT p FROM Produit p WHERE p.nom = :name")
})
public class Produit {

	@Id
	@GeneratedValue
	@Column(name="produit_id", length=19)
	private int produitId;
	
	@Column(name="nom", unique=true, length=255)
	private String nom;
	
	@Column(name="description", nullable=true, length=255)
	private String description;
	
	@ManyToOne
	private Catalogue catalogue;
	
	@OneToMany(mappedBy="produit")
	@Column(nullable=true)
	private List<Article> articles;
	
	public Produit() {}

	public Produit(String description, String nom, Catalogue catalogue,
			List<Article> articles) {
		this.description = description;
		this.nom = nom;
		this.catalogue = catalogue;
		this.articles = articles;
	}
	
	public Produit(String description, String nom) {
		this.description = description;
		this.nom = nom;
	}

	public int getProduitId() {
		return produitId;
	}

	public void setProduitId(int produitId) {
		this.produitId = produitId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Catalogue getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

	@Override
	public String toString() {
		return "Produit [produitId=" + produitId + ", description="
				+ description + ", nom=" + nom + ", catalogue=" + catalogue.getCatalogueId()
				+ "]";
	}
}
