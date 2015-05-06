package com.dta.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.dta.entities.Adresse;
import com.dta.entities.Utilisateur;

@Stateless
public class AddAdresseEJB {
	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;
	
	public int save(Adresse adresse){
		em.persist(adresse);
		TypedQuery<Integer> query = em.createNamedQuery("Adresse.findByAdresse",Integer.class);
		query.setParameter("codepostal", adresse.getCodePostal());
		query.setParameter("departement", adresse.getDepartement());
		query.setParameter("numero", adresse.getNum());
		query.setParameter("pays", adresse.getPays());
		query.setParameter("rue", adresse.getRue());
		query.setParameter("ville", adresse.getVille());

		List<Integer> adresseList = query.getResultList();
		if(adresseList != null){
			return (adresseList.get(adresseList.size()-1));
		}
		return -1;
	}
}
