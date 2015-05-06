package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Utilisateur;

@Stateless
public class DeleteUtilisateur {
	
	@PersistenceContext(unitName="ecommercedb")
	protected EntityManager em;
	
	public void delete(int utilisateurId){
		Utilisateur utilisateur = em.find(Utilisateur.class, utilisateurId);
		em.remove(utilisateur);		
	}
		
}
