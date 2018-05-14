package de.gaudian.webcrawler;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	
    private HashSet<String> crawledLinks;
    private String relevantCrawlerLink;
    private String relevantCrawlerLink2;
    
    public WebCrawler() {
    	crawledLinks = new HashSet<String>();
    	relevantCrawlerLink = "";
    	relevantCrawlerLink2 = "";
    }
    
    public void setParameter(String relevantCrawlerLink) {
    	this.relevantCrawlerLink = relevantCrawlerLink;
    }
    
    public void setParameter(String relevantCrawlerLink, String relevantCrawlerLink2) {
    	this.relevantCrawlerLink = relevantCrawlerLink;
    	this.relevantCrawlerLink2 = relevantCrawlerLink2;
    }
    
	public HashSet<String> getCrawledLinks() {
		return crawledLinks;
	}

    //Find all URLs that start with url and add them to the HashSet
    public void getPageLinks(String url) {
        if (!crawledLinks.contains(url)) {

            try {
                Document document = Jsoup.connect(url).get();
                Elements otherLinks = document.select("a[href^=\"" + relevantCrawlerLink + "\"]");
                
                if (!relevantCrawlerLink2.equals("")) {
                	otherLinks.addAll(document.select("a[href^=\"" + relevantCrawlerLink2 + "\"]"));
                }
                
                for (Element page : otherLinks) {
                	
                    if(blackList(page.attr("href"))) {
                    	//System.out.println("Schutz! Seite verlinkt auf sich selbst.");
                    }else {
                        if (crawledLinks.add(url)) {
                            System.out.println(url);
                        }
                     
                        getPageLinks(page.attr("abs:href"));
                    }   
                	

                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    public boolean blackList(String link) {
    	
    	if (link.equals(".//") || link.contains("Bildung") || link.contains("Sport") || link.contains("Freizeit")
    			|| link.contains("Gesundheit") || link.contains("Kinder") || link.contains("Kultur") || link.contains("Soziales")
    			|| link.contains("Politik") || link.contains("Auto") || link.contains("Tiere") || link.contains("Sammeln")
    		    || link.contains("Musik")  || link.contains("Tourismus")  || link.contains("Wirtschaft")  || link.contains("Natur")
    			|| link.contains("Sonstiges")) {
    		return true;
    	}
    	
    	return false;
    }


}
