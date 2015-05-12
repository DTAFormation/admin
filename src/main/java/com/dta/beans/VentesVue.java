package com.dta.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.dta.entities.Article;
import com.dta.metier.SearchArticleEJB;
import com.dta.metier.SearchCommandeEJB;

@ManagedBean
public class VentesVue implements Serializable{

	@EJB
	private SearchArticleEJB searchArticle;
	@EJB
	private SearchCommandeEJB searchCommande;
	
	private static final long serialVersionUID = 1L;

	private BarChartModel articleChart;

	@PostConstruct
	public void init() {
		createAnimatedModels();
	}



	public BarChartModel getArticleChart() {
		return articleChart;
	}

	private void createAnimatedModels() {

		Axis yAxis;

		articleChart = initBarModel();
		articleChart.setTitle("Ventes des articles");
		articleChart.setAnimate(true);
		articleChart.setLegendPosition("ne");
		yAxis = articleChart.getAxis(AxisType.Y);
		yAxis.setMin(0); 
		yAxis.setMax(30); //TODO nombre de ventes du produit le plus vendu
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		List<Article> articles = searchArticle.findAll();
		
		ChartSeries article = new ChartSeries();
		article.setLabel("Articles");
		for(Article a : articles){
			article.set(a.getNom(), searchCommande.getVentes(a.getArticleId()));
		}
	
		model.addSeries(article);

		return model;
	}



}
