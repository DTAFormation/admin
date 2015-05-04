package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Utilisateur;

@Stateless(name="AddClientEJB")
public class AddClientEJB {
	
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void save(Utilisateur utilisateur){
		em.persist(utilisateur);
	}
}
