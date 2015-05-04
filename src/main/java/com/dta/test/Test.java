package com.dta.test;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.dta.entities.Adresse;
import com.dta.entities.Article;
import com.dta.entities.Catalogue;
import com.dta.entities.Commande;
import com.dta.entities.Produit;
import com.dta.entities.Utilisateur;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommercedb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Adresse adr1 = new Adresse(10000, "10", 54, "france", "rue emile zola", "troyes", null, null);
		Adresse adr2 = new Adresse(54000, "54", 12, "france", "rue de la paix", "nancy", null, null);
		Adresse adr3 = new Adresse(51500, "51", 22, "france", "avenue des etats-unis", "reims", null, null);
		Adresse adr4 = new Adresse(33000, "10", 54, "france", "rue emile zola", "troyes", null, null);
		Adresse adr5 = new Adresse(52000, "54", 12, "france", "rue de la paix", "nancy", null, null);
		Adresse adr6 = new Adresse(11000, "51", 22, "france", "avenue des etats-unis", "reims", null, null);
		Adresse adr7 = new Adresse(63000, "10", 54, "france", "rue emile zola", "troyes", null, null);
		
		Article art1 = new Article("ballon", 15.0f, null, 12);
		Article art2 = new Article("crampons", 8.0f, null, 43);
		Article art3 = new Article("slip de bain", 10.0f, null, 19);
		Article art4 = new Article("bonnet de bain", 15.0f, null, 25);
		
		Catalogue cat = new Catalogue("articles de sport", "sport", null);
		
		Date d = new Date();
		
		Commande com1 = new Commande(d, d, "6531", "bancaire", adr1, null);
		Commande com2 = new Commande(d, d, "9865", "bancaire", adr2, null);
		Commande com3 = new Commande(d, d, "1356", "bancaire", adr3, null);
		Commande com4 = new Commande(d, d, "9647", "bancaire", adr1, null);
		Commande com5 = new Commande(d, d, "7653", "bancaire", adr3, null);

		Produit prod1 = new Produit("articles de foot", "football", cat, Arrays.asList(art1, art2));
		Produit prod2 = new Produit("articles de natation", "natation", cat, Arrays.asList(art3, art4));
		
		Utilisateur util1 = new Utilisateur("thunder@hotmail.com", 1357, "thunder", "abitbol", "theclass", "georges", 3548, "m", "a", Arrays.asList(adr1, adr2));
		Utilisateur util2 = new Utilisateur("epicness@hotmail.com", 7865, "plop", "dupond", "ploplop", "julie", 8541, "mme", "u", Arrays.asList(adr3, adr5));
		Utilisateur util3 = new Utilisateur("salameche92@hotmail.com", 9852, "username", "dupont", "secret", "bob", 5026, "m", "m", Arrays.asList(adr4, adr7));
		Utilisateur util4 = new Utilisateur("vegeta91@hotmail.com", 9025, "pif1991", "collins", "genesis", "phil", 6932, "m", "m", Arrays.asList(adr6));
		
		// Ajout des utilisateurs dans les commandes
		com1.setUtilisateur(util2);
		com2.setUtilisateur(util4);
		com3.setUtilisateur(util3);
		com4.setUtilisateur(util2);
		com5.setUtilisateur(util1);
		
		// Ajout des commandes dans les adresses
		adr1.setCommande(Arrays.asList(com1, com3));
		adr2.setCommande(Arrays.asList(com2, com3, com5));
		adr3.setCommande(Arrays.asList(com1, com2, com4));
		
		// Ajout des utilisateurs dans les adresses
		adr1.setUtilisateur(util1);
		adr2.setUtilisateur(util1);
		adr3.setUtilisateur(util2);
		adr4.setUtilisateur(util3);
		adr5.setUtilisateur(util2);
		adr6.setUtilisateur(util4);
		adr7.setUtilisateur(util3);
		
		// Ajout des produits dans les articles
		art1.setProduit(prod1);
		art2.setProduit(prod1);
		art3.setProduit(prod2);
		art4.setProduit(prod2);
		
		// Ajout des produits dans le catalogue
		cat.setProduits(Arrays.asList(prod1, prod2));		
		
		em.persist(adr1);
		em.persist(adr2);
		em.persist(adr3);
		em.persist(adr4);
		em.persist(adr5);
		em.persist(adr6);
		em.persist(adr7);
		em.persist(art1);
		em.persist(art2);
		em.persist(art3);
		em.persist(art4);
		em.persist(cat);
		em.persist(com1);
		em.persist(com2);
		em.persist(com3);
		em.persist(com4);
		em.persist(com5);
		em.persist(prod1);
		em.persist(prod2);
		em.persist(util1);
		em.persist(util2);
		em.persist(util3);
		em.persist(util4);		
		
		tx.commit();
		em.close();
		emf.close();
	}
}
