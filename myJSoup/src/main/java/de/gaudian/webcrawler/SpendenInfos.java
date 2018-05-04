package de.gaudian.webcrawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SpendenInfos{
	
	private String source;
	private String iban;
	private String bic;
	private String company_name;
	private String street;
	private String zip_code;
	private String city;
	private String homepage;
	private String telephone;
	private String fax;
	private String email;
	private String legal_form;
	private String category;
	
	public SpendenInfos() {
		source = "Nicht vorhanden";
		iban = "Nicht vorhanden";
		bic = "Nicht vorhanden";
		company_name = "Nicht vorhanden";
		street = "Nicht vorhanden";
		zip_code = "Nicht vorhanden";
		city = "Nicht vorhanden";
		homepage = "Nicht vorhanden";
		telephone = "Nicht vorhanden";
		fax = "Nicht vorhanden";
		email = "Nicht vorhanden";
		legal_form = "Nicht vorhanden";
		category = "Nicht vorhanden";
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
		System.out.println("Source: " + this.source);
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
		System.out.println("IBAN: " + this.iban);
	}
	public String getBic() {
		return bic;
	}
	public void setBic(String bic) {
		this.bic = bic;
		System.out.println("BIC: " + this.bic);
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
		System.out.println("Company Name: " + this.company_name);
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
		System.out.println("Street: " + this.street);
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
		System.out.println("Zip: " + this.zip_code);
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
		System.out.println("City: " + this.city);
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
		System.out.println("homepage: " + this.homepage);
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
		System.out.println("Telephone: " + this.telephone);
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
		System.out.println("Fax: " + this.fax);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		System.out.println("Email: " + this.email);
	}
	public String getLegal_form() {
		return legal_form;
	}
	public void setLegal_form(String legal_form) {
		this.legal_form = legal_form;
		System.out.println("Legal Form: " + this.legal_form);
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
		System.out.println("Category: " + this.category);
	}	
	
	public void setFields(String text) {
		if(text.contains("Website")) {
			setHomepage(text.substring(8, text.length()));
		}
		if(text.contains("Email")) {
			setEmail(text.substring(6, text.length()));
		}
		if(text.contains("Telefon")) {
			setTelephone(text.substring(8, text.length()));
		}
		if(text.contains("Fax")) {
			setFax(text.substring(4, text.length()));
		}
		if(text.contains("Anschrift")) {
			// Street
		    Pattern r = Pattern.compile("Anschrift ([0-9]+|)[a-zA-Z.‰ˆ¸ﬂƒ÷‹ -\\/]+()[0-9-a-z]+");
		    Matcher m = r.matcher(text);
		    
		    if (m.find()) {
		    	setStreet(m.group(0).substring(10, m.group(0).length()));
		    }else {
		        System.out.println("NO MATCH");
		    }
		    
		    // Zip Code
		    r = Pattern.compile("[0-9]{5}");
		    m = r.matcher(text);
		    
		    if (m.find()) {
		    	setZip_code(m.group(0));
		    }else {
		        System.out.println("NO MATCH");
		    }
		    
		    // City
		    r = Pattern.compile("[0-9]{5} [A-Za-z÷ƒ‹ˆ‰¸ﬂ ]+");
		    m = r.matcher(text);
		    
		    if (m.find()) {
		    	setCity(m.group(0).substring(6, m.group(0).length()));
		    }else {
		        System.out.println("NO MATCH");
		    }
		    
		    if(text.contains("USA")) {
			    // City fuer USA
			    r = Pattern.compile("[A-Za-z , ]+[0-9]{5}");
			    m = r.matcher(text);
			    
			    if (m.find()) {
			    	setCity(m.group(0).substring(0, m.group(0).length()-6) + " (USA)");
			    }else {
			        System.out.println("NO MATCH");
			    }
		    }
		}
		if(text.contains("IBAN")) {
		    Pattern r = Pattern.compile("IBAN: [A-Za-z0-9]+");
		    Matcher m = r.matcher(text);
		    
		    if (m.find()) {
		    	setIban(m.group(0).substring(6, m.group(0).length()));
		    }else {
		        System.out.println("NO MATCH");
		    }
		}
		if(text.contains("BIC")) {
			Pattern r = Pattern.compile("BIC: [A-Za-z0-9]+");
			Matcher m = r.matcher(text);
		    
		    if (m.find()) {
		    	setBic(m.group(0).substring(5, m.group(0).length()));
		    }else {
		        System.out.println("NO MATCH");
		    }
		}
		if(text.contains("Rechtsform")) {
			setLegal_form(text.substring(11, text.length()));
		}
		if(text.contains("Steuerstatus")) {
			setCategory(text.substring(13, text.length()));
		}
	}
}