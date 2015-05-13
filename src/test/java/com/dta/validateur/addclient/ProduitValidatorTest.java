package com.dta.validateur.addclient;


import static org.mockito.Mockito.when;

import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.dta.metier.AddProduitEJB;
import com.dta.validateur.ProduitValidator;

@RunWith(MockitoJUnitRunner.class)
public class ProduitValidatorTest {
    
    @Mock private AddProduitEJB addProduitEJB;
    private ProduitValidator pv;
    
    @Before
    public void init(){
        pv=new ProduitValidator();
        pv.setAddProduitEJB(addProduitEJB);  
    }
    
    @Test(expected=ValidatorException.class)
    public void validateTrueTest(){
        String Produit = "Produit";
        
        when(addProduitEJB.isProduitNameExists(Produit)).thenReturn(true);
        pv.validate(null, null, Produit);
    }
    
    @Test
    public void validateFalseTest(){
        String Produit = "Produit";
        
        when(addProduitEJB.isProduitNameExists(Produit)).thenReturn(false);
        pv.validate(null, null, Produit);
        Mockito.verify(addProduitEJB).isProduitNameExists(Produit);
    }
}
