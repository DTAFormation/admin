package com.dta.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="research")
public class ResearchController {
	
	private String type;
	private String nom;
	private String ref;
	private String msg;
	
	public ResearchController(){
		this("none", "none", "none", "none");
	}
	
	public ResearchController(String type, String nom, String ref, String msg) {
		super();
		this.type = type;
		this.nom = nom;
		this.ref = ref;
		this.msg = msg;
	}


	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getRef() {
		return ref;
	}
	
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	
	@Override
	public String toString() {
		return "ResearchController [type=" + type + ", nom=" + nom + ", ref="
				+ ref + ", msg=" + msg + "]";
	}

	public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Successful", this.toString()) );
    }	
	
}
