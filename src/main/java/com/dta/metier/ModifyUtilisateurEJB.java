package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Utilisateur;

@Stateless(name = "ModifyUtilisateurEJB")
public class ModifyUtilisateurEJB {
	private static final Logger LOG = LoggerFactory.getLogger(ModifyUtilisateurEJB.class);
	
	@PersistenceContext(unitName = "ecommercedb")
	private EntityManager em;

	public void update(Utilisateur utilisateur) {
		try {
			em.merge(utilisateur);
		} catch (OptimisticLockException e) {
			LOG.error("ERROR: ", e);
		}
	}

	protected EntityManager getEm() {
		return em;
	}

	protected void setEm(EntityManager em) {
		this.em = em;
	}
}
