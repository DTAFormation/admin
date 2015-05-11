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

import com.dta.entities.Article;
import com.dta.entities.Produit;
import com.dta.metier.SearchArticle;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchArticleTest{

	private SearchArticle searchArticle;

	private static final Logger LOG = LoggerFactory.getLogger(SearchArticleTest.class);	
	
	@Mock private EntityManager em;
	@Mock private Query query;
	@Mock private CriteriaQuery<Object> criteriaQuery;
	@Mock private CriteriaBuilder criteriaBuilder;
	@Mock private AbstractQuery<Object> abstractQuery;

	@Before
	public void setUp() {
		searchArticle = new SearchArticle();
		searchArticle.setEm(em);
	}

	@Test
	public void testRequestGenerator(){

		Article article = new Article();

		article.setNom("test");
		article.setPrix(-1);
		article.setStock(-1);

		String generated = searchArticle.requestGenerator(article, "", "");
		Assert.assertEquals(generated, "SELECT a FROM Article a WHERE a.nom LIKE '%test%' ");

		article.setNom(null);
		article.setPrix(1);
		article.setStock(-1);

		generated = searchArticle.requestGenerator(article, "", "");
		Assert.assertEquals(generated, "SELECT a FROM Article a WHERE a.prix=1.0 ");

		article.setNom(null);
		article.setPrix(-1);
		article.setStock(1);

		generated = searchArticle.requestGenerator(article, "", "");
		Assert.assertEquals(generated, "SELECT a FROM Article a WHERE a.stock=1 ");

		article.setNom(null);
		article.setPrix(-1);
		article.setStock(-1);

		generated = searchArticle.requestGenerator(article, "produit", "");
		Assert.assertEquals(generated, "SELECT a FROM Article a WHERE a.produit IN (SELECT p.produitId FROM Produit p WHERE p.nom ='produit') ");

		article.setNom(null);
		article.setPrix(-1);
		article.setStock(-1);

		generated = searchArticle.requestGenerator(article, "", "catalogue");
		Assert.assertEquals(generated, "SELECT a FROM Article a WHERE a.produit IN (SELECT p.produitId FROM Produit p WHERE p.catalogue IN (SELECT c.catalogueId FROM Catalogue c WHERE c.nom ='catalogue')) ");

		article.setNom("test");
		article.setPrix(1);
		article.setStock(1);

		generated = searchArticle.requestGenerator(article, "produit", "catalogue");
		Assert.assertEquals(generated, "SELECT a FROM Article a WHERE a.nom LIKE '%test%' AND a.prix=1.0 AND a.stock=1 AND a.produit IN (SELECT p.produitId FROM Produit p WHERE p.nom ='produit') AND a.produit IN (SELECT p.produitId FROM Produit p WHERE p.catalogue IN (SELECT c.catalogueId FROM Catalogue c WHERE c.nom ='catalogue')) ");

	}

	@Test
	public void searchAllArticlesTest(){
		
		List<Article> liste = new ArrayList<Article>();
		liste.add(new Article("bob", 10, new Produit(), 10));
		liste.add(new Article("bil", 20, new Produit(), 20));
		
		when(em.createNamedQuery("Article.findAll")).thenReturn(query);
		when(query.getResultList()).thenReturn(liste);

		int expectedResult = 2;
		
		int result = searchArticle.findAll().size();
		Assert.assertEquals(expectedResult, result);
	}

	@Test
	public void findByIdTest(){
			
		LOG.info("Etant donne un id de produit existant en base");
		
		// Programmer le comportement du mock
		List<Article> articlesEnBase = new ArrayList<>();
		articlesEnBase.add(new Article());
		when(em.createNamedQuery("Article.findById")).thenReturn(query);
		when(query.getResultList()).thenReturn(articlesEnBase);
		
		
		
		LOG.info("Objet suppose etre recu");
		List<Article> articleAttendu = new ArrayList<>();
		Article monArticleAttendu = new Article();
		articleAttendu.add(monArticleAttendu);
		
		LOG.info("Lorsque service.findById(0)");
		List<Article> result = searchArticle.findById(0);
		
		LOG.info("");
		assertEquals(result.get(0).toString(),articleAttendu.get(0).toString());
	}
	
	
	@Test
	public void findByNameTest(){
			
		LOG.info("Etant donne un id de produit existant en base");
		
		// Programmer le comportement du mock
		List<Article> articlesEnBase = new ArrayList<>();
		articlesEnBase.add(new Article("testNom", 0.0f, null, 0));
		when(em.createNamedQuery("Article.findByName")).thenReturn(query);
		when(query.getResultList()).thenReturn(articlesEnBase);
			
		LOG.info("Objet suppose etre recu");
		List<Article> articleAttendu = new ArrayList<>();
		Article monArticleAttendu = new Article("testNom", 0.0f, null, 0);
		articleAttendu.add(monArticleAttendu);
		
		LOG.info("Lorsque service.findByName(testNom)");
		List<Article> result = searchArticle.findByName("testNom");
		
		LOG.info("");
		assertEquals(result.get(0).toString(),articleAttendu.get(0).toString());
	}
	
	@Test
	public void findDetailTest(){
			
		LOG.info("Etant donne un id de produit existant en base");
		
		// Programmer le comportement du mock
		List<Article> articlesEnBase = new ArrayList<>();
		articlesEnBase.add(new Article("testNom", 1.0f, null, 0));
		
		when(em.createQuery("SELECT a FROM Article a WHERE a.prix=1.0 ")).thenReturn(query);
		when(query.getResultList()).thenReturn(articlesEnBase);
			
		
		LOG.info("Objet suppose etre recu");
		List<Article> articleAttendu = new ArrayList<>();
		Article monArticleAttendu = new Article("testNom", 1.0f, null, 0);
		articleAttendu.add(monArticleAttendu);
		
		LOG.info("Lorsque service.findByName(testNom)");
		Article articleModel = new Article(null, 1.0f, null, -1);
		String produitModel = "";
		String catalogueModel = "";
		System.out.println(searchArticle.requestGenerator(articleModel, produitModel, catalogueModel));
		List<Article> result = searchArticle.findDetail(articleModel,produitModel, catalogueModel);
		
		LOG.info("");
		assertEquals(result.get(0).toString(),articleAttendu.get(0).toString());
	}
	
}
