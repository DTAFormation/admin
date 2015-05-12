package com.dta.metier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Adresse;
import com.dta.entities.Utilisateur;
import com.dta.metier.AddUtilisateurEJB;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddUtilisateurTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(AddUtilisateurTest.class);
	
	@Mock private EntityManager em;
	@Mock private TypedQuery<String> query;
	
	private AddUtilisateurEJB ejb;
	
	@Before
	public void init(){
		ejb = new AddUtilisateurEJB();
		ejb.setEm(em);
	}
	
	@Test
	public void testAjoutClientSansAdresse(){
		Utilisateur utilisateur= new Utilisateur("testAjout@client.com",
				000, "logintestAjout", 
				"nomTest", "passwordTest", 
				"prenomTest", 123, "Monsieur", "a",null);
		Mockito.doNothing().when(em).persist(Mockito.anyObject());
		ejb.save(utilisateur);
		Mockito.verify(em).persist(Mockito.anyObject());
	}
	
	@Test
	public void testAjoutUtilisateurAvecAdresse(){
		Adresse adresse = new Adresse(11310, "aude", 4, "france", "rue du midi", "villemagne", null, null);
		List<Adresse> adresses = new ArrayList<Adresse>();
		adresses.add(adresse);
		Utilisateur utilisateur= new Utilisateur("testAjout@client.com",
				000, "logintestAjout", 
				"nomTest", "passwordTest", 
				"prenomTest", 123, "Monsieur", "a",adresses);
		Mockito.doNothing().when(em).persist(Mockito.anyObject());
		ejb.save(utilisateur);
		//Mockito.verify(em).persist(adresse);
		Mockito.verify(em).persist(utilisateur);
	}
	
	@Test
	public void SearchExistenceEmailTest(){
		 LOG.info("Etant donn� une adresse email existant en base");
		 String email="test@email.fr";
		 List<String> usersEnBase = new ArrayList<>();
		 usersEnBase.add(email);
		 when(em.createNamedQuery("Utilisateur.findByEmail",String.class)).thenReturn(query);
		 when(query.setParameter("email",email)).thenReturn(query);
		 when(query.getResultList()).thenReturn(usersEnBase);
		 LOG.info("Lorsque ejb.SearchExistenceEmail(email)");
		 
		 boolean result = ejb.SearchExistenceEmail(email);
		 
		 LOG.info("Alors le service renvoie vrai");
		 assertEquals("userExists devrait retourner vrai si l'email est en base", true, result);
	}
	
	@Test
	public void SearchExistenceLoginTest(){
		 LOG.info("Etant donn� une adresse email existant en base");
		 String Login="test@email.fr";
		 List<String> usersEnBase = new ArrayList<>();
		 usersEnBase.add(Login);
		 
		 when(em.createNamedQuery("Utilisateur.findByLogin",String.class)).thenReturn(query);
		 when(query.setParameter("login",Login)).thenReturn(query);
		 when(query.getResultList()).thenReturn(usersEnBase);
		 
		 LOG.info("Lorsque ejb.SearchExistenceEmail(email)");
		 
		 boolean result = ejb.SearchExistenceLogin(Login);
		 
		 LOG.info("Alors le service renvoie vrai");
		 assertEquals("userExists devrait retourner vrai si l'email est en base", true, result);
	}
}
