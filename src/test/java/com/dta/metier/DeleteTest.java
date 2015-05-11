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
import com.dta.beans.UtilisateurBean;
import com.dta.entities.Article;
import com.dta.entities.Produit;
import com.dta.entities.Utilisateur;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeleteTest {

	private DeleteArticle deleteArticleEJB;
	private DeleteUtilisateur deleteUtilisateurEJB;

	private static final Logger LOG = LoggerFactory
			.getLogger(DeleteArticle.class);

	@Mock private EntityManager em;
	@Mock private ArticleBean articleBean;
	@Mock private UtilisateurBean utilisateurBean;

	@Before
	public void setUp() {
		deleteArticleEJB = new DeleteArticle();
		deleteArticleEJB.setEm(em);
		deleteUtilisateurEJB = new DeleteUtilisateur();
		deleteUtilisateurEJB.setEm(em);
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
