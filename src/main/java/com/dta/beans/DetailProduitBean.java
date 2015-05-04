package com.dta.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="detailProduit")
@SessionScoped
public class DetailProduitBean{

	private String idProduit; 

	public String getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(String idProduit) {
		this.idProduit = idProduit;
	}

	private String h = "hello from bean";

	public String getHello() {
		return h;
	}

	public void setHello(String hello) {
		this.h = hello;
	}


	//HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
}