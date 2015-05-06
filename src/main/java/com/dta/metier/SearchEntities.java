package com.dta.metier;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public abstract class SearchEntities<T> {
	
	@PersistenceContext(unitName="ecommercedb")
	protected EntityManager em;
	
	private Class<T> entityClass;

	
	public SearchEntities(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public List<T> findAll(){
		CriteriaQuery criteria = em.getCriteriaBuilder().createQuery();
		criteria.select(criteria.from(entityClass));
		Query query = em.createQuery(criteria);
		return query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
