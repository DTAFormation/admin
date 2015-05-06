package com.dta.test.metier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.xml.registry.infomodel.EmailAddress;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.dta.entities.Article;
import com.dta.entities.Produit;
import com.dta.metier.SearchArticle;

@RunWith(MockitoJUnitRunner.class)
public class SearchArticleTest{

	private SearchArticle searchArticle;
	
	@Mock private EntityManager em;
	@Mock private Query query;
	@Mock private CriteriaQuery<Object> criteriaQuery;
	@Mock private CriteriaBuilder criteriaBuilder;
	@Mock private AbstractQuery<Object> abstractQuery;

	@Before
	public void initTest(){
		searchArticle = new SearchArticle();
		searchArticle.setEm(this.em);
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

		Mockito.when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		Mockito.when(criteriaBuilder.createQuery()).thenReturn(criteriaQuery);
		Mockito.when(criteriaQuery.select(Mockito.anyObject())).thenReturn(criteriaQuery);
		Mockito.when(em.createQuery(Mockito.anyObject())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(liste);
		
		int expectedResult = 2;
		
		int result = searchArticle.findAll().size();
		Assert.assertEquals(expectedResult, result);
	}
}
