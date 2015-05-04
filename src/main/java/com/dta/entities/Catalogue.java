package com.dta.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Catalogue {

	@Id
	@GeneratedValue
	@Column(name="catalogue_id", length=19)
	private int catalogueId;
	
	@Column(name="description", length=255)
	private String description;
	
	@Column(name="nom", length=255)
	private String nom;
	
	@OneToMany(mappedBy="catalogue")
	@Column(nullable=true)
	private List<Produit> produits;
	
	public Catalogue() {}

	public Catalogue(String description, String nom,
			List<Produit> produits) {
		this.description = description;
		this.nom = nom;
		this.produits = produits;
	}

	public int getCatalogueId() {
		return catalogueId;
	}

	public void setCatalogueId(int catalogueId) {
		this.catalogueId = catalogueId;
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
	
	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	@Override
	public String toString() {
		return "Catalogue [catalogueId=" + catalogueId + ", description="
				+ description + ", nom=" + nom + ", nbProduits " + produits.size() + "]";
	}
}