package de.gaudian.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Extractor {

    private List<List<String>> articles;
    private List<SpendenInfos> spendenInfos;
    private String relevantArticleLink;
    
    public Extractor() {
        articles = new ArrayList<List<String>>();
        spendenInfos = new ArrayList<SpendenInfos>();
        relevantArticleLink = "";
    }
    
    public void setParameter(String relevantArticleLink) {
    	this.relevantArticleLink = relevantArticleLink;
    }
    
	public List<SpendenInfos> getSpendenInfos() {
		return spendenInfos;
	}
    
    //Connect to each link saved in the article and find all the articles in the page
    public void getArticles(HashSet<String> links) {
        links.forEach(x -> {
            Document document;
            try {
                document = Jsoup.connect(x).get();
                Elements articleLinks = document.select("a[href^=\"" + relevantArticleLink + "\"]");
                for (Element article : articleLinks) {
                    
                    ArrayList<String> temporary = new ArrayList<>();
                    temporary.add(article.text()); //The title of the article
                    temporary.add(article.attr("abs:href")); //The URL of the article
                    if(!articles.contains(temporary)) {
                    	articles.add(temporary);  
                    }  
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
    }
    
    public List<SpendenInfos> readDziArticles() {

    	articles.forEach(x -> {
            Document document;
            try {
                document = Jsoup.connect(x.get(1)).get();

               	SpendenInfos spendenInfos = new SpendenInfos();
               	spendenInfos.setCompany_name(x.get(0));
               	spendenInfos.setSource(x.get(1));

                Elements classDetails = document.getElementsByClass("orgaboxtext");
                for (Element classDetail : classDetails) {
                	
                	Elements elementDetails = classDetail.getElementsByTag("p");
                	for (Element elementDetail : elementDetails) {
                		spendenInfos.setDziFields(elementDetail.text());
                    }
                }
            	
                this.spendenInfos.add(spendenInfos);
                
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
    	
    	return this.spendenInfos;
    }
    
    public List<SpendenInfos> readVereinslisteArticles() {
   	
    	System.out.println("Start");
    	articles.forEach(x -> {
            Document document;
            try {
                document = Jsoup.connect(x.get(1)).get();

               	SpendenInfos spendenInfos = new SpendenInfos();
               	spendenInfos.setCompany_name(x.get(0));
               	spendenInfos.setSource(x.get(1));

                Elements tagDetails = document.getElementsByTag("tr");
                for (Element elementDetail : tagDetails) {                	
                	spendenInfos.setVereinslisteFields(elementDetail.text());
                }
            	
                this.spendenInfos.add(spendenInfos);
                
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
    	
    	return this.spendenInfos;
    }
   
}