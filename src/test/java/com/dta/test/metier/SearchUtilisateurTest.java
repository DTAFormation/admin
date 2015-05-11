package com.dta.test.metier;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
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
import com.dta.metier.SearchUtilisateur;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchUtilisateurTest {

	private SearchUtilisateur searchUtilisateur;

	private static final Logger LOG = LoggerFactory.getLogger(SearchArticleTest.class);	
	
	@Mock private EntityManager em;
	@Mock private Query query;
	@Mock private CriteriaQuery<Object> criteriaQuery;
	@Mock private CriteriaBuilder criteriaBuilder;
	@Mock private AbstractQuery<Object> abstractQuery;

	@Before
	public void setUp() {
		searchUtilisateur = new SearchUtilisateur();
		searchUtilisateur.setEm(em);
	}
	
	@Test
	public void findByNameTest(){
			
		LOG.info("Etant donn� un id de produit existant en base");
		
		// Programmer le comportement du mock
		List<Utilisateur> utilisateurEnBase = new ArrayList<>();
		utilisateurEnBase.add(new Utilisateur("emailTest", 0, "loginTest", "nomTest", "passwordTest", "prenomTest", 0, "", "a", null));
		when(em.createNamedQuery("Utilisateur.findByName")).thenReturn(query);
		when(query.getResultList()).thenReturn(utilisateurEnBase);
			
		LOG.info("Objet suppos� etre re�u");
		List<Utilisateur> utilisateurAttendu = new ArrayList<>();
		Utilisateur monUtilisateurAttendu = new Utilisateur("emailTest", 0, "loginTest", "nomTest", "passwordTest", "prenomTest", 0, "", "a", null);
		utilisateurAttendu.add(monUtilisateurAttendu);
		
		LOG.info("Lorsque service.findByName(nomTest)");
		List<Utilisateur> result = searchUtilisateur.findByName("nomTest");

		LOG.info("");
		assertEquals(result.get(0).toString(),utilisateurAttendu.get(0).toString());
	}
	
	
	@Test
	public void findByTitreTest(){
			
		LOG.info("Etant donn� un id de produit existant en base");
		
		// Programmer le comportement du mock
		List<Utilisateur> utilisateurEnBase = new ArrayList<>();
		utilisateurEnBase.add(new Utilisateur("emailTest", 0, "loginTest", "nomTest", "passwordTest", "prenomTest", 0, "mlle", "a", null));
		when(em.createNamedQuery("Utilisateur.findByTitre")).thenReturn(query);
		when(query.getResultList()).thenReturn(utilisateurEnBase);
			
		LOG.info("Objet suppos� etre re�u");
		List<Utilisateur> utilisateurAttendu = new ArrayList<>();
		Utilisateur monUtilisateurAttendu = new Utilisateur("emailTest", 0, "loginTest", "nomTest", "passwordTest", "prenomTest", 0, "mlle", "a", null);
		utilisateurAttendu.add(monUtilisateurAttendu);
		
		LOG.info("Lorsque service.findByTitre(mlle)");
		List<Utilisateur> result = searchUtilisateur.findByTitre("mlle");

		LOG.info("");
		assertEquals(result.get(0).toString(),utilisateurAttendu.get(0).toString());
	}
	
	
	
	@Test
	public void findByIdTest(){
			
		LOG.info("Etant donné un id de utilisateur existant en base");
		
		// Programmer le comportement du mock
		Utilisateur utilisateursEnBase = new Utilisateur();
		when(em.find(Utilisateur.class, 0)).thenReturn(utilisateursEnBase);
		
		LOG.info("Objet supposé etre reçu");
		Utilisateur monUtilisateurAttendu = new Utilisateur();
		
		LOG.info("Lorsque service.findById(0)");
		Utilisateur result = searchUtilisateur.findById(0);
		System.out.println(result);
		LOG.info("");
		assertEquals(result.toString(),monUtilisateurAttendu.toString());
	}
	
	@Test
	public void findDetailTest(){
			
		LOG.info("Etant donn� un id de produit existant en base");
		
		// Programmer le comportement du mock
		List<Utilisateur> utilisateurEnBase = new ArrayList<>();
		utilisateurEnBase.add(new Utilisateur("emailTest", 0, "loginTest", "nomTest", "passwordTest", "prenomTest", 0, "mlle", "a", null));
		
		when(em.createQuery("SELECT u FROM Utilisateur u WHERE u.login ='loginTest' ")).thenReturn(query);
		when(query.getResultList()).thenReturn(utilisateurEnBase);
			
		
		LOG.info("Objet suppos� etre re�u");
		List<Utilisateur> utilisateurAttendu = new ArrayList<>();
		Utilisateur monUtilisateurAttendu = new Utilisateur("emailTest", 0, "loginTest", "nomTest", "passwordTest", "prenomTest", 0, "mlle", "a", null);
		utilisateurAttendu.add(monUtilisateurAttendu);
		
		LOG.info("Lorsque service.findByName(testNom)");
		Utilisateur utilisateurModel = new Utilisateur(null, 0, "loginTest", null, null, null, 0, null, null, null);
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
	
	
	/*
		
	
	@SuppressWarnings("unchecked")
	public List<Utilisateur> findDetail (Utilisateur user){
		String requete = requestGenerator(user);
		Query query = em.createQuery(requete);
		if (query.getResultList().size() == 0)
			return new ArrayList<Utilisateur>();
		return query.getResultList();
	}
	
	*/
	
	@Test public void findAuthentificationTest(){
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setUtilisateurId(1);
		
		when(em.createQuery("SELECT u FROM Utilisateur u WHERE u.login = :login AND u.password = :password AND u.typeUtil = :type")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(utilisateur);
		
		Utilisateur util = searchUtilisateur.findAuthentification("login", "password", "type");
		Assert.assertEquals(util.getUtilisateurId(), 1);
	}
	
	/*
	
	//méthode pour authentification utilisateur
	public Utilisateur findAuthentification (String login, String password, String type){
		Query query_auth = em.createQuery("SELECT u FROM Utilisateur u WHERE u.login = :log AND u.password = :passw AND u.typeUtil = :typeUt");
		System.out.println(type);
		query_auth.setParameter("log", login);
		query_auth.setParameter("passw", password);
		query_auth.setParameter("typeUt", type);
		try{
			Utilisateur result = (Utilisateur) query_auth.getSingleResult();
			System.out.println(result);
			return result;
		}catch (NoResultException e){
			return null;
		}
	}
	*/ 
}
