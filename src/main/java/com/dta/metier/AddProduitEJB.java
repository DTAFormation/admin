package com.dta.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Catalogue;
import com.dta.entities.Produit;

@Stateless(name="AddProduitEJB")
public class AddProduitEJB {
	private static final Logger LOG = LoggerFactory.getLogger(AddProduitEJB.class);

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void save(Produit produit){
		if(!isProduitNameExists(produit.getNom())){
			try{
				em.persist(produit);	
			} catch(OptimisticLockException e){
				LOG.error("ERROR: ", e);
			}
		}	
	}

	public boolean isProduitNameExists(String name) {
		return !em.createNamedQuery("Produit.findByName", Produit.class)
				.setParameter("name", name)
				.getResultList()
				.isEmpty();
	}

	public Catalogue getCatalogueById(int id) {
		return em.createNamedQuery("Catalogue.findById", Catalogue.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	public List<Catalogue> getAllCatalogues() {		
		return em.createNamedQuery("Catalogue.findAll", Catalogue.class)
				.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}