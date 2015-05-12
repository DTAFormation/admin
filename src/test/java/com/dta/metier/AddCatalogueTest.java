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

import com.dta.entities.Catalogue;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AddCatalogueTest {

	private static final Logger LOG = LoggerFactory.getLogger(AddCatalogueTest.class);
	
	@Mock 
	private EntityManager em;
	
	@Mock
	private TypedQuery<Catalogue> query;
	
	private AddCatalogueEJB ejb;
	
	@Before
	public void setUp() {
		ejb = new AddCatalogueEJB();
		ejb.setEm(em);		
	}
	
	@Test
	public void creerCatalogue() {		
		LOG.info("Etant donne un objet Catalogue");		
		Catalogue catalogue = new Catalogue("description","nom",null);
		
		when(em.createNamedQuery("Catalogue.findByName", Catalogue.class)).thenReturn(query);
		when(query.setParameter("name", "nom")).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<Catalogue>());
		
		LOG.info("Lorsque ejb.save(catalogue)");
		ejb.save(catalogue);
		
		LOG.info("Alors 'catalogue' a été persisté");
		verify(em).persist(catalogue);
		
		LOG.info("FIN");
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void creerCatalogueAvecNomExistant() {
		LOG.info("Etant donne des objets Catalogue avec le meme nom");
		Catalogue catalogue1 = new Catalogue("description1","nom",null);
		Catalogue catalogue2 = new Catalogue("description2","nom",null);
		
		when(em.createNamedQuery("Catalogue.findByName", Catalogue.class)).thenReturn(query);
		when(query.setParameter("name", "nom")).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<Catalogue>(), Arrays.asList(catalogue1));
		
		boolean firstAttempt = false;
		boolean secondAttempt = true;
		
		assertEquals(firstAttempt, ejb.isCatalogueNameExists(catalogue1.getNom()));
		assertEquals(secondAttempt, ejb.isCatalogueNameExists(catalogue2.getNom()));
	
		LOG.info("FIN");
		
		
	}
	
}