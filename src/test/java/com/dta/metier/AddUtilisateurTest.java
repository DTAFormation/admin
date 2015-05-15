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

		Utilisateur utilisateur= new Utilisateur();
		utilisateur.setEmail("testAjout@client.com");
		utilisateur.setFax(000);
		utilisateur.setLogin("logintestAjout");
		utilisateur.setNom("nomTest");
		utilisateur.setPassword("passwordTest");
		utilisateur.setPrenom("prenomTest");
		utilisateur.setTelephone(123);
		utilisateur.setTitre("Monsieur");
		utilisateur.setTypeUtil("a");
		utilisateur.setAdresses(null);
		
		Mockito.doNothing().when(em).persist(Mockito.anyObject());
		ejb.save(utilisateur);
		Mockito.verify(em).persist(Mockito.anyObject());
	}
	
	@Test
	public void testAjoutUtilisateurAvecAdresse(){
		Adresse adresse = new Adresse();
		adresse.setCodePostal(11310);
		adresse.setDepartement("aude");
		adresse.setNum(4);
		adresse.setPays("France");
		adresse.setRue("rue du midi");
		adresse.setVille("villemagne");
		adresse.setCommande(null);
		adresse.setUtilisateur(null);
		
		List<Adresse> adresses = new ArrayList<Adresse>();
		adresses.add(adresse);
		Utilisateur utilisateur= new Utilisateur();
		utilisateur.setEmail("testAjout@client.com");
		utilisateur.setFax(000);
		utilisateur.setLogin("logintestAjout");
		utilisateur.setNom("nomTest");
		utilisateur.setPassword("passwordTest");
		utilisateur.setPrenom("prenomTest");
		utilisateur.setTelephone(123);
		utilisateur.setTitre("Monsieur");
		utilisateur.setTypeUtil("a");
		utilisateur.setAdresses(adresses);
		
		Mockito.doNothing().when(em).persist(Mockito.anyObject());
		ejb.save(utilisateur);
		//Mockito.verify(em).persist(adresse);
		Mockito.verify(em).persist(utilisateur);
	}
	
	@Test
	public void SearchExistenceEmailTest(){
		 LOG.info("Etant donne une adresse email existant en base");
		 String email="test@email.fr";
		 List<String> usersEnBase = new ArrayList<>();
		 usersEnBase.add(email);
		 when(em.createNamedQuery("Utilisateur.findByEmail",String.class)).thenReturn(query);
		 when(query.setParameter("email",email)).thenReturn(query);
		 when(query.getResultList()).thenReturn(usersEnBase);
		 LOG.info("Lorsque ejb.SearchExistenceEmail(email)");
		 
		 boolean result = ejb.searchExistenceEmail(email);
		 
		 LOG.info("Alors le service renvoie vrai");
		 assertEquals("userExists devrait retourner vrai si l'email est en base", true, result);
	}
	
	@Test
	public void SearchExistenceLoginTest(){
		 LOG.info("Etant donne une adresse email existant en base");
		 String Login="test@email.fr";
		 List<String> usersEnBase = new ArrayList<>();
		 usersEnBase.add(Login);
		 
		 when(em.createNamedQuery("Utilisateur.findByLogin",String.class)).thenReturn(query);
		 when(query.setParameter("login",Login)).thenReturn(query);
		 when(query.getResultList()).thenReturn(usersEnBase);
		 
		 LOG.info("Lorsque ejb.SearchExistenceEmail(email)");
		 
		 boolean result = ejb.searchExistenceLogin(Login);
		 
		 LOG.info("Alors le service renvoie vrai");
		 assertEquals("userExists devrait retourner vrai si l'email est en base", true, result);
	}
}
