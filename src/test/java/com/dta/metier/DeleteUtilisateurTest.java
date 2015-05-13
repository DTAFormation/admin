package com.dta.metier;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Utilisateur;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteUtilisateurTest {

	private DeleteUtilisateurEJB deleteUtilisateurEJB;

	private static final Logger LOG = LoggerFactory
			.getLogger(DeleteUtilisateurEJB.class);

	@Mock private EntityManager em;

	@Before
	public void setUp() {
		deleteUtilisateurEJB = new DeleteUtilisateurEJB();
		deleteUtilisateurEJB.setEm(em);
	}

	@Test
	public void testDeleteUtilisateur() {
		LOG.info("Creation d'un utilisateur");
		Utilisateur utilisateur = new Utilisateur(
				"email@dta.com",
				1,
				"login",
				"nom",
				"password",
				"prenom",
				1,
				"titre",
				"m",
				null);
				
		when(em.find(Utilisateur.class, 0)).thenReturn(utilisateur);
		doNothing().when(em).remove(utilisateur);
		deleteUtilisateurEJB.setEm(em);
		deleteUtilisateurEJB.delete(0);
		verify(em).remove(utilisateur);
	}

}
