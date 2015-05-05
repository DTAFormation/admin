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
		CriteriaQuery criteria = em.getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(Utilisateur.class));
		return em.createQuery(criteria).getResultList();		
    }

}
