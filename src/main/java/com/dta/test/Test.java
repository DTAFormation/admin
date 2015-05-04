package com.dta.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.dta.entities.Utilisateur;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommercedb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	
		Utilisateur util = new Utilisateur("a@a.com", 1321, "zierh", "erkze", "pass", "amezr", 654, "mer", "a");
		em.persist(util);
		tx.commit();
		em.close();
		emf.close();
	}
}
