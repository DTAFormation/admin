package com.dta.test.metier;

import java.util.List;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import com.dta.entities.Produit;
import com.dta.metier.SearchProduit;

public class SearchProductTest {
	
	@EJB
	private SearchProduit searchProduit;
	

	@Test
	public void searchAllProductsTest(){
		List<Produit> produitList = searchProduit.findAll();
		Assert.assertEquals(2, produitList.size());
	}
	
	@Test
	public void searchProductByName(){
		Produit produit = searchProduit.findByName("articles de natation");
		Assert.assertEquals(produit.getProduitId(), 2);
	}
}
