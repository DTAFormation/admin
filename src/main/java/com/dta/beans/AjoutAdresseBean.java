package com.dta.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.dta.entities.Adresse;

@ManagedBean
public class AjoutAdresseBean {

	private Adresse adresse;
	
    public Adresse getAdresse() {
        if (adresse == null) {
            adresse = new Adresse();
        }
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @PostConstruct
    public void init() {
        adresse = new Adresse();
    }

	public void save() {
	    AjoutUtilisateurBean.saveAdresse(adresse);
		adresse = new Adresse();
	}

}
