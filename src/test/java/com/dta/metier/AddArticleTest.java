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

import com.dta.entities.Article;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AddArticleTest {

	private static final Logger LOG = LoggerFactory.getLogger(AddArticleTest.class);
	
	@Mock 
	private EntityManager em;
	
	@Mock
	private TypedQuery<Article> query;
	
	private AddArticleEJB ejb;
	
	@Before
	public void setUp() {
		ejb = new AddArticleEJB();
		ejb.setEm(em);		
	}
	
	@Test
	public void creerArticle() {		
		LOG.info("Etant donne un objet Article");		
		Article article = new Article("nom",0,null,1,"URL de l'image");
		
		when(em.createNamedQuery("Article.findByName", Article.class)).thenReturn(query);
		when(query.setParameter("name", "nom")).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<Article>());
		
		LOG.info("Lorsque ejb.save(article)");
		ejb.save(article);
		
		LOG.info("Alors 'article' a ete persiste");
		verify(em).persist(article);
		
		LOG.info("FIN");
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void creerArticleAvecNomExistant() {
		LOG.info("Etant donne des objets Article avec le meme nom");
		Article article1 = new Article("nom",0,null,1,"URL de l'image");
		Article article2 = new Article("nom",0,null,1,"URL de l'image");
		
		when(em.createNamedQuery("Article.findByName", Article.class)).thenReturn(query);
		when(query.setParameter("name", "nom")).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<Article>(), Arrays.asList(article1));
		
		boolean firstAttempt = false;
		boolean secondAttempt = true;
		
		assertEquals(firstAttempt, ejb.isArticleNameExists(article1.getNom()));
		assertEquals(secondAttempt, ejb.isArticleNameExists(article2.getNom()));
	
		LOG.info("FIN");
		
		
	}
	
}