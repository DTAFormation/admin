package com.dta.metier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.dta.entities.Article;
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
	
	public String requestGenerator(Utilisateur utilisateur){

		String request = "SELECT u FROM Utilisateur a WHERE ";

		if(!(utilisateur.getLogin()).equals("")){
			request += "u.login ='"+utilisateur.getLogin()+"' ";
		}
		if(!(utilisateur.getNom()).equals("")){
			if(!(utilisateur.getLogin()).equals("")){
				request += "AND u.nom ='"+utilisateur.getNom()+"' ";
			}else{
				request += "u.nom ='"+utilisateur.getNom()+"' ";
			}
		}
		if(!(utilisateur.getPrenom()).equals("")){
			if((!(utilisateur.getLogin()).equals("")) || (!(utilisateur.getNom()).equals(""))){
				request += "AND u.prenom ='"+utilisateur.getPrenom()+"' ";
			}else{
				request += "u.prenom ='"+utilisateur.getPrenom()+"' ";
			}
		}
		if(!(utilisateur.getEmail()).equals("")){
			if((!(utilisateur.getLogin()).equals("")) || (!(utilisateur.getNom()).equals("")) || (!(utilisateur.getPrenom()).equals(""))){
				request += "AND u.email ='"+utilisateur.getEmail()+"' ";
			}else{
				request += "u.email ='"+utilisateur.getEmail()+"' ";
			}
		}
		if(!(utilisateur.getTypeUtil()).equals("")){
			if((!(utilisateur.getLogin()).equals("")) || (!(utilisateur.getNom()).equals("")) || (!(utilisateur.getPrenom()).equals("")) || (!(utilisateur.getEmail()).equals(""))){
				request += "AND u.typeUtil ='"+utilisateur.getTypeUtil()+"' ";
			}else{
				request += "u.typeUtill ='"+utilisateur.getTypeUtil()+"' ";
			}
		}
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
