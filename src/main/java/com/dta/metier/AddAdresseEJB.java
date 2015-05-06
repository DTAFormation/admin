package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Adresse;

@Stateless
public class AddAdresseEJB {
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public void save(Adresse adresse){
		em.persist(adresse);
	}
}
