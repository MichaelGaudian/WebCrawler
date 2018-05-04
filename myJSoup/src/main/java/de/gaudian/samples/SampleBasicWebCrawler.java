package de.gaudian.samples;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class SampleBasicWebCrawler {

    private HashSet<String> links;

    public SampleBasicWebCrawler() {
        links = new HashSet<String>();
    }

    public void getPageLinks(String URL) {
        //4. Pruefen ob die URL nicht bereits gecrawled wurde 
        if (!links.contains(URL)) {
            try {
                //4. (i) Wenn nicht im HashSet nicht hinzugefuegt
                if (links.add(URL)) {
                    System.out.println(URL);
                }

                //2. HTML Code holen
                Document document = Jsoup.connect(URL).get();
                //3. HTML Links werden extrahiert
                Elements linksOnPage = document.select("a[href]");

                //5. Rekursiv fuer jede URL Funktion erneut oeffnen
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        //1. Waehle URL vom Frontier
        new SampleBasicWebCrawler().getPageLinks("http://www.mkyong.com/");
    }

}
