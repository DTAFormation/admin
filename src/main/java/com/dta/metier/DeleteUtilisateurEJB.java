package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Utilisateur;

@Stateless(name="DeleteUtilisateurEJB")
public class DeleteUtilisateurEJB {
	private static final Logger LOG = LoggerFactory.getLogger(DeleteUtilisateurEJB.class);

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void delete(int utilisateurId){
		Utilisateur utilisateur = em.find(Utilisateur.class, utilisateurId);
		try{
			em.remove(utilisateur);	
		} catch(OptimisticLockException e){
			LOG.error("ERROR: ", e);
		}

	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
