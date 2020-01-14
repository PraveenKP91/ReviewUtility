package net.javaguides.maven_web_project.ComapreCsvFileAndGenerateExcel;

import java.io.BufferedReader;
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
public class App 
{
   
	    public static void main(String[] args) {
	
	        String csvFile1 = "files/compare1.csv";
	        String csvFile2 = "files/compare2.csv";
	        BufferedReader br1 = null;
	        BufferedReader br2 = null;
	        String line = "",line1="";
	        String cvsSplitBy = ",";
	
	        try {
	
	            br1 = new BufferedReader(new FileReader(csvFile1));
	            br2 = new BufferedReader(new FileReader(csvFile2));
	            	
	            	//int i=0;
	
	               // Creating Workbook instances 
	            Workbook workbook = new XSSFWorkbook(); 
	         
	               // An output stream accepts output bytes and sends them to sink. 
	               OutputStream fileOut = new FileOutputStream("files/Compared_Data.xlsx"); 
	                 
	               // Creating Sheets using sheet object 
	               Sheet sheet = workbook.createSheet("TemplateSheet"); 
	                
				
				  String[] columnss = {"Column Name", "Extracted Value",
				  "Expected Value","Found",}; Row headerRow = sheet.createRow(0);
				  
				  for(int i = 0; i < columnss.length; i++) { Cell cell =
				  headerRow.createCell(i); cell.setCellValue(columnss[i]); 
	//			  cell.setCellStyle(headerCellStyle); 
				  }
				 
	               
	               
	             int rowNum = 1;
	             int i=0;
	             
	            while ((line = br1.readLine()) != null) 
	            {
	            	line1 =br2.readLine();
	            	
	            	if(i!=0) {
	                // use comma as separator
	                String[] csv1 = line.split(cvsSplitBy);
	                String[] csv2 = line1.split(cvsSplitBy);
	                String columnName1 =csv1[0].replace("\"","");
	                columnName1 =csv1[0].replace("+ACI-","");
	                
	                String columnName2 =csv2[0].replace("\"","");
	//                System.err.println(columnName1+","+columnName2);
	                String expectedValue="",extractedValue="";
	                if(!columnName1.equalsIgnoreCase("Columnname") && !columnName2.equalsIgnoreCase("columnname"))
	                {
	                	try 
	                	{
	                 expectedValue=csv1[1].replace("\"","");
	                
	                 extractedValue=csv2[1].replace("\"","");
	                	}
	                	catch(Exception e)
	                	{}
	                String found = "FALSE";
					if (expectedValue.equalsIgnoreCase(extractedValue)) {
						found = "TRUE";
					}
	                
	                Row row = sheet.createRow(rowNum++);
	
	                row.createCell(0).setCellValue(columnName1);
	
	                row.createCell(1).setCellValue(expectedValue);
	
	                row.createCell(2).setCellValue(extractedValue);
	                
	                row.createCell(3).setCellValue(found);
	                
	//              Cell dateOfBirthCell = row.createCell(2);
	//              dateOfBirthCell.setCellValue(extractedValue);
	//              dateOfBirthCell.setCellStyle(dateCellStyle);
	                
	                 /*
						 * 
						 * Sheet sheet2 = wb.createSheet("String"); Sheet sheet3 =
						 * wb.createSheet("LinkedList"); Sheet sheet4 = wb.createSheet("Tree"); Sheet
						 * sheet5 = wb.createSheet("Dynamic Programing"); Sheet sheet6 =
						 * wb.createSheet("Puzzles");
						 */
	                System.out.println(columnName1+" Expected value : "+expectedValue+",,extracted value : "+extractedValue+",,found : "+found);
	                
	                }
	                }
	                i++;
	            }
	            workbook.write(fileOut);
	            fileOut.close();
	            System.out.println("Sheets Has been Created successfully");
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br1 != null) {
	                try {
	                    br1.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	
	    }
	
	
	
}
