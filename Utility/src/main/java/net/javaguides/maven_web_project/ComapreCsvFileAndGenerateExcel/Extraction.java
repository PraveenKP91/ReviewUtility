package net.javaguides.maven_web_project.ComapreCsvFileAndGenerateExcel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.comparator.NameFileComparator ;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Extraction {
	public static void main(String[] args) throws FileNotFoundException{
		String goldLine ="",extractedLine ="";
		String goldDocumentNo="",extractedDocumentNo="";
		String deleimeterForFileNameSplit="-",found="";
		
		ArrayList<String> batchNameLi =new ArrayList<String>();
		ArrayList<String> batchNoLi =new ArrayList<String>();
		ArrayList<String> docTypeLi =new ArrayList<String>();
		ArrayList<String> docNoLi =new ArrayList<String>();
		ArrayList<String> fieldNameLi =new ArrayList<String>();
		ArrayList<String> fieldNameLi1 =new ArrayList<String>();
		ArrayList<String> expectedValLi =new ArrayList<String>();
		ArrayList<String> extractedValLi =new ArrayList<String>();
		ArrayList<String> foundLi =new ArrayList<String>();
		
		int docIdPosition=0;
		try
		{
		File extractedBatchInstanceFolder = new File("files/extractedFiles");
		File[] extfactedListOfBatchInstance = extractedBatchInstanceFolder.listFiles();

		File goldFolder = new File("files/goldFiles");
		File[] listOfFiles = goldFolder.listFiles();
		Arrays.sort(listOfFiles, NameFileComparator.NAME_COMPARATOR);
		int totaldoccount=0;
		String concat="";
		String jl="";
		ArrayList<String> aasd=new ArrayList<String>();
		for(File f :listOfFiles)
		{
			String fname =f.getName();
//			System.err.println("--"+fname);
//			if(fname.equals("Loan_Application-Flood_Certificates.csv") || fname.equals("Loan_Application-FHA_Connections.csv") ) {
			 BufferedReader	goldBr = new BufferedReader(new FileReader(f));	
			 int yh=0;int docCoun =0;
			 String filesNames[]=fname.split(deleimeterForFileNameSplit);
			 String docType=filesNames[1].split("\\.")[0];
			 while((goldLine=goldBr.readLine())!=null)
			 {
				 String k[] =goldLine.split("\",\"");
				 if(yh==0)
				 {
					 for(String l:k)
					 {
						 if(!l.equals(""))
						 {
							 l=l.replace("\"","");
//							 System.out.println(docType+"@"+l);
						 aasd.add(docType+"@"+l);
						 }
					 }
				 }
				 if(yh!=0)
				 {
					 docCoun++;
				 }
				 yh++;
			 }
			 
			 totaldoccount+=docCoun;
			 concat+=docType+"#"+docCoun+"@";
//			 System.out.println("in fname : "+docType+"=="+docCoun);
//			}
		}
		String w[]=concat.split("@");
		for(String e :w)
		{
			String r[]=e.split("#");
			String docuTy = r[0];
			int countd=Integer.parseInt(r[1]);
//			System.out.println("docuTy , , "+docuTy+",,"+countd);
//			System.out.println("countd , , "+countd); 
			for(int i=1;i<=countd;i++)
			{
//				System.err.println("docuTy , , "+docuTy+",,"+countd);
				for(String a : aasd)
				{
//					System.out.println("a : "+a);
					String g[] =a.split("@");
					String doct =g[0];
					String fieldname =g[1];
					if(docuTy.equalsIgnoreCase(doct))
					{
						fieldNameLi1.add(fieldname);
					}
//					System.out.println(doct+" : "+fieldname);
					
//					if(aSplit[0].equalsIgnoreCase(docuTy))
//					System.out.println("a : "+aSplit[1]);
				}
			}
		}
		System.err.println("fieldNameLi1 : "+fieldNameLi1.size());
		int batchInsCount=1;
		int batchInstanceCount =0;
		for(File batchInstance :extfactedListOfBatchInstance)
		{
//			System.out.println("$ "+batchInstanceCount);
		String fileBatchName =batchInstance.getName();
		batchNoLi.add(fileBatchName);
//		System.out.println("batchInsCount : "+batchInsCount+","+fileBatchName);
		File extractedFilesFolder = new File(batchInstance.getPath());
		File[] extfactedListOfFiles = extractedFilesFolder.listFiles();
		Arrays.sort(extfactedListOfFiles, NameFileComparator.NAME_COMPARATOR);
		int batchInsFilesCount=1;
		for (File extractedFile : extfactedListOfFiles) 
		{
//			System.out.println("batchInsFilesCount : "+batchInsFilesCount+",,"+extractedFile.getName());
			String extractedFileName =extractedFile.getName().replace("-"+fileBatchName,"");
			int goldCount=0;
			
			for(File goldFiles :listOfFiles)
			{
				
				if(goldFiles.getName().equalsIgnoreCase(extractedFileName))
				{
//					System.out.println("      : "+",,"+goldFiles.getName());
				
			 BufferedReader	goldBr = new BufferedReader(new FileReader(goldFiles));	
			 BufferedReader	extractedBr = new BufferedReader(new FileReader(extractedFile));
			 int whilecount=0;
//			 if(goldFiles.getName().equalsIgnoreCase("Loan_Application-Flood_Certificates.csv") ||goldFiles.getName().equalsIgnoreCase("Loan_Application-FHA_Connections.csv"))
//			 {
//				 System.out.println("jhfjdhjfhdjf : "+",,"+goldFiles.getName());
			 while((goldLine=goldBr.readLine())!=null)
			 {
				 extractedLine=extractedBr.readLine();
				 String[] goldDataReading = goldLine.split("\",\"");
				 String[] extractedDataReading = extractedLine.split("\",\"");
				 int goldCountNonZero=0;
				 for(String goldDataFieldsName :goldDataReading)
				 {
					 goldDataFieldsName=goldDataFieldsName.replace("\"","");
					
					 if(whilecount==0)
					 {
//								fieldNameLi1.add(goldDataFieldsName);
								
								if(goldDataFieldsName.equalsIgnoreCase("DOC ID"))
								{
									docIdPosition=goldCount;
								}
								
								 goldDocumentNo=goldDataReading[docIdPosition].replace("\"","");
								 extractedDocumentNo=extractedDataReading[docIdPosition].replace("\"","");
								 int extC=0;
								 if(goldDocumentNo.equalsIgnoreCase(extractedDocumentNo))
								 {
								 for(String extractedDataFieldsName :extractedDataReading)
								 {
									 
									 extractedDataFieldsName=extractedDataFieldsName.replace("\"","");
//									 System.out.println(extractedDataFieldsName);
									 
									 extC++;
								 }
								 }
					 }
					 if(whilecount!=0)
					 {
					 
					 goldDocumentNo=goldDataReading[docIdPosition].replace("\"","");
					 extractedDocumentNo=extractedDataReading[docIdPosition].replace("\"","");
						
//					 System.err.println(goldDocumentNo+""+extractedDocumentNo);
					 if(goldDocumentNo.equalsIgnoreCase(extractedDocumentNo))
					 {
						 int extractedCount=0;
//						 System.err.println("File "+extractedFileName);
						 for(String extractedDataFieldsName :extractedDataReading)
						 {
							 extractedDataFieldsName=extractedDataFieldsName.replace("\"","");
							 if(goldCountNonZero==extractedCount)
							 {
								 String filesNames[]=extractedFileName.split(deleimeterForFileNameSplit);
								 String docType=filesNames[1].split("\\.")[0];
								 
									batchNameLi.add(fileBatchName);
									docTypeLi.add(docType);
									docNoLi.add(goldDocumentNo);
									fieldNameLi.add(fieldNameLi1.get(extractedCount));
									expectedValLi.add(goldDataFieldsName);
									extractedValLi.add(extractedDataFieldsName);
									foundLi.add(found);
//									if(fieldNameLi1.get(extractedCount).equals("DOC ID"))
//									System.err.println(goldDocumentNo+",,"+extractedCount+",,"+docType+"@ " +fieldNameLi1.get(extractedCount));
//								    System.out.println(fileBatchName+",,"+docType+","+fieldNameLi.get(extractedCount)+","+goldDataFieldsName+",,,,,"+extractedDataFieldsName+",,"+found);
							 }

							 extractedCount++;
						 }
					 }
					 goldCountNonZero++;
					 }
					 goldCount++;
				 }
				 whilecount++;
			 }
//			}
			
			}
			
			batchInsFilesCount++;
		}
		batchInsCount++;
		}
		batchInstanceCount++;
		}
		
//		------------------------------------------making excel sheet------------------------------------
		
//		System.err.println(batchNoLi.size());
		int headerRowNum =5,totalRowsNum=6,precisionRowNum=2;
		for(String batchNo:batchNoLi)
		{
		int totalTruePositive =0,totalTrueNegative=0,totalFalsePositive =0,totalFalseNegative=0;
			
		Workbook workbook = new XSSFWorkbook();
		
		OutputStream fileOut = new FileOutputStream("files/Compared_Data_"+batchNo+".xlsx");

		Sheet sheet = workbook.createSheet("TemplateSheet");

		String[] columnss = { "Batch Name", "Doc Type", "Doc No", "Field Name", "Expected Value", "Extracted Value","Found", };
		
		
		int j=0;
		
		Row headerRow = sheet.createRow(headerRowNum);
		
		for (int i = 0; i < columnss.length; i++) 
		{
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columnss[i]);
			sheet.autoSizeColumn(i);
		}
		
		int forI=0;
		
		int totalRows=6;
		totalRowsNum=6;
		int insideFor=0;
		for(String batchNameStr :batchNameLi)
		{
		
//			  if(batchNameStr.equalsIgnoreCase(batchNo))
			  j=0;
			  String doctype =docTypeLi.get(forI);
			  String goldDocumentNo1 =docNoLi.get(forI);
			  
			  String goldDataFieldsName =expectedValLi.get(forI);
			  String extractedDataFieldsName =extractedValLi.get(forI);
			  String found1 ="";//foundLi.get(forI);
			  if(batchNo==batchNameStr)
			  {
				  String fieldName =fieldNameLi1.get(insideFor);
//				  System.out.println(": : : : "+insideFor+",,");
//				  System.out.println("totalRowsNum : "+totalRowsNum);
				  Row row = sheet.createRow(totalRowsNum++);
			  if(goldDataFieldsName.equalsIgnoreCase(extractedDataFieldsName))
				{
					found1="TRUE POSITIVE";
					totalTruePositive++;
				}
				else if((goldDataFieldsName!=null && goldDataFieldsName!="") && (extractedDataFieldsName.trim().isEmpty()))
				{
					found1="FALSE NEGATIVE";
					totalFalseNegative++;
				}
				else if((extractedDataFieldsName!=null && extractedDataFieldsName!="") && (goldDataFieldsName.trim().isEmpty()))
				{
					found1="TRUE NEGATIVE";
					totalTrueNegative++;
				}
				else if(!goldDataFieldsName.equalsIgnoreCase(extractedDataFieldsName))
				{
					found1="FALSE POSITIVE";
					totalFalsePositive++;
				}
			   
//			  System.out.println(batchNameStr+","+batchNo+","+goldDataFieldsName+","+extractedDataFieldsName);
//			  System.out.println(",,"+fieldName);
			  
			  row.createCell(j).setCellValue(batchNameStr); j++;
			  row.createCell(j).setCellValue(doctype); j++;
			  row.createCell(j).setCellValue(goldDocumentNo1); j++;
			  row.createCell(j).setCellValue(fieldName); j++;
			  row.createCell(j).setCellValue(goldDataFieldsName); j++;
			  row.createCell(j).setCellValue(extractedDataFieldsName); j++;
			  row.createCell(j).setCellValue(found1);
			  insideFor++;
			  totalRows++;}
			  forI++;
			  
		}
//		System.out.println("UJJ "+uj);
//			int totalRows=forI;

		  j=0;
		  Row row = sheet.createRow(precisionRowNum);
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
//		  row.createCell(j).setCellValue(""); j++;
//		  row.createCell(j).setCellValue("");
		
		  for(int i=0;i<=6;i++)
		  {
		  sheet.autoSizeColumn(i);
		  }
				/*
				 * File directory = new File("files/completedBatch"); if (! directory.exists())
				 * { directory.mkdir(); // If you require it to make the entire directory path
				 * including parents, // use directory.mkdirs(); here instead. }
				 */
		    
		  Files.move(Paths.get("files/extractedFiles/"+batchNo),Paths.get("files/completedBatch/"+batchNo)); 
			  
		  workbook.write(fileOut);
		  fileOut.close();
		
		
		}
		
		
		}
		catch(Exception e) {e.printStackTrace();}
		
		
}
}
