package com.dta.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Utilisateur {

	@Id
	@GeneratedValue
	@Column(name="utilisateur_id", length=19)
	private int utilisateurId;
	
	@Column(name="email", length=255)
	private String email;
	
	@Column(name="fax", length=10)
	private int fax;
	
	@Column(name="login", length=255)
	private String login;
	
	@Column(name="nom", length=255)
	private String nom;
	
	@Column(name="password", length=255)	
	private String password;
	
	@Column(name="prenom", length=255)
	private String prenom;
	
	@Column(name="telephone", length=10)
	private int telephone;
	
	@Column(name="titre", length=255)
	private String titre;
	
	@Column(name="type_util", length=1)
	private String typeUtil;
	
	/*
	@OneToMany(mappedBy="utilisateur")
	private List<Adresse> adresses;*/
	
	public Utilisateur() {}

	public Utilisateur(String email, int fax, String login,
			String nom, String password, String prenom, int telephone,
			String titre, String typeUtil/*, List<Adresse> adresses*/) {
		this.email = email;
		this.fax = fax;
		this.login = login;
		this.nom = nom;
		this.password = password;
		this.prenom = prenom;
		this.telephone = telephone;
		this.titre = titre;
		this.typeUtil = typeUtil;
		//this.adresses = adresses;
	}

	public int getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(int utilisateurId) {
		this.utilisateurId = utilisateurId;
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

	/*public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresse(List<Adresse> adresses) {
		this.adresses = adresses;
	}*/

	@Override
	public String toString() {
		return "Utilisateur [utilisateurId=" + utilisateurId + ", email="
				+ email + ", fax=" + fax + ", login=" + login + ", nom=" + nom
				+ ", password=" + password + ", prenom=" + prenom
				+ ", telephone=" + telephone + ", titre=" + titre
				+ ", typeUtil=" + typeUtil + "]";
	}
}