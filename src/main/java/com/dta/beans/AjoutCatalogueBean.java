package com.dta.beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

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
		ejb.save(catalogue);
		notifyAjout();
		cleanAndUpdate();
	}
	
	private void notifyAjout() {
		FacesMessage msg = new FacesMessage("Catalogue enregistré");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		updateElement("ajoutCatalogueForm:msgs");
	}

	private void cleanAndUpdate() {
		nom = null;
		description = null;
		updateElement("ajoutCatalogueForm");
	}
	
	private void updateElement(String elementId) {
		RequestContext.getCurrentInstance().update(elementId);
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