package com.dta.beans;

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
	private String catnom;
	
	@EJB
	private AddProduitEJB ejb;

	public void save(){
		Catalogue catalogue = ejb.getCatalogueByName(catnom).get(0);
		produit = new Produit(description, nom, catalogue, null);
		ejb.save(produit);
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

	public String getCatnom() {
		return catnom;
	}

	public void setCatnom(String catnom) {
		this.catnom = catnom;
	}
	
	
}
