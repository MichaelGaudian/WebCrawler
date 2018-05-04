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
    
    public WebCrawler() {
    	crawledLinks = new HashSet<String>();
    }
    
    public void setParameter(String relevantCrawlerLink) {
    	this.relevantCrawlerLink = relevantCrawlerLink;
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

                for (Element page : otherLinks) {
                    if (crawledLinks.add(url)) {
                        System.out.println(url);
                    }
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }


}
