package net.javaguides.maven_web_project.ComapreCsvFileAndGenerateExcel;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 *
 */
public class Utility2 {
	public static void main(String[] args) {

		Workbook workbook = new XSSFWorkbook();
		try {
			OutputStream fileOut = new FileOutputStream("files/Compared_Data.xlsx");

			Sheet sheet = workbook.createSheet("TemplateSheet");

			String[] columnss = { "Batch Name", "Doc Type", "Doc No", "Field Type", "Expected Value", "Extracted Value",
					"Found", };
			Row headerRow = sheet.createRow(0);

//          String[] columnss = {"Document Name","Column Name", "Extracted Value","Expected Value","Found",}; 
			int rowNum = 1;

			for (int i = 0; i < columnss.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columnss[i]);
			}

			BufferedReader goldBr = null;
			BufferedReader extractedBr = null;

			String goldLine = "", extractedLine = "", cvsSplitBy = ",";

			String batchName = "", docType = "", docNo = "", fieldType = "", expectedValue = "", extractedValue = "";

			File goldFolder = new File("files/goldFiles");

			File[] listOfFiles = goldFolder.listFiles();
			for (File file : listOfFiles) {
				if (file.isFile()) {
					goldBr = new BufferedReader(new FileReader(file));

					File extractedFilesFolder = new File("files/extractedFiles");
					File[] extfactedListOfFiles = extractedFilesFolder.listFiles();

					for (File extractedFile : extfactedListOfFiles) {
						if (extractedFile.isFile()) {
							extractedBr = new BufferedReader(new FileReader(extractedFile));
//	    	        	    System.err.println("matched file name : "+file.getName()+" extrac "+extractedFile.getName());

							if (file.getName().equals(extractedFile.getName())) {
//	    	        	     System.err.println("matched file name : "+file.getName()+" extrac "+extractedFile.getName());

								int i = 0;
								int j = 0;

//	    	   	             System.out.println("first line : = "+goldBr.read);

								/*
								 * int fieldcount=1; java.util.List<String> li =new ArrayList<String>(); while
								 * ((goldLine = goldBr.readLine()) != null) { String[] goldDataReading =
								 * goldLine.split(cvsSplitBy);
								 * 
								 * int forcount=0; for (String goldDataFieldsName : goldDataReading) {
								 * 
								 * goldDataFieldsName = goldDataFieldsName.replace("+ACI-",
								 * "").replace("\"",""); if(fieldcount==1) { li.add(goldDataFieldsName); }
								 * 
								 * forcount++; } fieldcount++;
								 * 
								 * }
								 * 
								 */
								
								int count = 0;
								while ((goldLine = goldBr.readLine()) != null) {
									System.out.println("-------------@count@-----------------"+count);
//									if(count>1)
//									{
									extractedLine = extractedBr.readLine();
									String[] goldDataReading = goldLine.split(cvsSplitBy);
									String[] extractedDataReading = extractedLine.split(cvsSplitBy);
									String filesNames[]=extractedFile.getName().split("_",2);
									 
									 batchName=filesNames[0]; 
									 docType=filesNames[1].split("\\.")[0];
									 System.err.println(batchName+",,"+docType);
									for (String goldDataFieldsName : goldDataReading) {
										try {
											

											
											for (String extractedDataFieldsName : extractedDataReading) {
												extractedDataFieldsName=extractedDataFieldsName.replace("+ACI-", "").replace("\"","");
												String extractedDataDocId = extractedDataReading[count].replace("\"",
														"");
												
												
												if (count >1) {
													System.out.println("E@Val : " + extractedDataFieldsName);
												}
												
//												if (goldDataFieldsName.equalsIgnoreCase(extractedDataFieldsName)) {
//													System.out.println("E : " + extractedDataFieldsName);

//													System.out.println("@count@"+count);
													/*
													 * 
													 * String found = "FALSE"; if
													 * (expectedValue.equalsIgnoreCase(extractedValue)) { found =
													 * "TRUE"; }
													 * 
													 * Row row = sheet.createRow(rowNum++);
													 * 
													 * j=0;
													 * 
													 * 
													 * row.createCell(j).setCellValue(batchName); j++;
													 * row.createCell(j).setCellValue(docType); j++;
													 * row.createCell(j).setCellValue("docno"); j++;
													 * row.createCell(j).setCellValue(""); j++;
													 * row.createCell(j).setCellValue(""); j++;
													 * row.createCell(j).setCellValue(""); j++;
													 * row.createCell(j).setCellValue("");
													 */

//												}

//												 System.out.println(goldDataDocId+",,"+extractedDataDocId);

											}

											/*
											 * String filesNames[]=extractedFile.getName().split("_",2);
											 * 
											 * batchName=filesNames[0]; docType=filesNames[1].split("\\.")[0];
											 * expectedValue=goldDataReading[1].replace("\"","");
											 * extractedValue=extractedDataReading[1].replace("\"","");
											 */
//										  System.out.println(expectedValue);

//										  }
										} catch (Exception e) {
											e.printStackTrace();
										}

//									}
									count++;

									/*
									 * try { String filesNames[]=extractedFile.getName().split("_",2); batchName
									 * =filesNames[0]; docType=filesNames[1].split("\\.")[0];
									 * 
									 * expectedValue=goldDataReading[1].replace("\"","");
									 * expectedValue=goldDataReading[1].replace("\"","");
									 * extractedValue=extractedDataReading[1].replace("\"",""); } catch(Exception e)
									 * {
									 * 
									 * }
									 * 
									 * // if(!columnName1.equalsIgnoreCase("columnname") &&
									 * !columnName2.equalsIgnoreCase("columnname") ) {
									 * 
									 * String found = "FALSE"; if (expectedValue.equalsIgnoreCase(extractedValue)) {
									 * found = "TRUE"; }
									 * 
									 * Row row = sheet.createRow(rowNum++);
									 * 
									 * j=0;
									 * 
									 * 
									 * row.createCell(j).setCellValue(batchName); j++;
									 * row.createCell(j).setCellValue(docType); j++;
									 * row.createCell(j).setCellValue(extractedFile.getName()); j++;
									 * row.createCell(j).setCellValue(columnName1); j++;
									 * row.createCell(j).setCellValue(expectedValue); j++;
									 * row.createCell(j).setCellValue(extractedValue); j++;
									 * row.createCell(j).setCellValue(found);
									 * 
									 * // System.out.println(columnName1+" Expected value : "
									 * +expectedValue+",,extracted value : "+extractedValue+",,found : "+found);
									 * 
									 * // }
									 */ 
									
								}
									}
							}

						}

					}
				}
			}

			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
		}
	}
}
