package com.dta.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.dta.entities.Utilisateur;

@Stateless
public class SearchUtilisateur extends SearchEntities<Utilisateur>{

	
	public SearchUtilisateur() {
		super(Utilisateur.class);
	}
	
	public List<Utilisateur> findByName(String name){
		TypedQuery<Utilisateur> query = em.createQuery("Utilisateur.findByName", Utilisateur.class);
		query.setParameter("name", name);
		return query.getResultList();		
	}
	
	public List<Utilisateur> findByTitre(String type){
		TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.findByTitre", Utilisateur.class);
		query.setParameter("typeUt", type);
		return query.getResultList();
	}


}
