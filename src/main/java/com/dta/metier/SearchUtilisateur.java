package com.dta.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.dta.entities.Utilisateur;

@Stateless
public class SearchUtilisateur extends SearchEntities<Utilisateur>{

	
	public SearchUtilisateur() {
		super(Utilisateur.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByName(String name){
		Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.nom = :name");
		query.setParameter("name", name);
		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByTitre(String type){
		Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.typeUtil = :typeUt");
		query.setParameter("typeUt", type);
		return query.getResultList();
	}


}
