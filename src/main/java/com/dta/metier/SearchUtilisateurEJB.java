package com.dta.metier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Utilisateur;

@Stateless(name="SearchUtilisateurEJB")
public class SearchUtilisateurEJB extends SearchEntities<Utilisateur>{

	private static final Logger LOG = LoggerFactory.getLogger(SearchUtilisateurEJB.class);	
	
	public SearchUtilisateurEJB() {
		super(Utilisateur.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByName(String name){
		Query query = em.createNamedQuery("Utilisateur.findByName");
		query.setParameter("name", name);
		return query.getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findByTitre(String type){
		Query query = em.createNamedQuery("Utilisateur.findByTitre");
		query.setParameter("typeUt", type);
		return query.getResultList();
	}
	
	public Utilisateur findById(int id){
		return em.find(Utilisateur.class, id);
	}
	
	public String requestGenerator(Utilisateur utilisateur){
		String request = "SELECT u FROM Utilisateur u ";
		
		if(utilisateur.getLogin() != null)
			request += "WHERE u.login ='"+utilisateur.getLogin()+"' ";
		if(utilisateur.getNom() != null){
			if(utilisateur.getLogin() != null)
				request += "AND u.nom ='" + utilisateur.getNom() + "' ";
			else
				request += "WHERE u.nom ='" + utilisateur.getNom() + "' ";
		}
		if (utilisateur.getPrenom() != null){
			if((utilisateur.getLogin() != null) || (utilisateur.getNom() != null))
				request += "AND u.prenom ='" + utilisateur.getPrenom() + "' ";
			else
				request += "WHERE u.prenom ='" + utilisateur.getPrenom() + "' ";
		}
		if (utilisateur.getEmail() != null){
			if((utilisateur.getLogin() != null) || (utilisateur.getNom() != null) || (utilisateur.getPrenom() != null))
				request += "AND u.email ='" + utilisateur.getEmail() + "' ";
			else
				request += "WHERE u.email ='" + utilisateur.getEmail() + "' ";
		}
		if (utilisateur.getTypeUtil() != null){
			if((utilisateur.getLogin() != null) || (utilisateur.getNom() != null) || (utilisateur.getPrenom() != null) || (utilisateur.getEmail() != null))
				request += "AND u.typeUtil ='" + utilisateur.getTypeUtil() + "' ";
			else
				request += "WHERE u.typeUtil ='" + utilisateur.getTypeUtil() + "' ";
		}
		
		return request;
	}
		
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findDetail (Utilisateur user){
		String requete = requestGenerator(user);
		Query query = em.createQuery(requete);
		if (query.getResultList().isEmpty())
			return new ArrayList<Utilisateur>();
		return query.getResultList();
	}
	
	//méthode pour authentification utilisateur
	public Utilisateur findAuthentification (String login, String password, String type){
		
		Query query_auth = em.createQuery("SELECT u FROM Utilisateur u WHERE u.login = :login AND u.password = :password AND u.typeUtil = :type");
		query_auth.setParameter("login", login);
		query_auth.setParameter("password", password);
		query_auth.setParameter("type", type);

		try{
			Utilisateur result = (Utilisateur) query_auth.getSingleResult();
			return result;
		}catch (NoResultException e){
			LOG.info("error NoResultException", e);
			return null;
		}

	}

}
