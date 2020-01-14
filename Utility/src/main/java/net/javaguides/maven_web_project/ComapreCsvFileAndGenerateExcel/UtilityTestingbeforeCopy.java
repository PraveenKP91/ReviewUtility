package net.javaguides.maven_web_project.ComapreCsvFileAndGenerateExcel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UtilityTestingbeforeCopy {
		public static void main(String[] args) {

			Workbook workbook = new XSSFWorkbook();
			try {
				
				ArrayList<String> batchNameLi =new ArrayList<String>();
				ArrayList<String> docTypeLi =new ArrayList<String>();
				ArrayList<String> docNoLi =new ArrayList<String>();
				ArrayList<String> fieldNameLi =new ArrayList<String>();
				ArrayList<String> expectedValLi =new ArrayList<String>();
				ArrayList<String> extractedValLi =new ArrayList<String>();
				ArrayList<String> foundLi =new ArrayList<String>();
				
				BufferedReader goldBr = null;
				BufferedReader extractedBr = null;

				String goldLine = "", extractedLine = "", cvsSplitBy = ",",deleimeterForFileNameSplit ="-";
	  
				String batchName = "", docType = "", docNo = "", fieldType = "", expectedValue = "", extractedValue = "";

				int totalTruePositive =0,totalTrueNegative=0,totalFalsePositive =0,totalFalseNegative=0;
				
				File goldFolder = new File("files/goldFiles");

				int totalGoldCount=0,totalWhileCount=0,totalExtractedCount=0,totalRows=0;
				
				File[] listOfFiles = goldFolder.listFiles();
				for (File file : listOfFiles) {
					if (file.isFile()) {
						goldBr = new BufferedReader(new FileReader(file));

						File extractedBatchInstanceFolder = new File("files/extractedFiles");
						File[] extfactedListOfBatchInstance = extractedBatchInstanceFolder.listFiles();

						for(File batchInstance :extfactedListOfBatchInstance)
						{
						String fileBatchName =batchInstance.getName();
						
						File extractedFilesFolder = new File(batchInstance.getPath());
						File[] extfactedListOfFiles = extractedFilesFolder.listFiles();

						for (File extractedFile : extfactedListOfFiles) {
							
							String extractedFileName =extractedFile.getName().replace("-"+fileBatchName,"");
							
							if(extractedFile.getName().contains(fileBatchName))
							{
//								System.out.println(fileBatchName+",,"+extractedFile.getName());
							if (extractedFile.isFile()) {
								
								extractedBr = new BufferedReader(new FileReader(extractedFile));
//								System.out.println("*********"+fileBatchName);
								if (file.getName().equals(extractedFileName)) {
//								int j = 0;
									System.out.println(fileBatchName+"*********"+extractedFileName);
								 java.util.List<String> li =new ArrayList<String>(); 
								
									int whilecount = 0;
									int docIdPosition=0;
									String goldDocumentNo ="";
									String extractedDocumentNo ="";
									String found="FALSE";

									while ((goldLine = goldBr.readLine()) != null) {

										extractedLine = extractedBr.readLine();
										String[] goldDataReading = goldLine.split("\",\"");
										String[] extractedDataReading = extractedLine.split("\",\"");
										String filesNames[]=extractedFileName.split(deleimeterForFileNameSplit);
										 
										 batchName=fileBatchName;//filesNames[0]
										 docType=filesNames[1].split("\\.")[0];
										 System.err.println(",,"+batchName);
											/*
											 * goldDocumentNo=goldDataReading[1].replace("\"","");
											 * extractedDocumentNo=extractedDataReading[1].replace("\"","");
											 */
										 
											int goldCount=0;
											for (String goldDataFieldsName : goldDataReading) {
//										    System.out.println(goldDataFieldsName);
											try {
												
												if(whilecount==0)
												{
//													System.err.println(goldCount);
														li.add(goldDataFieldsName);
														if(goldDataFieldsName.equalsIgnoreCase("DOC ID"))
														{
															docIdPosition=goldCount;
														}
												}
												if(whilecount!=0)
												{
												goldDataFieldsName=goldDataFieldsName.replace("\"","").replace("\n","").replace("\r", "");
												goldDocumentNo=goldDataReading[docIdPosition].replace("\"","");
												extractedDocumentNo=extractedDataReading[docIdPosition].replace("\"","");
												 
//												 System.err.println("^^^^^^^"+goldDocumentNo+",,"+extractedDocumentNo);
												 if(goldDocumentNo.equals(extractedDocumentNo))
												 {
												int extractedCount=0;
												for (String extractedDataFieldsName : extractedDataReading) {
													extractedDataFieldsName=extractedDataFieldsName.replace("\"","").replace("\n","").replace("\r", "");
													
													if(goldCount==extractedCount)
													{
//														System.out.println("&&&&&&"+li.get(extractedCount));
//														System.out.println("---"+li.get(extractedCount));
														String fieldName =li.get(extractedCount).replace("\"","").replace("\n","").replace("\r", "");
//														System.out.println("********"+goldLine);
														
														if(goldDataFieldsName.equalsIgnoreCase(extractedDataFieldsName))
														{
															found="TRUE POSITIVE";
															totalTruePositive++;
														}
														else if((goldDataFieldsName!=null && goldDataFieldsName!="") && (extractedDataFieldsName.trim().isEmpty()))
														{
															found="FALSE NEGATIVE";
															totalFalseNegative++;
														}
														else if((extractedDataFieldsName!=null && extractedDataFieldsName!="") && (goldDataFieldsName.trim().isEmpty()))
														{
															found="TRUE NEGATIVE";
															totalTrueNegative++;
														}
														else if(!goldDataFieldsName.equalsIgnoreCase(extractedDataFieldsName))
														{
															found="FALSE POSITIVE";
															totalFalsePositive++;
														}
//														System.err.println("found  : "+found);
														batchNameLi.add(fileBatchName);
														docTypeLi.add(docType);
														docNoLi.add(goldDocumentNo);
														fieldNameLi.add(fieldName);
														expectedValLi.add(goldDataFieldsName);
														extractedValLi.add(extractedDataFieldsName);
														foundLi.add(found);
														
															/*
															 * Row row = sheet.createRow(rowNum++);
															 * 
															 * j=0;
															 * 
															 * row.createCell(j).setCellValue(batchName); j++;
															 * row.createCell(j).setCellValue(docType); j++;
															 * row.createCell(j).setCellValue(goldDocumentNo); j++;
															 * row.createCell(j).setCellValue(fieldName); j++;
															 * row.createCell(j).setCellValue(goldDataFieldsName); j++;
															 * row.createCell(j).setCellValue(extractedDataFieldsName);
															 * j++; row.createCell(j).setCellValue(found);
															 */ 
															/*
															 * System.out.println( "**********"+batchName+","+
															 * docType+","+ goldDocumentNo+","+
															 * li.get(extractedCount)+","+ extractedDataFieldsName+","+
															 * goldDataFieldsName);
															 */
													}
														
													
													
														
													extractedCount++;
												}
												 
												totalExtractedCount=extractedCount;
												 }
											  }
											} catch (Exception e) {
												e.printStackTrace();
											}

											goldCount++;
									}
										totalGoldCount=goldCount;
									
									
										whilecount++;
									}
									totalWhileCount=whilecount;
									
//									totalRows = whilecount*totalRows;
									
								}
							}
							}

						}
					}
					}
				}
				
				
				totalWhileCount=totalWhileCount-1;;
//				System.out.println("=="+totalFalseNegative+",,"+totalFalsePositive+",,"+totalTrueNegative+",,"+totalTruePositive);
//				System.out.println("=="+totalExtractedCount+",,"+totalGoldCount+",,"+totalWhileCount);
				
//				System.err.println("totalExtractedCount : ="+totalExtractedCount);
//				System.err.println("totalWhileCount : ="+totalWhileCount);
				
				totalRows=(totalExtractedCount*totalWhileCount);
//				System.err.println("totalRows : ="+totalRows);
				
				
				
				
//				------------------------------------------making excel sheet------------------------------------
				OutputStream fileOut = new FileOutputStream("files/Compared_Data.xlsx");

				Sheet sheet = workbook.createSheet("TemplateSheet");

				String[] columnss = { "Batch Name", "Doc Type", "Doc No", "Field Name", "Expected Value", "Extracted Value",
						"Found", };
				
				
				int rowNum = 2;
				Row row = sheet.createRow(rowNum);
				  
//				   sheet.addMergedRegion(new CellRangeAddress(1,1,1,4));
				  int j=0;
				  
				  row.createCell(j).setCellValue("Total False Positive "); j+=2;
				  sheet.addMergedRegion(new CellRangeAddress(2,2,0,1));
				  
				  row.createCell(j).setCellValue(totalFalsePositive); j+=2;
				  RegionUtil.setBorderTop(1, new CellRangeAddress(2,2,0,2), sheet,workbook);
				  RegionUtil.setBorderBottom(1, new CellRangeAddress(2,2,0,2), sheet,workbook);
				  RegionUtil.setBorderLeft(1, new CellRangeAddress(2,2,0,2), sheet,workbook);
				  RegionUtil.setBorderRight(1, new CellRangeAddress(2,2,0,2), sheet,workbook);
				  
				  row.createCell(j).setCellValue("Total False Negative "); j+=2;
				  sheet.addMergedRegion(new CellRangeAddress(2,2,4,5));
				  row.createCell(j).setCellValue(totalFalseNegative); j+=2;
				  
				  RegionUtil.setBorderTop(1, new CellRangeAddress(2,2,4,6), sheet,workbook);
				  RegionUtil.setBorderBottom(1, new CellRangeAddress(2,2,4,6), sheet,workbook);
				  RegionUtil.setBorderLeft(1, new CellRangeAddress(2,2,4,6), sheet,workbook);
				  RegionUtil.setBorderRight(1, new CellRangeAddress(2,2,4,6), sheet,workbook);
				 
				  row.createCell(j).setCellValue("Total True Positive "); j+=2;
				  sheet.addMergedRegion(new CellRangeAddress(2,2,8,9));
				  
				  row.createCell(j).setCellValue(totalTruePositive); j+=2;
				  
				  RegionUtil.setBorderTop(1, new CellRangeAddress(2,2,8,10), sheet,workbook);
				  RegionUtil.setBorderBottom(1, new CellRangeAddress(2,2,8,10), sheet,workbook);
				  RegionUtil.setBorderLeft(1, new CellRangeAddress(2,2,8,10), sheet,workbook);
				  RegionUtil.setBorderRight(1, new CellRangeAddress(2,2,8,10), sheet,workbook);
				 
				  int precisionToDivide =totalRows+totalFalsePositive;
				  int recallToDivide =totalRows+totalFalseNegative;
					 
				  try
				  {
				  double precision =0,recall =0;
				  double totalRowsDob =totalRows;
				  
				  double precisionToDivideDob =precisionToDivide;
				  double recallToDivideDob =recallToDivide;
				  precision =(totalRowsDob/precisionToDivideDob)*100;
				  
				  recall =(totalRowsDob/recallToDivideDob)*100;
				  
				  row.createCell(j).setCellValue("Precision :  "); j++;
				  row.createCell(j).setCellValue(precision); j+=2;
				  
				  RegionUtil.setBorderTop(1, new CellRangeAddress(2,2,12,13), sheet,workbook);
				  RegionUtil.setBorderBottom(1, new CellRangeAddress(2,2,12,13), sheet,workbook);
				  RegionUtil.setBorderLeft(1, new CellRangeAddress(2,2,12,13), sheet,workbook);
				  RegionUtil.setBorderRight(1, new CellRangeAddress(2,2,12,13), sheet,workbook);
				 
				  row.createCell(j).setCellValue("Recall : "); j++;
				  row.createCell(j).setCellValue(recall); j++;
				  
				  RegionUtil.setBorderTop(1, new CellRangeAddress(2,2,15,16), sheet,workbook);
				  RegionUtil.setBorderBottom(1, new CellRangeAddress(2,2,15,16), sheet,workbook);
				  RegionUtil.setBorderLeft(1, new CellRangeAddress(2,2,15,16), sheet,workbook);
				  RegionUtil.setBorderRight(1, new CellRangeAddress(2,2,15,16), sheet,workbook);
				 
				  }
				  catch(Exception e)  {e.printStackTrace();}
				  row.createCell(j).setCellValue(""); j++;
				  row.createCell(j).setCellValue("");
//				  RegionUtil.setBorderTop(1, new CellRangeAddress(0,2,1,2), sheet,workbook);
//				  sheet.addMergedRegion(new CellRangeAddress(0,2,1,2));
//	          	  String[] columnss = {"Document Name","Column Name", "Extracted Value","Expected Value","Found",}; 
				

				  rowNum +=2;

				Row headerRow = sheet.createRow(rowNum);
				
				for (int i = 0; i < columnss.length; i++) 
				{
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(columnss[i]);
					sheet.autoSizeColumn(i);
				}
				
				int forI=0;
				rowNum++;
				for(String batchNameStr :batchNameLi)
				{
					  row = sheet.createRow(rowNum++);
					  j=0;
					  String doctype =docTypeLi.get(forI);
					  String goldDocumentNo =docNoLi.get(forI);
					  String fieldName =fieldNameLi.get(forI);
					  String goldDataFieldsName =expectedValLi.get(forI);
					  String extractedDataFieldsName =extractedValLi.get(forI);
					  String found =foundLi.get(forI);
					  row.createCell(j).setCellValue(batchNameStr); j++;
					  row.createCell(j).setCellValue(doctype); j++;
					  row.createCell(j).setCellValue(goldDocumentNo); j++;
					  row.createCell(j).setCellValue(fieldName); j++;
					  row.createCell(j).setCellValue(goldDataFieldsName); j++;
					  row.createCell(j).setCellValue(extractedDataFieldsName); j++;
					  row.createCell(j).setCellValue(found);
					  forI++;
				}
 				  
				  workbook.write(fileOut);
				  fileOut.close();
			} catch (Exception e) {
			}
		}

}
