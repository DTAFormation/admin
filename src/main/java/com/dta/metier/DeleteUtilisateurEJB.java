package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import com.dta.entities.Utilisateur;

@Stateless(name="DeleteUtilisateurEJB")
public class DeleteUtilisateurEJB {

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void delete(int utilisateurId){
		Utilisateur utilisateur = em.find(Utilisateur.class, utilisateurId);
		try{
			em.remove(utilisateur);	
		} catch(OptimisticLockException e){
			e.printStackTrace();
		}

	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
