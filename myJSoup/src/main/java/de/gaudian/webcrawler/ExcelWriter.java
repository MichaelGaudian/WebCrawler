package de.gaudian.webcrawler;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    private static final String FILE_NAME = "C:\\GitRepository\\WebCrawler\\myJSoup\\Spendenorganisationen.xlsx";
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private int rowNum;
    private int colNum;
    
    public ExcelWriter() {
    	workbook = new XSSFWorkbook();
    	rowNum = 0;
    	colNum = 0;
    }
 
    public void writeHeader() {
    	
    	System.out.println("Creating excel");
    	
        sheet = workbook.createSheet("Spendenorganisationen");
        String[] datatype = {
        		"source", "iban", "bic", "company_name", "street", "zip_code", "city", "homepage", "telephone", "fax", "email", "legal_form", "category"
        };

        writeInRow(datatype);
    }
    
    public void writeAndClose() {
        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME, false);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
    
    public void writeSpendenInfo(List<SpendenInfos> spendenInfos) {
     
    	String[] datatype = new String[13];
    	
        for (SpendenInfos spendenInfo : spendenInfos) {
        	
            datatype[0] = spendenInfo.getSource();
            datatype[1] = spendenInfo.getIban();
            datatype[2] = spendenInfo.getBic();
            datatype[3] = spendenInfo.getCompany_name();
            datatype[4] = spendenInfo.getStreet();
            datatype[5] = spendenInfo.getZip_code();
            datatype[6] = spendenInfo.getCity();
            datatype[7] = spendenInfo.getHomepage();
            datatype[8] = spendenInfo.getTelephone();
            datatype[9] = spendenInfo.getFax();
            datatype[10] = spendenInfo.getEmail();
            datatype[11] = spendenInfo.getLegal_form();
            datatype[12] = spendenInfo.getCategory();
            
            writeInRow(datatype);
        }
      
    }
    
    private void writeInRow(String[] datatype) {
        Row row = sheet.createRow(rowNum++);

        for (String field : datatype) {
            Cell cell = row.createCell(colNum++);
            cell.setCellValue(field);
        }
        
        colNum = 0;
    }
}
