package com.dta.validateur;

import javax.faces.validator.ValidatorException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.dta.metier.AddUtilisateurEJB;
import com.dta.validateur.ExistenceLoginValidator;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExistenceLoginValidatorTest {
	@Mock private AddUtilisateurEJB addclientEJB;
	private ExistenceLoginValidator elv;
	
	@Before
	public void init(){
		elv=new ExistenceLoginValidator();
		elv.setAddclientEJB(addclientEJB);	
	}
	
	@Test(expected=ValidatorException.class)
	public void validateTrueTest(){
		String login = "login";
		
		when(addclientEJB.searchExistenceLogin(login)).thenReturn(true);
		elv.validate(null, null, login);
	}
	
	@Test
	public void validateFalseTest(){
		String login = "login";
		
		when(addclientEJB.searchExistenceLogin(login)).thenReturn(false);
		elv.validate(null, null, login);
		Mockito.verify(addclientEJB).searchExistenceLogin(login);
	}
}
