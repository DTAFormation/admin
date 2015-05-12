package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.dta.entities.Commande;

@Stateless
public class SearchCommandeEJB extends SearchEntities<Commande> {

	public SearchCommandeEJB() {
		super(Commande.class);
	}

	public long getVentes(int articleId) {

		Query query = em.createNamedQuery("Commande.getVentes");
		query.setParameter("id", articleId);
	
		return (query.getSingleResult()== null) ? 0 : (long) query.getSingleResult();
	}

}
