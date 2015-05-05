package com.dta.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.dta.entities.Catalogue;
import com.dta.entities.Produit;
import com.dta.metier.AddProduitEJB;

@ManagedBean(name="ajoutProduit")
public class AjoutProduitBean {

	Produit produit;
	
	private String nom;
	private String description;
	private int catalogueId;
	private Catalogue catalogue;
	
	@EJB
	private AddProduitEJB ejb;

	public void save(){
		catalogue = ejb.getCatalogueById(catalogueId);
		produit = new Produit(description, nom, catalogue, null);
		ejb.save(produit);
	}
	
	public List<Catalogue> getAllCatalogues() {
		return ejb.getAllCatalogues();
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
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

	public int getCatalogueId() {
		return catalogueId;
	}

	public void setCatalogueId(int catalogueId) {
		this.catalogueId = catalogueId;
	}

	

	
	
}
