package com.dta.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produit {

	@Id
	@GeneratedValue
	@Column(name="produit_id", length=19)
	private int produitId;
	
	@Column(name="description", length=255)
	private String description;
	
	@Column(name="nom", length=255)
	private String nom;
	
	@ManyToOne
	private Catalogue catalogue;
	
	@OneToMany(mappedBy="produit")
	private List<Article> articles;
	
	public Produit() {}

	public Produit(String description, String nom,
			Catalogue catalogue) {
		this.description = description;
		this.nom = nom;
		this.catalogue = catalogue;
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
				+ description + ", nom=" + nom + ", catalogue=" + catalogue
				+ "]";
	}
}