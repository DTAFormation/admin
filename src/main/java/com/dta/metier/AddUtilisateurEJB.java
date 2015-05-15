package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.dta.entities.Utilisateur;

@Stateless
public class AddUtilisateurEJB {

	@PersistenceContext(unitName = "ecommercedb")
	private EntityManager em;

	public void save(Utilisateur utilisateur) {

		try{
			em.persist(utilisateur);
		} catch(OptimisticLockException e){
			e.printStackTrace();
		}
	}

	// Methode pour le validator.
	public boolean searchExistenceEmail(String email) {
		TypedQuery<String> query = em.createNamedQuery(
				"Utilisateur.findByEmail", String.class).setParameter("email",
						email);
		return !query.getResultList().isEmpty();
	}

	public boolean searchExistenceLogin(String login) {
		TypedQuery<String> query = em.createNamedQuery(
				"Utilisateur.findByLogin", String.class).setParameter("login",
						login);
		return !query.getResultList().isEmpty();
	}

	protected EntityManager getEm() {
		return em;
	}

	protected void setEm(EntityManager em) {
		this.em = em;
	}
}
