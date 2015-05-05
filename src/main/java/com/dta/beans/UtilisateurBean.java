package com.dta.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.dta.entities.Adresse;
import com.dta.entities.Utilisateur;
import com.dta.metier.SearchUtilisateur;


@ManagedBean
@RequestScoped
public class UtilisateurBean {
	
	@EJB
	private SearchUtilisateur searchUtilisateur;
	
	public Utilisateur showOne(int userID) {
		Utilisateur utilisateur = searchUtilisateur.findById(userID);
		return utilisateur;
    }
	
	public List<Adresse> showAdresses(int userID) {
		List<Adresse> adresses = new ArrayList<>();
		Utilisateur utilisateur = searchUtilisateur.findById(userID);
		adresses = utilisateur.getAdresses();
		return adresses;
    }
	
	public List<Utilisateur> getShowAll() {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		utilisateurs = searchUtilisateur.findAll();
		return utilisateurs;
    }
	
	public void delete() {
		// An admin can delete the user 
    }
}
