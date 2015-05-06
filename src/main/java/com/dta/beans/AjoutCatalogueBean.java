package com.dta.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.dta.entities.Catalogue;
import com.dta.metier.AddCatalogueEJB;

@ManagedBean(name="ajoutCatalogue")
public class AjoutCatalogueBean {
	
	Catalogue catalogue;
	
	private String nom;
	private String description;

	@EJB
	private AddCatalogueEJB ejb;

	public void save(){
		catalogue = new Catalogue(description, nom, null);
		try {
			ejb.save(catalogue);
		}
		catch(Exception ex) {}
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