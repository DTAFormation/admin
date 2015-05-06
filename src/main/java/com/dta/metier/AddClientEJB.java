package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.dta.entities.Utilisateur;

@Stateless
public class AddClientEJB{
	
	
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void save(Utilisateur utilisateur){
		em.persist(utilisateur);
	}
	
	//Methode pour le validator.
	public boolean SearchExistenceEmail(String email){
		TypedQuery<String> query = em.createNamedQuery("Utilisateur.findByEmail",String.class).setParameter("email", email);
		return (query.getResultList().size()!=0);
	}
	public boolean SearchExistenceLogin(String login){
		TypedQuery<String> query = em.createNamedQuery("Utilisateur.findByLogin",String.class).setParameter("login", login);
		return (query.getResultList().size()!=0);
	}

	
	protected EntityManager getEm() {
		return em;
	}
	protected void setEm(EntityManager em) {
		this.em = em;
	}	
}
