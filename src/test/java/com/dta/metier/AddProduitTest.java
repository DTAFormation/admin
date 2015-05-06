package com.dta.metier;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.dta.entities.Produit;

@RunWith(MockitoJUnitRunner.class)
public class AddProduitTest {

	private static final Logger LOG = LoggerFactory.getLogger(AddProduitTest.class);
	
	@Mock 
	private EntityManager em;
	
	@Mock
	private Query query;
	
	private AddProduitEJB ejb;
	
	@Before
	public void setUp() {
		ejb = new AddProduitEJB();
		ejb.setEm(em);		
	}
	
	@Test
	public void creerProduit() {
		LOG.info("Etant donné un objet Produit");
		
		Produit produit = new Produit("ceci est une description", "nom");
		
		when(em.createNamedQuery("Produit.findByName")).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<Produit>());
		
		LOG.info("Lorsque ejb.save(produit)");
		
		ejb.save(produit);
		
		LOG.info("Alors 'produit' est présent en BDD");
		verify(em).persist(produit);
	}
	
	@Test
	public void creerProduitAvecNomExistant() {
		LOG.info("Etant donné des objets Produit avec le même nom");
		
		Produit produit1 = new Produit("ceci est une description", "nom");
		Produit produit2 = new Produit("ceci est une autre description", "nom");
		
		when(em.createNamedQuery("Produit.findByName")).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<Produit>());
		
		LOG.info("Lorsque ejb.save(produit)");
		
		ejb.save(produit1);
		ejb.save(produit2);
		
		LOG.info("Alors 'produit' est présent en BDD");
		verify(em).persist(produit1);
	}
}