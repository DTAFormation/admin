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
	
	private String request;
	
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
		request = "SELECT u FROM Utilisateur u ";
		
		addWhereLogin(utilisateur);
		addWhereNom(utilisateur);
		addWherePrenom(utilisateur);
		addWhereEmail(utilisateur);
		addWhereTypeUtil(utilisateur);
		
		return request;
	}
		
	private void addWhereLogin(Utilisateur utilisateur) {
	    if(utilisateur.getLogin() != null) {
            request += "WHERE u.login ='"+utilisateur.getLogin()+"' ";
	    }
	}
	
	private void addWhereNom(Utilisateur utilisateur) {
	    if(utilisateur.getNom() != null){
            if(utilisateur.getLogin() != null)
                request += "AND u.nom ='" + utilisateur.getNom() + "' ";
            else
                request += "WHERE u.nom ='" + utilisateur.getNom() + "' ";
        }
    }
	
	private void addWherePrenom(Utilisateur utilisateur) {
	    if (utilisateur.getPrenom() != null){
            if((utilisateur.getLogin() != null) || (utilisateur.getNom() != null))
                request += "AND u.prenom ='" + utilisateur.getPrenom() + "' ";
            else
                request += "WHERE u.prenom ='" + utilisateur.getPrenom() + "' ";
        }
    }
	
	private void addWhereEmail(Utilisateur utilisateur) {
	    if (utilisateur.getEmail() != null){
            if((utilisateur.getLogin() != null) || (utilisateur.getNom() != null) || (utilisateur.getPrenom() != null))
                request += "AND u.email ='" + utilisateur.getEmail() + "' ";
            else
                request += "WHERE u.email ='" + utilisateur.getEmail() + "' ";
        }
    }
	
	private void addWhereTypeUtil(Utilisateur utilisateur) {
        if (utilisateur.getTypeUtil() != null){
            if((utilisateur.getLogin() != null) || (utilisateur.getNom() != null) || (utilisateur.getPrenom() != null) || (utilisateur.getEmail() != null))
                request += "AND u.typeUtil ='" + utilisateur.getTypeUtil() + "' ";
            else
                request += "WHERE u.typeUtil ='" + utilisateur.getTypeUtil() + "' ";
        }
    }
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findDetail (Utilisateur user){
		String requete = requestGenerator(user);
		Query query = em.createQuery(requete);
		if (query.getResultList().isEmpty())
			return new ArrayList<Utilisateur>();
		return query.getResultList();
	}
	
	//methode pour authentification utilisateur
	public Object findAuthentification (String login, String password) {
		
		Query queryAuth = em.createQuery("SELECT u FROM Utilisateur u WHERE u.login = :login AND u.password = :password AND (u.typeUtil ='a' OR u.typeUtil ='m')");
		queryAuth.setParameter("login", login);
		queryAuth.setParameter("password", password);
		Object result;
		try {
			result = queryAuth.getSingleResult();
		} catch (NoResultException e) {
			LOG.info("CONNEXION ERREUR : L UTILISATEUR N A PAS ETE TROUVE",e);
			result = null;
		}
		return result;
	}

}
