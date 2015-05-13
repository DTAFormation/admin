package com.dta.beans;

import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

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
public class VentesVue {

	@EJB
	private SearchArticleEJB searchArticle;
	@EJB
	private SearchCommandeEJB searchCommande;
	
	private BarChartModel articleChart;
	private BarChartModel bestArticleChart;

	@PostConstruct
	public void init() {
		createAnimatedModels();
	}

	public BarChartModel getBestArticleChart() {
		return bestArticleChart;
	}

	public BarChartModel getArticleChart() {
		return articleChart;
	}

	private void createAnimatedModels() {

		Axis yAxis;

		articleChart = initArticle();
		articleChart.setTitle("Ventes des articles");
		articleChart.setAnimate(true);
		articleChart.setLegendPosition("ne");
		yAxis = articleChart.getAxis(AxisType.Y);
		yAxis.setMin(0); 
		yAxis.setMax(searchCommande.getMaxVentes()); 

		bestArticleChart = initMeilleurArticle();
		bestArticleChart.setTitle("Meilleures ventes d'articles");
		bestArticleChart.setAnimate(true);
		bestArticleChart.setLegendPosition("ne");
		yAxis = bestArticleChart.getAxis(AxisType.Y);
		yAxis.setMin(0); 
		yAxis.setMax(searchCommande.getMaxVentes()); 
		
	}

	private BarChartModel initArticle() {
		BarChartModel model = new BarChartModel();

		List<Article> articles = searchArticle.findAll();
		
		ChartSeries article = new ChartSeries();
		article.setLabel("Articles");
		for(Article a : articles){
			article.set(a.getNom(), searchCommande.getVentesById(a.getArticleId()));
		}
	
		model.addSeries(article);

		return model;
	}

	private BarChartModel initMeilleurArticle() {
		BarChartModel model = new BarChartModel();

		List<Article> articles = searchArticle.findAll();
		
		ChartSeries article = new ChartSeries();
		article.setLabel("Articles");
		
		SortedMap<Long, String> meilleuresVentes = new TreeMap<Long, String>();
		
		for(Article a : articles) {
			meilleuresVentes.put(searchCommande.getVentesById(a.getArticleId()), a.getNom());
		}
		
		int i=0;
		for(Entry<Long, String> entry : meilleuresVentes.entrySet()) {
			if(i++<meilleuresVentes.size()-3) continue;
			article.set(entry.getValue(), entry.getKey());
		}
	
		model.addSeries(article);

		return model;
	}
}
