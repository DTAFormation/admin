package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Utilisateur;

@Stateless(name="ModifyUtilisateurEJB")
public class ModifyUtilisateurEJB{
	
	
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void update(Utilisateur utilisateur){
		if(isUtilisateurLoginExists(utilisateur.getLogin()))
			em.persist(utilisateur);
	}
	
	public boolean isUtilisateurLoginExists(String login) {
		return !em.createNamedQuery("Utilisateur.findByLogin", Utilisateur.class)
				.setParameter("login", login)
				.getResultList()
				.isEmpty();
	}
	
	
	protected EntityManager getEm() {
		return em;
	}
	protected void setEm(EntityManager em) {
		this.em = em;
	}	
}
