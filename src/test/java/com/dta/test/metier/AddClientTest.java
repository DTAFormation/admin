package com.dta.test.metier;

import javax.ejb.EJB;

import org.junit.Ignore;
import org.junit.Test;

import com.dta.entities.Utilisateur;
import com.dta.metier.AddClientEJB;

public class AddClientTest {
	
	@EJB
	private AddClientEJB addClientEJB;
	
	@Ignore("Not ready to run ")
	@Test
	public void testAjoutClientSansAdresse(){
		Utilisateur utilisateur= new Utilisateur("testAjout@client.com",
												000, "logintestAjout", 
												"nomTest", "passwordTest", 
												"prenomTest", 123, "Monsieur", "a",null);
		addClientEJB.save(utilisateur);
	}	
	
	//@Ignore("Not ready to run ")
	@Test//(expected = Exception.class)
	public void testAjoutClientDoublonLogin(){
		Utilisateur utilisateur= new 
					Utilisateur("testAjoutdoublon@client.com",
								000, "logintestAjoutDoublon", 
								"nomTest", "passwordTest", 
								"prenomTest", 123, "Monsieur", 
								"a",null);
		Utilisateur utilisateur2= new 
				Utilisateur("testAjoutdoublon2@client.com",
							000, "logintestAjoutDoublon", 
							"nomTest2", "passwordTest2", 
							"prenomTest2", 123, "Monsieur",
							"a",null);

		addClientEJB.save(utilisateur);
		addClientEJB.save(utilisateur2);
	}
}
