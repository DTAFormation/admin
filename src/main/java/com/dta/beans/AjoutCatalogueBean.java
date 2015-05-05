package com.dta.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.dta.entities.Catalogue;
import com.dta.metier.AddCatalogueEJB;

@ManagedBean(name="ajoutCatalogue")
public class AjoutCatalogueBean {
	
	Catalogue catalogue;
	
	private String description;
	
	private String nom;

	@EJB
	private AddCatalogueEJB ejb;

	@Override
	public String toString() {
		return "AjoutCatalogueBean [catalogue=" + catalogue + ", description="
				+ description + ", nom=" + nom + "]";
	}


	public void save(){
		catalogue = new Catalogue(description, nom, null);
		ejb.save(catalogue);
	}


	public Catalogue getCatalogue() {
		return catalogue;
	}


	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
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
