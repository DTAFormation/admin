package com.dta.validateur.addclient;


import static org.mockito.Mockito.when;

import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.dta.metier.AddUtilisateurEJB;
import com.dta.validateur.ExistenceEmailValidator;

@RunWith(MockitoJUnitRunner.class)
public class ExistenceEmailValidatorTest {
	@Mock private AddUtilisateurEJB addclientEJB;
	private ExistenceEmailValidator elv;
	
	@Before
	public void init(){
		elv=new ExistenceEmailValidator();
		elv.setAddclientEJB(addclientEJB);	
	}
	
	@Test(expected=ValidatorException.class)
	public void validateTrueTest(){
		String Email = "Email";
		
		when(addclientEJB.searchExistenceEmail(Email)).thenReturn(true);
		elv.validate(null, null, Email);
	}
	
	@Test
	public void validateFalseTest(){
		String Email = "Email";
		
		when(addclientEJB.searchExistenceEmail(Email)).thenReturn(false);
		elv.validate(null, null, Email);
		Mockito.verify(addclientEJB).searchExistenceEmail(Email);
	}
}
