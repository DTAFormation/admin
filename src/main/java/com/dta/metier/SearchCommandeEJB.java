package com.dta.metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		ArrayList<Long> result = (ArrayList<Long>) query.getResultList();
		Collections.sort(result);
		return result.get(result.size() - 1);
	}
	
	public void getVentesOrdonnees() {
		Query query = em.createNamedQuery("Commande.getVentesOrdonnees");
		List<Object> a = query.getResultList();
		//System.out.println(a.toString());
		
	}

}
