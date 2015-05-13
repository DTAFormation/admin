package com.dta.metier;

import java.util.ArrayList;
import java.util.Collections;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.dta.entities.Commande;

@Stateless
public class SearchCommandeEJB extends SearchEntities<Commande> {

	public SearchCommandeEJB() {
		super(Commande.class);
	}

	public long getVentesById(int articleId) {

		Query query = em.createNamedQuery("Commande.getVentesById");
		query.setParameter("id", articleId);
	
		return (query.getSingleResult()== null) ? 0 : (long) query.getSingleResult();
	}

	public long getMaxVentes() {

		Query query = em.createNamedQuery("Commande.getVentes");
		ArrayList<Long> result = new ArrayList<Long>();
		if( query.getResultList().size() != 0){
			result = (ArrayList<Long>) query.getResultList();
			Collections.sort(result);
			return result.get(result.size() - 1);
		 }
		else {
			return 0;
		}
	}
	

}
