package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dta.entities.Utilisateur;

@Stateless
public class AddClientEJB{
	
	
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void save(Utilisateur utilisateur){
		em.persist(utilisateur);
	}
	
	//Methode pour le validator.
	public boolean SearchEmail(String email){
		Query query = em.createQuery("SELECT email FROM Utilisateur WHERE email= :email").setParameter("email", email);
		return (query.getResultList().size()!=0);
	}
	
	public boolean SearchLogin(String login){
		Query query = em.createQuery("SELECT login FROM Utilisateur WHERE login= :login").setParameter("login", login);
		return (query.getResultList().size()!=0);
	}

	
	protected EntityManager getEm() {
		return em;
	}
	protected void setEm(EntityManager em) {
		this.em = em;
	}	
}
