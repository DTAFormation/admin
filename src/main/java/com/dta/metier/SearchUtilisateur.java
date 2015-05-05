package com.dta.metier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.dta.entities.Article;
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
	
	public String requestGenerator(Utilisateur utilisateur){
		System.out.println("\n \n \n \n \n " + utilisateur);
		String request = "SELECT u FROM Utilisateur u WHERE ";

		if(utilisateur.getLogin() != null){
			request += "u.login ='"+utilisateur.getLogin()+"' ";
		}
//		if(utilisateur.getNom() != null){
//			if(utilisateur.getLogin() != null){
//				request += "AND u.nom ='"+utilisateur.getNom()+"' ";
//			}else{
//				request += "u.nom ='"+utilisateur.getNom()+"' ";
//			}
//		}
//		if(utilisateur.getPrenom() != null){
//			if((utilisateur.getLogin() != null) || (utilisateur.getNom() != null)){
//				request += "AND u.prenom ='"+utilisateur.getPrenom()+"' ";
//			}else{
//				request += "u.prenom ='"+utilisateur.getPrenom()+"' ";
//			}
//		}
//		if(utilisateur.getEmail() != null){
//			if((utilisateur.getLogin() != null) || (utilisateur.getNom() != null) || (utilisateur.getPrenom() != null)){
//				request += "AND u.email ='"+utilisateur.getEmail()+"' ";
//			}else{
//				request += "u.email ='"+utilisateur.getEmail()+"' ";
//			}
//		}
//		if(utilisateur.getTypeUtil() != null){
//			if((utilisateur.getLogin() != null) || (utilisateur.getNom() != null) || (utilisateur.getPrenom() != null) || (utilisateur.getEmail() != null)){
//				request += "AND u.typeUtil ='"+utilisateur.getTypeUtil()+"' ";
//			}else{
//				request += "u.typeUtill ='"+utilisateur.getTypeUtil()+"' ";
//			}
//		}
		System.out.println("\n \n \n \n \n " + request);
		System.out.println("\n \n \n \n \n " + request);
		System.out.println("\n \n \n \n \n " + request);
		return request;
	}
		
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findDetail (Utilisateur user){
		String requete = requestGenerator(user);
		Query query = em.createQuery(requete);
		if (query.getResultList().size() == 0)
			return new ArrayList<Utilisateur>();
		return query.getResultList();
	}

}
