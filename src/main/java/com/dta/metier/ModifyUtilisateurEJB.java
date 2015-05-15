package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import com.dta.entities.Utilisateur;

@Stateless(name = "ModifyUtilisateurEJB")
public class ModifyUtilisateurEJB {

	@PersistenceContext(unitName = "ecommercedb")
	private EntityManager em;

	public void update(Utilisateur utilisateur) {
		try {
			em.merge(utilisateur);
		} catch (OptimisticLockException e) {
			e.printStackTrace();
		}
	}

	protected EntityManager getEm() {
		return em;
	}

	protected void setEm(EntityManager em) {
		this.em = em;
	}
}
