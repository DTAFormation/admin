package com.dta.metier;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.dta.beans.ClientAddBean;
import com.dta.entities.Utilisateur;
import com.dta.metier.AddClientEJB;

/*
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatefulBean;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.Configuration;
import org.apache.openejb.junit.Module;

import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dta.entities.Utilisateur;
import com.dta.metier.AddClientEJB;
/**/
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
