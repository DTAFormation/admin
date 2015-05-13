package com.dta.validateur.addclient;

import static org.mockito.Mockito.when;

import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.dta.metier.AddArticleEJB;
import com.dta.validateur.ArticleValidator;

@RunWith(MockitoJUnitRunner.class)
public class ArticleValidatorTest {
    
    @Mock private AddArticleEJB addArticleEJB;
    private ArticleValidator av;
    
    @Before
    public void init(){
        av=new ArticleValidator();
        av.setEjb(addArticleEJB);  
    }
    
    @Test(expected=ValidatorException.class)
    public void validateTrueTest(){
        String Article = "Article";
        
        when(addArticleEJB.isArticleNameExists(Article)).thenReturn(true);
        av.validate(null, null, Article);
    }
    
    @Test
    public void validateFalseTest(){
        String Article = "Article";
        
        when(addArticleEJB.isArticleNameExists(Article)).thenReturn(false);
        av.validate(null, null, Article);
        Mockito.verify(addArticleEJB).isArticleNameExists(Article);
    }
}
