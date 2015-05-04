package com.dta.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.dta.entities.Produit;
import com.dta.metier.AddProduitEJB;

@ManagedBean(name="addProduit")
public class AjoutProduitBean {
	
	Produit produit;
	
	private String description;
	
	private String nom;

	@EJB
	private AddProduitEJB ejb;

	@Override
	public String toString() {
		return "AjoutProduitBean [produit=" + produit + ", description="
				+ description + ", nom=" + nom + "]";
	}


	public void save(){
		produit = new Produit(description, nom, null, null);
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

	
}
