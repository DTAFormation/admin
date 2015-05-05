package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Utilisateur;


@Stateless
public class GetUtilisateurEJG {

	@PersistenceContext(unitName = "ecommercedb")
	private EntityManager em;
	private Utilisateur utilisateur;

	public GetUtilisateurEJG() {
		this.utilisateur = new Utilisateur();
	}

	public Utilisateur find(int utilisateurID) {
		return em.find(utilisateur.getClass(), utilisateurID);
	}

}
