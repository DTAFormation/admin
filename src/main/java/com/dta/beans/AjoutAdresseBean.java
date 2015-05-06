package com.dta.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.dta.entities.Adresse;
import com.dta.entities.Utilisateur;
import com.dta.metier.AddAdresseEJB;

@ManagedBean
public class AjoutAdresseBean {
	private int codePostal;
	private String departement;
	private int num;
	private String pays;
	private String rue;
	private String ville;
	
	private Adresse adresse;
	
	@EJB
	private AddAdresseEJB ejb;
	
	public void save(){
		System.out.println(toString());
		adresse =new Adresse();

		//ejb.save(adresse);
	}
	
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
}
