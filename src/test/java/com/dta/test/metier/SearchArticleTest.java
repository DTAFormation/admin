package com.dta.test.metier;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import com.dta.metier.SearchArticle;

public class SearchArticleTest{
	
	@EJB
	private SearchArticle searchArticle;
	
	@Test
	public void searchAllArticlesTest(){
		int expectedResult = 4;
		int result = searchArticle.findAll().size();
		Assert.assertEquals(expectedResult, result);
	}

}
