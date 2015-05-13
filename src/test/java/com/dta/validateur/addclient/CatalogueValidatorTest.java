package com.dta.validateur.addclient;

import static org.mockito.Mockito.when;

import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.dta.metier.AddCatalogueEJB;
import com.dta.validateur.CatalogueValidator;

@RunWith(MockitoJUnitRunner.class)
public class CatalogueValidatorTest {
    
    @Mock private AddCatalogueEJB addCatalogueEJB;
    private CatalogueValidator pv;
    
    @Before
    public void init(){
        pv=new CatalogueValidator();
        pv.setEjb(addCatalogueEJB);  
    }
    
    @Test(expected=ValidatorException.class)
    public void validateTrueTest(){
        String Catalogue = "Catalogue";
        
        when(addCatalogueEJB.isCatalogueNameExists(Catalogue)).thenReturn(true);
        pv.validate(null, null, Catalogue);
    }
    
    @Test
    public void validateFalseTest(){
        String Catalogue = "Catalogue";
        
        when(addCatalogueEJB.isCatalogueNameExists(Catalogue)).thenReturn(false);
        pv.validate(null, null, Catalogue);
        Mockito.verify(addCatalogueEJB).isCatalogueNameExists(Catalogue);
    }
}
