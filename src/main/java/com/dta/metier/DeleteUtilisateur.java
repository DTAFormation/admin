package com.dta.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dta.entities.Adresse;
import com.dta.entities.Utilisateur;

@Stateless
public class DeleteUtilisateur {
	
	@PersistenceContext(unitName="ecommercedb")
	protected EntityManager em;
	
	public void delete(Utilisateur utilisateur){
		List<Adresse> adresses = utilisateur.getAdresses();
		for (Adresse adresse : adresses) {
			em.remove(adresse);
		}
		em.remove(utilisateur);
	}

}
