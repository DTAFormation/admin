package com.dta.test.metier;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Utilisateur;
import com.dta.metier.SearchUtilisateurEJB;

@RunWith(MockitoJUnitRunner.class)
public class SearchUtilisateurTest {

	private SearchUtilisateurEJB searchUtilisateur;

	private static final Logger LOG = LoggerFactory.getLogger(SearchUtilisateurTest.class);	

	Utilisateur utilisateur = new Utilisateur();
	Utilisateur utilisateurAttendu = new Utilisateur();
	Utilisateur utilisateurVide = new Utilisateur();
	
	@Mock private EntityManager em;
	@Mock private Query query;
	@Mock private CriteriaQuery<Object> criteriaQuery;
	@Mock private CriteriaBuilder criteriaBuilder;
	@Mock private AbstractQuery<Object> abstractQuery;

	@Before
	public void setUp() {
		searchUtilisateur = new SearchUtilisateurEJB();
		searchUtilisateur.setEm(em);

		utilisateur.setEmail("emailTest");
		utilisateur.setFax(0);
		utilisateur.setLogin("loginTest");
		utilisateur.setNom("nomTest");
		utilisateur.setPassword("passwordTest");
		utilisateur.setPrenom("prenomTest");
		utilisateur.setTelephone(0);
		utilisateur.setTitre("mlle");
		utilisateur.setTypeUtil("a");
		utilisateur.setAdresses(null);

		utilisateurAttendu.setEmail("emailTest");
		utilisateurAttendu.setFax(0);
		utilisateurAttendu.setLogin("loginTest");
		utilisateurAttendu.setNom("nomTest");
		utilisateurAttendu.setPassword("passwordTest");
		utilisateurAttendu.setPrenom("prenomTest");
		utilisateurAttendu.setTelephone(0);
		utilisateurAttendu.setTitre("mlle");
		utilisateurAttendu.setTypeUtil("a");
		utilisateurAttendu.setAdresses(null);
		
		utilisateurVide.setEmail(null);
		utilisateurVide.setFax(0);
		utilisateurVide.setLogin("loginTest");
		utilisateurVide.setNom(null);
		utilisateurVide.setPassword(null);
		utilisateurVide.setPrenom(null);
		utilisateurVide.setTelephone(0);
		utilisateurVide.setTitre(null);
		utilisateurVide.setTypeUtil(null);
		utilisateurVide.setAdresses(null);
		
	}
	
	@Test
	public void findByNameTest(){
			
		LOG.info("Etant donne un id de produit existant en base");
		
		// Programmer le comportement du mock
		List<Utilisateur> utilisateurEnBase = new ArrayList<>();
		utilisateurEnBase.add(utilisateur);
		when(em.createNamedQuery("Utilisateur.findByName")).thenReturn(query);
		when(query.getResultList()).thenReturn(utilisateurEnBase);
			
		LOG.info("Objet supposee etre recu");
		List<Utilisateur> utilisateurAttendu = new ArrayList<>();
		Utilisateur monUtilisateurAttendu = this.utilisateurAttendu;
		utilisateurAttendu.add(monUtilisateurAttendu);
		
		LOG.info("Lorsque service.findByName(nomTest)");
		List<Utilisateur> result = searchUtilisateur.findByName("nomTest");

		LOG.info("");
		assertEquals(result.get(0).toString(),utilisateurAttendu.get(0).toString());
	}
	
	
	@Test
	public void findByTitreTest(){
			
		LOG.info("Etant donne un id de produit existant en base");
		
		// Programmer le comportement du mock
		List<Utilisateur> utilisateurEnBase = new ArrayList<>();
		utilisateurEnBase.add(utilisateur);
		
		when(em.createNamedQuery("Utilisateur.findByTitre")).thenReturn(query);
		when(query.getResultList()).thenReturn(utilisateurEnBase);
			
		LOG.info("Objet suppose etre recu");
		List<Utilisateur> utilisateursAttendus = new ArrayList<>();
		Utilisateur monUtilisateurAttendu = this.utilisateurAttendu;
		utilisateursAttendus.add(monUtilisateurAttendu);
		
		LOG.info("Lorsque service.findByTitre(mlle)");
		List<Utilisateur> result = searchUtilisateur.findByTitre("mlle");

		LOG.info("");
		assertEquals(result.get(0).toString(),utilisateursAttendus.get(0).toString());
	}
	
	
	
	@Test
	public void findByIdTest(){
			
		LOG.info("Etant donne un id de utilisateur existant en base");
		
		// Programmer le comportement du mock
		Utilisateur utilisateursEnBase = new Utilisateur();
		when(em.find(Utilisateur.class, 0)).thenReturn(utilisateursEnBase);
		
		LOG.info("Objet suppose etre recu");
		Utilisateur monUtilisateurAttendu = new Utilisateur();
		
		LOG.info("Lorsque service.findById(0)");
		Utilisateur result = searchUtilisateur.findById(0);
		System.out.println(result);
		LOG.info("");
		assertEquals(result.toString(),monUtilisateurAttendu.toString());
	}
	
