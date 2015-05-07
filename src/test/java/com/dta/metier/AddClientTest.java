package com.dta.metier;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.dta.entities.Utilisateur;
import com.dta.metier.AddClientEJB;

@RunWith(MockitoJUnitRunner.class)
public class AddClientTest {
	
	@Test
	public void testAjoutClientSansAdresse(){
		AddClientEJB cab = new AddClientEJB();
		Utilisateur utilisateur= new Utilisateur("testAjout@client.com",
				000, "logintestAjout", 
				"nomTest", "passwordTest", 
				"prenomTest", 123, "Monsieur", "a",null);
		EntityManager em = Mockito.mock(EntityManager.class);
		Mockito.doNothing().when(em).persist(Mockito.anyObject());
		cab.setEm(em);
		cab.save(utilisateur);
		Mockito.verify(em).persist(Mockito.anyObject());;
	}
	
	
}
