package com.dta.metier;

import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
	private TypedQuery<Produit> query;
	
	private AddProduitEJB ejb;
	
	@Before
	public void setUp() {
		ejb = new AddProduitEJB();
		ejb.setEm(em);		
	}
	
	@Test
	public void creerProduit() {		
		LOG.info("Etant donne un objet Produit");		
		Produit produit = new Produit("ceci est une description", "nom");
		
		when(em.createNamedQuery("Produit.findByName", Produit.class)).thenReturn(query);
		when(query.setParameter("name", "nom")).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<Produit>());
		
		LOG.info("Lorsque ejb.save(produit)");
		ejb.save(produit);
		
		LOG.info("Alors 'produit' a ete persiste");
		verify(em).persist(produit);
		
		LOG.info("FIN");
	}
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void creerProduitAvecNomExistant() {
		LOG.info("Etant donne des objets Produit avec le meme nom");		
		Produit produit1 = new Produit("ceci est une description", "nom");
		Produit produit2 = new Produit("ceci est une autre description", "nom");
		
		when(em.createNamedQuery("Produit.findByName", Produit.class)).thenReturn(query);
		when(query.setParameter("name", "nom")).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<Produit>(), Arrays.asList(produit1));
		
		boolean firstAttempt = false;
		boolean secondAttempt = true;

		assertEquals(firstAttempt, ejb.isProduitNameExists(produit1.getNom()));
		assertEquals(secondAttempt, ejb.isProduitNameExists(produit2.getNom()));
	
		LOG.info("FIN");
	}
}