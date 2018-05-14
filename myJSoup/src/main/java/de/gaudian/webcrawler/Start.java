package de.gaudian.webcrawler;

public class Start {

    public static void main(String[] args) {
    	
    	Extractor extractor = new Extractor();
    	WebCrawler webCrawler = new WebCrawler();
    	ExcelWriter excelWriter = new ExcelWriter();

    	    	
    	// www.vereinsliste.de
    	// Parameter: Welche Links sollen zum Crawlen auf der angegebenen Seite beruecksichtigt werden
    	webCrawler.setParameter("/Vereine/", "./");
    	webCrawler.getPageLinks("http://www.vereinsliste.de/Vereine/Bundesl%E4nder");

    	System.out.println("Warten");
    	//Parameter: Links fuer die relevanten Artikel
    	extractor.setParameter("/verein/verein");
        extractor.getArticles(webCrawler.getCrawledLinks());
        extractor.readVereinslisteArticles();
               
    	excelWriter.writeHeader();
    	excelWriter.writeSpendenInfo(extractor.getSpendenInfos());
    	excelWriter.writeAndClose();
    	
    	
    	// www.dzi.de
    	//Parameter: Welche Links sollen zum Crawlen auf einer Seite beruecksichtigt werden
        //webCrawler.setParameter("http://www.dzi.de/spenderberatung/datenbanksuchmaske/suchergebnisse/seite/");
    	//webCrawler.getPageLinks("http://www.dzi.de/spenderberatung/datenbanksuchmaske/suchergebnisse/seite/1/?typ=alle&keyword=&bereiche=alle&laender=alle&sitz=alle");

    	//Parameter: Links fuer die relevanten Artikel
    	//extractor.setParameter("http://www.dzi.de/spenderberatung/datenbanksuchmaske/suchergebnisse/");
    	//extractor.getArticles(webCrawler.getCrawledLinks());
        //extractor.readDziArticles();
               
        //excelWriter.writeHeader();
    	//excelWriter.writeSpendenInfo(extractor.getSpendenInfos());
    	//excelWriter.writeAndClose();
    }

}
