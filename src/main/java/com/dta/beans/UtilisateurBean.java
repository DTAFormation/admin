package com.dta.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dta.entities.Utilisateur;


@ManagedBean(name="utilisateur")
@SessionScoped
public class UtilisateurBean {

	private Utilisateur utilisateur;
	
	public UtilisateurBean() {
		this.utilisateur = new Utilisateur();
	}
	
	public int getUtilisateurId() {
		return utilisateur.getUtilisateurId();
	}

	public void setUtilisateurId(int utilisateurId) {
		utilisateur.setUtilisateurId(utilisateurId);
	}

	public String getEmail() {
		return utilisateur.getEmail();
	}

	public void setEmail(String email) {
		utilisateur.setEmail(email);
	}

	public int getFax() {
		return utilisateur.getFax();
	}

	public void setFax(int fax) {
		utilisateur.setFax(fax);
	}

	public String getLogin() {
		return utilisateur.getLogin();
	}

	public void setLogin(String login) {
		utilisateur.setLogin(login);
	}

	public String getNom() {
		return utilisateur.getNom();
	}

	public void setNom(String nom) {
		utilisateur.setNom(nom);
	}

	public String getPassword() {
		return utilisateur.getPassword();
	}

	public void setPassword(String password) {
		utilisateur.setPassword(password);
	}

	public String getPrenom() {
		return utilisateur.getPrenom();
	}

	public void setPrenom(String prenom) {
		utilisateur.setPrenom(prenom);
	}

	public int getTelephone() {
		return utilisateur.getTelephone();
	}

	public void setTelephone(int telephone) {
		utilisateur.setTelephone(telephone);
	}

	public String getTitre() {
		return utilisateur.getTitre();
	}

	public void setTitre(String titre) {
		utilisateur.setTitre(titre);
	}

	public String getTypeUtil() {
		return utilisateur.getTypeUtil();
	}

	public void setTypeUtil(String typeUtil) {
		utilisateur.setTypeUtil(typeUtil);
	}

	public List<Utilisateur> getUtilisateur() {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		/*Utilisateur u = new Utilisateur("email", 11, "login", "nom", "password", "prenom", 6, "titre", "typeUtil");
		utilisateurs.add(u);*/
		Utilisateur u = new Utilisateur("email", 11, "login", "nom", "password", "prenom", 6, "titre", "typeUtil", null);
		utilisateurs.add(u);

		return utilisateurs;
    }
}
