package com.dta.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.persistence.Column;

import com.dta.entities.Utilisateur;
import com.dta.metier.AddClientEJB;

@ManagedBean(name="addClient")
public class ClientAddBean {
	
	Utilisateur utilisateur;
	
	private String email;
	private int fax;
	private String login;
	private String nom;
	private String password;
	private String prenom;
	private int telephone;
	private String titre;
	private String typeUtil;
	private String address;

	@EJB
	private AddClientEJB ejb;

	@Override
	public String toString() {
		return "ClientAddBean [email=" + email + ", fax=" + fax + ", login="
				+ login + ", nom=" + nom + ", password=" + password
				+ ", prenom=" + prenom + ", telephone=" + telephone
				+ ", titre=" + titre + ", typeUtil=" + typeUtil + "]";
	}


	public void save(){
		System.out.println(toString());
		utilisateur =new Utilisateur();
		utilisateur.setEmail(email);
		utilisateur.setFax(fax);
		utilisateur.setLogin(login);
		utilisateur.setNom(nom);
		utilisateur.setPassword(password);
		utilisateur.setPrenom(prenom);
		utilisateur.setTelephone(telephone);
		utilisateur.setTitre(titre);
		utilisateur.setTypeUtil(typeUtil);
		ejb.save(utilisateur);
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getFax() {
		return fax;
	}
	public void setFax(int fax) {
		this.fax = fax;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getTypeUtil() {
		return typeUtil;
	}
	public void setTypeUtil(String typeUtil) {
		this.typeUtil = typeUtil;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
