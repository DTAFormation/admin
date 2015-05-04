package com.dta.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="ajoutProduit")
@SessionScoped
public class AjoutProduitBean{

	private String nom;
	
	private String description;
	
	private int catalogue;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(int catalogue) {
		this.catalogue = catalogue;
	}
	public void save(){
		System.out.println("save");
	}
	


	//HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
}