package com.dta.metier;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dta.entities.Article;
import com.dta.entities.Produit;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteArticleTest {

	private DeleteArticleEJB deleteArticleEJB;

	private static final Logger LOG = LoggerFactory
			.getLogger(DeleteArticleEJB.class);

	@Mock private EntityManager em;

	@Before
	public void setUp() {
		deleteArticleEJB = new DeleteArticleEJB();
		deleteArticleEJB.setEm(em);
	}

	@Test
	public void testDeleteArticle() {
		LOG.info("Creation d'un produit P");
		Produit produit = new Produit("description", "nomProduit");
		LOG.info("Creation d'un article A dans les produits P");
		Article article = new Article("nomArticle", 18.2f, produit, 12);

		when(em.find(Article.class, 0)).thenReturn(article);
		doNothing().when(em).remove(article);
		deleteArticleEJB.setEm(em);
		deleteArticleEJB.delete(0);
		verify(em).remove(article);
	}
}