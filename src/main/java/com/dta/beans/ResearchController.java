package com.dta.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.dta.entities.Article;

@ManagedBean(name="research")
public class ResearchController {
	
	private String type;
	private String nom;
	private String ref;
	private String msg;
	
	private List<Article> products;
	
	public ResearchController(){
		this("none", "none", "none", "none");
	}
	
	public ResearchController(String type, String nom, String ref, String msg) {
		super();
		this.type = type;
		this.nom = nom;
		this.ref = ref;
		this.msg = msg;
		
		products = new ArrayList<Article>();
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
	
	public List<Article> getProducts(){
		return this.products;
	}
	
	
	@Override
	public String toString() {
		return "ResearchController [type=" + type + ", nom=" + nom + ", ref="
				+ ref + ", msg=" + msg + "]";
	}

	public void submitResearch() {
		
		//requetes recherche
		
		System.out.println("\n\n --> recherche: ");
		System.out.println(this.toString());
		
		for(int i = 0; i<100; i++){
			Article art = new Article();
			art.setNom("article n°"+i);
			art.setPrix(500);
			art.setProduit(null);
			art.setStock(100);
			products.add(art);
		}
    }
	
	public int getResultSize(){
		return products.size();
	}
	
}