	@Test
	public void findDetailTest(){
			
		LOG.info("Etant donne un id de produit existant en base");
		
		// Programmer le comportement du mock
		List<Utilisateur> utilisateurEnBase = new ArrayList<>();
		utilisateurEnBase.add(utilisateur);
		
		when(em.createQuery("SELECT u FROM Utilisateur u WHERE u.login ='loginTest' ")).thenReturn(query);
		when(query.getResultList()).thenReturn(utilisateurEnBase);
			
		
		LOG.info("Objet suppose etre recu");
		List<Utilisateur> utilisateurAttendu = new ArrayList<>();
		Utilisateur monUtilisateurAttendu = this.utilisateurAttendu;
		utilisateurAttendu.add(monUtilisateurAttendu);
		
		LOG.info("Lorsque service.findByName(testNom)");
		Utilisateur utilisateurModel = utilisateurVide;
		List<Utilisateur> result = searchUtilisateur.findDetail(utilisateurModel);
		
		LOG.info("");
		assertEquals(result.get(0).toString(),utilisateurAttendu.get(0).toString());
	}
	
	@Test
	public void requestGeneratorTest(){
		Utilisateur utilisateur = new Utilisateur();
		
		utilisateur.setLogin("login");
		utilisateur.setEmail(null);
		utilisateur.setNom(null);
		utilisateur.setPrenom(null);
		utilisateur.setTypeUtil(null);
		
		String generated = searchUtilisateur.requestGenerator(utilisateur);
		Assert.assertEquals(generated, "SELECT u FROM Utilisateur u WHERE u.login ='login' ");
		
		utilisateur.setLogin(null);
		utilisateur.setEmail(null);
		utilisateur.setNom("nom");
		utilisateur.setPrenom(null);
		utilisateur.setTypeUtil(null);
		
		generated = searchUtilisateur.requestGenerator(utilisateur);
		Assert.assertEquals(generated, "SELECT u FROM Utilisateur u WHERE u.nom ='nom' ");
		
		utilisateur.setLogin(null);
		utilisateur.setEmail(null);
		utilisateur.setNom(null);
		utilisateur.setPrenom("prenom");
		utilisateur.setTypeUtil(null);
		
		generated = searchUtilisateur.requestGenerator(utilisateur);
		Assert.assertEquals(generated, "SELECT u FROM Utilisateur u WHERE u.prenom ='prenom' ");
		
		utilisateur.setLogin(null);
		utilisateur.setEmail(null);
		utilisateur.setNom(null);
		utilisateur.setPrenom(null);
		utilisateur.setTypeUtil("a");
		
		generated = searchUtilisateur.requestGenerator(utilisateur);
		Assert.assertEquals(generated, "SELECT u FROM Utilisateur u WHERE u.typeUtil ='a' ");
		
		utilisateur.setLogin(null);
		utilisateur.setEmail("email");
		utilisateur.setNom(null);
		utilisateur.setPrenom(null);
		utilisateur.setTypeUtil(null);
		
		generated = searchUtilisateur.requestGenerator(utilisateur);
		Assert.assertEquals(generated, "SELECT u FROM Utilisateur u WHERE u.email ='email' ");
		
		utilisateur.setLogin("login");
		utilisateur.setEmail("email");
		utilisateur.setNom("nom");
		utilisateur.setPrenom("prenom");
		utilisateur.setTypeUtil("a");
		
		generated = searchUtilisateur.requestGenerator(utilisateur);
		Assert.assertEquals(generated, "SELECT u FROM Utilisateur u WHERE u.login ='login' AND u.nom ='nom' AND u.prenom ='prenom' AND u.email ='email' AND u.typeUtil ='a' ");
		
	}
	
	@Test
	public void findAuthentificationTest(){
		
		LOG.info("DEBUT TEST findAuthentificationTest");

		
		Utilisateur utilisateurExpected = new Utilisateur();
		utilisateurExpected.setUtilisateurId(1);
		
		when(em.createQuery("SELECT u FROM Utilisateur u WHERE u.login = :login AND u.password = :password AND (u.typeUtil ='a' OR u.typeUtil ='m')")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(utilisateurExpected);
		
		Utilisateur utilisateurTrouve = (Utilisateur) searchUtilisateur.findAuthentification("login", "password");
		Assert.assertEquals(utilisateurTrouve.getUtilisateurId(), 1);
		
		LOG.info("FIN TEST findAuthentificationTest");
	}
	
	@Test
	public void findAuthentification_NoResult_Test(){
		
		LOG.info("DEBUT TEST findAuthentification_NoResult_Test");

		when(em.createQuery("SELECT u FROM Utilisateur u WHERE u.login = :login AND u.password = :password AND (u.typeUtil ='a' OR u.typeUtil ='m')")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(new NoResultException());
		LOG.info("Etant donne un utilisateur qui se connecte avec un mauvais login ou password");

		Object utilisateur = searchUtilisateur.findAuthentification("login", "password");
		
		LOG.info("Une erreur est levee : " + utilisateur.toString());

		Assert.assertEquals(utilisateur.toString(),"javax.persistence.NoResultException");
		LOG.info("FIN TEST findAuthentification_NoResult_Test");
	}
}
