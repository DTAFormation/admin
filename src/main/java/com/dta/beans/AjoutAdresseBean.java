package com.dta.beans;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;

import com.dta.entities.Adresse;

@ManagedBean
public class AjoutAdresseBean {
	private int codePostal;
	private String departement;
	private int num;
	private String pays;
	private String rue;
	private String ville;
	private Adresse adresse;
	
	private int idAdresse;
	
	public void save(){
		System.out.println(toString());
		adresse =new Adresse();
		adresse.setCodePostal(codePostal);
		adresse.setDepartement(departement);
		adresse.setNum(num);
		adresse.setPays(pays);
		adresse.setRue(rue);
		adresse.setVille(ville);

		AjoutClientBean.saveAdresses(adresse);
		RequestContext.getCurrentInstance().execute("PF('dlgadress').hide()");
		RequestContext.getCurrentInstance().execute("PF('dlgAdresseEnregistre').show()");
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
	public int getIdAdresse() {
		return idAdresse;
	}
	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}
}
