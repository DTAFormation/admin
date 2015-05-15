package com.dta.metier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Article;

@Stateless(name="modifyArticleEJB")
public class ModifyArticleEJB {
	private static final Logger LOG = LoggerFactory.getLogger(ModifyArticleEJB.class);

	@PersistenceContext(unitName="ecommercedb")
	private EntityManager em;

	public void update(Article article){
		try{
			em.merge(article);
		} catch(OptimisticLockException e){
			LOG.error("ERROR: ", e);
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}

