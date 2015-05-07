package com.dta.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.dta.entities.Adresse;
import com.dta.entities.Utilisateur;
import com.dta.metier.DeleteUtilisateur;
import com.dta.metier.SearchUtilisateur;


@ManagedBean
@RequestScoped
public class UtilisateurBean {
	
	@EJB
	private DeleteUtilisateur deleteUtilisateur;
	
	@EJB
	private SearchUtilisateur searchUtilisateur;
	
<<<<<<< HEAD
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

	public List<Utilisateur> setShowOne(int userID) {
		List<Utilisateur> utilisateurs = new ArrayList<>();

		//Utilisateur u = new Utilisateur("email", 11, "login", "nom", "password", "prenom", 6, "titre", "typeUtil");
		Utilisateur u = utilisateurEJB.find(userID);

		utilisateurs.add(u);
		return utilisateurs;
=======
	public Utilisateur GetUtilisateurById(int utilisateurId) {
		return searchUtilisateur.findById(utilisateurId);
    }
	
	public List<Adresse> showAdresses(int utilisateurId) {
		Utilisateur utilisateur = searchUtilisateur.findById(utilisateurId); 
		return utilisateur.getAdresses();
>>>>>>> a459a9922c795df6ade12f95b2d9d1c47ee2ee45
    }
	
	public List<Utilisateur> getShowAll() {
		return searchUtilisateur.findAll();
    }
	
	public void delete(int utilisateurId) {
		deleteUtilisateur.delete(utilisateurId);
    }
	
}
