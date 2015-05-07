package com.dta.metier;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.beans.ArticleBean;
import com.dta.entities.Article;
import com.dta.entities.Produit;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteTest {
	
	private DeleteArticle deleteArticle;

	private static final Logger LOG = LoggerFactory.getLogger(DeleteArticle.class);	
	
	@Mock private EntityManager em;
	@Mock private ArticleBean articleBean;
	
	@Before
	public void setUp() {
		deleteArticle = new DeleteArticle();
		deleteArticle.setEm(em);
	}
	
	@Test
	public void testDeleteArticle(){
		Produit produit = new Produit("description", "nomProduit");
		Article article = new Article("nomArticle", 18.2f, produit, 12);
		
	}

}
