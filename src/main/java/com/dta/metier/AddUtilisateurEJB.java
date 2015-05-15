package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Utilisateur;

@Stateless
public class AddUtilisateurEJB {
	private static final Logger LOG = LoggerFactory.getLogger(AddUtilisateurEJB.class);

	@PersistenceContext(unitName = "ecommercedb")
	private EntityManager em;

	public void save(Utilisateur utilisateur) {

		try{
			em.persist(utilisateur);
		} catch(OptimisticLockException e){
			LOG.error("ERROR: ", e);
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
