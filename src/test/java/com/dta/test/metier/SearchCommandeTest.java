package com.dta.test.metier;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.metier.SearchCommandeEJB;


@RunWith(MockitoJUnitRunner.class)
public class SearchCommandeTest {

	private SearchCommandeEJB searchCommande;

	private static final Logger LOG = LoggerFactory.getLogger(SearchCommandeTest.class);	
	
	@Mock private EntityManager em;
	@Mock private Query query;
	
	@Before
	public void setUp() {
		searchCommande = new SearchCommandeEJB();
		searchCommande.setEm(em);
	}
	
	@Test
	public void getVentesByIdTest(){
		        
		LOG.info("Etant donne un id d'article existant en base");
		
		long expectedResult = 19;
		
		when(em.createNamedQuery("Commande.getVentesById")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(expectedResult);
		
	    LOG.info("Lorsque searchCommande.getVentesById(id)");
		long result = searchCommande.getVentesById(0);
		
		Assert.assertEquals("getVentesById devrait retourner un long si l'id est en base",expectedResult, result);
		
	}
	
	@Test
	public void getVentesByIdNullTest(){
		        
		LOG.info("Etant donne un id d'article n'existant pas en base");
		
		
		when(em.createNamedQuery("Commande.getVentesById")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(null);
		
	    LOG.info("Lorsque searchCommande.getVentesById(id)");
		long result = searchCommande.getVentesById(-1);
		
		Assert.assertEquals("getVentesById devrait retourner un long 0 si l'id n'est pas en base",0, result);
		
	}
	
	@Test
	public void getMaxVentesTest(){
		        
		LOG.info("Etant donne des lignecommande existantes base");
		
		ArrayList<Long> arrayResult = new ArrayList<Long>();
		long a = 15;
		long b = 17;
		arrayResult.add(a);
		arrayResult.add(b);
		
		when(em.createNamedQuery("Commande.getVentes")).thenReturn(query);
		when(query.getResultList()).thenReturn(arrayResult);
		
	    LOG.info("Lorsque searchCommande.getMaxVentes)");
		long result = searchCommande.getMaxVentes();
		
		Assert.assertEquals("getMaxVentes devrait retourner un long si des lignecommande existent en base",b, result);
		
	}
	
	@Test
	public void getMaxVentesNullTest(){
		        
		LOG.info("Etant donne des lignecommande inexistantes base");
		ArrayList<Long> arrayResult = new ArrayList<Long>();
		
		when(em.createNamedQuery("Commande.getVentes")).thenReturn(query);
		when(query.getResultList()).thenReturn(arrayResult);
		
	    LOG.info("Lorsque searchCommande.getMaxVentes)");
		long result = searchCommande.getMaxVentes();
		
		Assert.assertEquals("getMaxVentes devrait retourner un long égale à 0 si des lignecommande n'existent pas en base",0, result);
		
	}
}
