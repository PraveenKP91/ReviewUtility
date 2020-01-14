package net.javaguides.maven_web_project.ComapreCsvFileAndGenerateExcel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
/**
 * Hello world!
 *
 */
public class UtilityOld 
{
	    public static void main(String[] args) {
	
            Workbook workbook = new XSSFWorkbook(); 
            try {
            OutputStream fileOut = new FileOutputStream("files/Compared_Data.xlsx"); 
              
            Sheet sheet = workbook.createSheet("TemplateSheet"); 
             
//            String[] columnss = {"Batch Name", "Doc Type","Doc No","Field Type","Expected Value","Extracted Value","Found",}; Row headerRow = sheet.createRow(0);
			  
            String[] columnss = {"Document Name","Column Name", "Extracted Value","Expected Value","Found",}; Row headerRow = sheet.createRow(0);
            int rowNum = 1;
            
            for(int i = 0; i < columnss.length; i++) 
            { Cell cell =headerRow.createCell(i); cell.setCellValue(columnss[i]); }
	    	
	        BufferedReader goldBr = null;
	        BufferedReader extractedBr = null;
	        String goldLine = "",extractedLine="";
	        String cvsSplitBy = ",";
	
	        String expectedValue="",extractedValue="";
	        
	        	File goldFolder = new File("files/goldFiles");
	        	
	        	File[] listOfFiles = goldFolder.listFiles();
	        	for (File file : listOfFiles) 
	        	{
	        	    if (file.isFile()) 
	        	    {
	        	    	goldBr = new BufferedReader(new FileReader(file));
	        	    	
	        	    	File extractedFilesFolder = new File("files/extractedFiles");
	        	    	File[] extfactedListOfFiles = extractedFilesFolder.listFiles();
	        	    	
	        	    	for (File extractedFile : extfactedListOfFiles) 
	        	    	{
	    	        	    if (extractedFile.isFile()) 
	    	        	    {
	    	        	    extractedBr = new BufferedReader(new FileReader(extractedFile));
//	    	        	    System.err.println("matched file name : "+file.getName()+" extrac "+extractedFile.getName());
	    	        	    
	    	        	    if(file.getName().equals(extractedFile.getName()))
	    	        	    {
	    	        	     System.err.println("matched file name : "+file.getName()+" extrac "+extractedFile.getName());
	    	   	             
	    	   	             int i=0;
	    	   	             int j=0;
	    	        	    	 while ((goldLine = goldBr.readLine()) != null) 
	    	        	    	 {
	    	        	    	  extractedLine =extractedBr.readLine();
								  String[] goldDataReading = goldLine.split(cvsSplitBy); 
								  String[] extractedDataReading =extractedLine.split(cvsSplitBy); 
								  String columnName1 =goldDataReading[0].replace("\"","");
								  		 columnName1 =goldDataReading[0].replace("+ACI-","");
								  String columnName2 =extractedDataReading[0].replace("\"","");
								  		 columnName2 =extractedDataReading[0].replace("+ACI-","");
								  try
				                	{
				                		expectedValue=goldDataReading[1].replace("\"","");
				                		extractedValue=extractedDataReading[1].replace("\"","");
				                	}
				                	catch(Exception e)
				                	{
				                		
				                	}
								  
//								  System.err.println("columnName1 : "+columnName1+",columnName2 : "+columnName2);
								  if(!columnName1.equalsIgnoreCase("columnname") && !columnName2.equalsIgnoreCase("columnname") )
					                {
					                System.err.println(columnName1+","+columnName2);
					                String found = "FALSE";
									if (expectedValue.equalsIgnoreCase(extractedValue)) 
									{
										found = "TRUE";
									}
					                
					                Row row = sheet.createRow(rowNum++);
					                
//					                System.err.println("-----------------row num ----------"+rowNum);
//					                System.err.println("-----------extractedFile.getName()----------------"+extractedFile.getName());
					                j=0;
					                row.createCell(j).setCellValue(extractedFile.getName());
					                j++;
					                row.createCell(j).setCellValue(columnName1);
					                j++;
					                row.createCell(j).setCellValue(expectedValue);
					                j++;
					                row.createCell(j).setCellValue(extractedValue);
					                j++;
					                row.createCell(j).setCellValue(found);
					                
//					                System.out.println(columnName1+" Expected value : "+expectedValue+",,extracted value : "+extractedValue+",,found : "+found);
					                
					                }
					                }
					                i++;
								 }
	    	        	   
	    	        	    	
	    	        	     }
	    	        	    
	    	        	    }
	        	    	}
	        	    }
	        	
	        	
	        	 workbook.write(fileOut);
		            fileOut.close();
	        }
	        catch(Exception e) {}
	    }
}
