package com.dta.metier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

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

	public List<Utilisateur> getAll() {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		//Utilisateur u = new Utilisateur("email", 11, "login", "nom", "password", "prenom", 6, "titre", "typeUtil");
		/*Utilisateur u = utilisateurEJB.find(1);
		Utilisateur u1 = utilisateurEJB.find(2);
		Utilisateur u2 = utilisateurEJB.find(3);
		utilisateurs.add(u);
		utilisateurs.add(u1);
		utilisateurs.add(u2);//*/
		
		CriteriaQuery criteria = em.getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(Utilisateur.class));
		return em.createQuery(criteria).getResultList();
		
    }

}
