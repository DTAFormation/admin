package com.dta.metier;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Produit;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteProduitTest {

	private DeleteProduitEJB deleteProduitEJB;

	private static final Logger LOG = LoggerFactory
			.getLogger(DeleteProduitEJB.class);

	@Mock private EntityManager em;

	@Before
	public void setUp() {
		deleteProduitEJB = new DeleteProduitEJB();
		deleteProduitEJB.setEm(em);
	}

	@Test
	public void testDeleteProduit() {
		LOG.info("Creation d'un produit");
		Produit p = new Produit("description","nom");


		when(em.find(Produit.class, 0)).thenReturn(p);
		doNothing().when(em).remove(p);
		deleteProduitEJB.setEm(em);
		deleteProduitEJB.delete(0);
		verify(em).remove(p);
	}
}