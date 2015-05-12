package com.dta.metier;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Catalogue;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteCatalogueTest {

	private DeleteCatalogueEJB deleteCatalogueEJB;

	private static final Logger LOG = LoggerFactory
			.getLogger(DeleteCatalogueEJB.class);

	@Mock private EntityManager em;

	@Before
	public void setUp() {
		deleteCatalogueEJB = new DeleteCatalogueEJB();
		deleteCatalogueEJB.setEm(em);
	}

	@Test
	public void testDeleteArticle() {
		LOG.info("Creation d'un catalogue");
		Catalogue c = new Catalogue("description","nom",null);


		when(em.find(Catalogue.class, 0)).thenReturn(c);
		doNothing().when(em).remove(c);
		deleteCatalogueEJB.setEm(em);
		deleteCatalogueEJB.delete(0);
		verify(em).remove(c);
	}
}