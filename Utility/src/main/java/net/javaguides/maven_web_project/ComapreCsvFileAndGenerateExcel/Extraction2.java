package net.javaguides.maven_web_project.ComapreCsvFileAndGenerateExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import common.Batch;
import domains.Document;
import domains.DocumentLevelField;
import domains.DocumentLevelFields;
import util.AppPropertyReader;

public class Extraction2 {
	public static void main(String[] args) throws IOException {
		try {
			// folder locations read from property file
			String goldFileLocation = AppPropertyReader.getProperty("GOLD_FILE_LOCATION_EX");
			String testFileLocation = AppPropertyReader.getProperty("TEST_FILE_LOCATION_EX");

			String unzipDestDirGold = goldFileLocation;
			String unzipDestDirTest = testFileLocation;

			// list of goldFiles
			File goldFileFolder = new File(goldFileLocation);
			File[] listOfGoldFiles = goldFileFolder
					.listFiles((dir, name) -> name.toLowerCase().contains("post_validate"));
			Arrays.sort(listOfGoldFiles, NameFileComparator.NAME_COMPARATOR.reversed());

			// list of testFiles
			File testFileFolder = new File(testFileLocation);
			File[] listOfTestFiles = testFileFolder
					.listFiles((dir, name) -> name.toLowerCase().contains("pre_validate"));
			Arrays.sort(listOfTestFiles, NameFileComparator.NAME_COMPARATOR.reversed());

			for (File goldFileList : listOfGoldFiles) {
				String goldFileName = goldFileList.getName();
				// if it is a zip file unzip it on same location
				if (goldFileName.toLowerCase().endsWith("zip")) {
					goldFileName = goldFileName.substring(0, goldFileName.lastIndexOf("_"));
					goldFileName = goldFileName.substring(0, goldFileName.lastIndexOf("_"));
					unzip(goldFileList.getAbsolutePath(), unzipDestDirGold, goldFileName);
				}
				for (File testFileList : listOfTestFiles) {
					String testFileName = testFileList.getName();
					// if it is a zip file unzip it on same location
					if (testFileName.toLowerCase().endsWith("zip")) {
						testFileName = testFileName.substring(0, testFileName.lastIndexOf("_"));
						testFileName = testFileName.substring(0, testFileName.lastIndexOf("_"));
						unzip(testFileList.getAbsolutePath(), unzipDestDirTest, testFileName);
					}
					File goldFile = new File(goldFileLocation + "/" + goldFileName);
					File processedfile = new File(testFileLocation + "/" + testFileName);
					compareFiles(goldFile, processedfile);
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private static void unzip(String zipFilePath, String destDir, String fileName) {
		File dir = new File(destDir);
		// create output directory if it doesn't exist
		if (!dir.exists())
			dir.mkdirs();
		FileInputStream fis;
		// buffer for read and write data to file
		byte[] buffer = new byte[1024];
		try {
			fis = new FileInputStream(zipFilePath);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				// String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				System.out.println("Unzipping to " + newFile.getAbsolutePath());
				// create directories for sub directories in zip
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				// close this ZipEntry
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			// close last ZipEntry
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void compareFiles(File goldFile, File processedfile) throws JAXBException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Batch.class);
			JAXBContext jaxbContext2 = JAXBContext.newInstance(Batch.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Unmarshaller jaxbUnmarshaller2 = jaxbContext2.createUnmarshaller();
			Batch batchGold = (Batch) jaxbUnmarshaller.unmarshal(goldFile);
			Batch batchProcessed = (Batch) jaxbUnmarshaller2.unmarshal(processedfile);
			String batchNameGold = batchGold.getBatchName();
			String batchNameProcessed = batchProcessed.getBatchName();

			if (batchNameGold.equalsIgnoreCase(batchNameProcessed)) {

				String outputFile = AppPropertyReader.getProperty("OUTPUT_FILE_LOCATION_EXTRACTION");
				String batchInstanceIdentifier = batchGold.getBatchInstanceIdentifier();

				DateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
				Date dateobj = new Date();

				Workbook workbook = new XSSFWorkbook();

				OutputStream fileOut = new FileOutputStream(
						outputFile + "/" + batchInstanceIdentifier + "_Extraction_" + df.format(dateobj) + ".xlsx");

				Sheet sheet = workbook.createSheet("TemplateSheet");

				String[] columnss = { "Batch Name", "Doc Type", "Doc No", "Field Name", "Expected Value",
						"Extracted Value", "Found", };

				int headerRowNum = 5, totalRowsNum = 6, precisionRowNum = 2;

				Row headerRow = sheet.createRow(headerRowNum);

				for (int i = 0; i < columnss.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(columnss[i]);
					sheet.autoSizeColumn(i);
				}
				int rowNum = 6;
				int totalRows = 1;

				int totalTruePositive = 0;
				int totalFalseNegative = 0;
				int totalTrueNegative = 0;
				int totalFalsePositive = 0;

				for (int i = 0; i < batchGold.getDocuments().getDocument().size(); i++) {
					Document docGold = batchGold.getDocuments().getDocument().get(i);
					Document docTest = batchProcessed.getDocuments().getDocument().get(i);

					String docType = docGold.getType();
					String docNo = docGold.getIdentifier();

					if (docGold.getDocumentLevelFields() != null)
						for (int j = 0; j < docGold.getDocumentLevelFields().size(); j++) {
							DocumentLevelFields doc2 = docGold.getDocumentLevelFields().get(j);
							DocumentLevelFields doc22 = docTest.getDocumentLevelFields().get(j);

							if (null != doc2.getDocumentLevelField())
								for (int k = 0; k < doc2.getDocumentLevelField().size(); k++) {
									DocumentLevelField doc3 = doc2.getDocumentLevelField().get(k);
									DocumentLevelField doc33 = doc22.getDocumentLevelField().get(k);

									if (null != doc3.getName()) {

										int cellNo = 0;
										String fieldNameGold = doc3.getName();
										String fieldNameExtracted = doc33.getName();
										String found = "";
										Row row = sheet.createRow(rowNum++);
										System.out.println(rowNum);
										if (fieldNameGold.equalsIgnoreCase(fieldNameExtracted)) {
											found = "TRUE POSITIVE";
											totalTruePositive++;
										} else if ((fieldNameGold != null && fieldNameGold != "")
												&& (fieldNameExtracted.trim().isEmpty())) {
											found = "FALSE NEGATIVE";
											totalFalseNegative++;
										} else if ((fieldNameExtracted != null && fieldNameExtracted != "")
												&& (fieldNameGold.trim().isEmpty())) {
											found = "TRUE NEGATIVE";
											totalTrueNegative++;
										} else if (!fieldNameGold.equalsIgnoreCase(fieldNameExtracted)) {
											found = "FALSE POSITIVE";
											totalFalsePositive++;
										}

										row.createCell(cellNo).setCellValue(batchInstanceIdentifier);
										cellNo++;
										row.createCell(cellNo).setCellValue(docType);
										cellNo++;
										row.createCell(cellNo).setCellValue(docNo);
										cellNo++;
										row.createCell(cellNo).setCellValue(fieldNameGold);
										cellNo++;
										row.createCell(cellNo).setCellValue(doc3.getValue());
										cellNo++;
										row.createCell(cellNo).setCellValue(doc33.getValue());
										cellNo++;
										row.createCell(cellNo).setCellValue(found);
										totalRows++;

									}
								}
						}

				}

				int j = 0;
				Row row = sheet.createRow(precisionRowNum);
				row.createCell(j).setCellValue("Total False Positive ");
				j += 2;
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));

				row.createCell(j).setCellValue(totalFalsePositive);
				j += 2;
				RegionUtil.setBorderTop(1, new CellRangeAddress(2, 2, 0, 2), sheet, workbook);
				RegionUtil.setBorderBottom(1, new CellRangeAddress(2, 2, 0, 2), sheet, workbook);
				RegionUtil.setBorderLeft(1, new CellRangeAddress(2, 2, 0, 2), sheet, workbook);
				RegionUtil.setBorderRight(1, new CellRangeAddress(2, 2, 0, 2), sheet, workbook);

				row.createCell(j).setCellValue("Total False Negative ");
				j += 2;
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 5));
				row.createCell(j).setCellValue(totalFalseNegative);
				j += 2;

				RegionUtil.setBorderTop(1, new CellRangeAddress(2, 2, 4, 6), sheet, workbook);
				RegionUtil.setBorderBottom(1, new CellRangeAddress(2, 2, 4, 6), sheet, workbook);
				RegionUtil.setBorderLeft(1, new CellRangeAddress(2, 2, 4, 6), sheet, workbook);
				RegionUtil.setBorderRight(1, new CellRangeAddress(2, 2, 4, 6), sheet, workbook);

				row.createCell(j).setCellValue("Total True Positive ");
				j += 2;
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 8, 9));

				row.createCell(j).setCellValue(totalTruePositive);
				j += 2;

				RegionUtil.setBorderTop(1, new CellRangeAddress(2, 2, 8, 10), sheet, workbook);
				RegionUtil.setBorderBottom(1, new CellRangeAddress(2, 2, 8, 10), sheet, workbook);
				RegionUtil.setBorderLeft(1, new CellRangeAddress(2, 2, 8, 10), sheet, workbook);
				RegionUtil.setBorderRight(1, new CellRangeAddress(2, 2, 8, 10), sheet, workbook);

				int precisionToDivide = totalRows + totalFalsePositive;
				int recallToDivide = totalRows + totalFalseNegative;

				double precision = 0, recall = 0;
				double totalRowsDob = totalRows;

				double precisionToDivideDob = precisionToDivide;
				double recallToDivideDob = recallToDivide;
				precision = (totalRowsDob / precisionToDivideDob) * 100;

				recall = (totalRowsDob / recallToDivideDob) * 100;

				row.createCell(j).setCellValue("Precision :  ");
				j++;
				row.createCell(j).setCellValue(precision);
				j += 2;

				RegionUtil.setBorderTop(1, new CellRangeAddress(2, 2, 12, 13), sheet, workbook);
				RegionUtil.setBorderBottom(1, new CellRangeAddress(2, 2, 12, 13), sheet, workbook);
				RegionUtil.setBorderLeft(1, new CellRangeAddress(2, 2, 12, 13), sheet, workbook);
				RegionUtil.setBorderRight(1, new CellRangeAddress(2, 2, 12, 13), sheet, workbook);

				row.createCell(j).setCellValue("Recall : ");
				j++;
				row.createCell(j).setCellValue(recall);
				j++;

				RegionUtil.setBorderTop(1, new CellRangeAddress(2, 2, 15, 16), sheet, workbook);
				RegionUtil.setBorderBottom(1, new CellRangeAddress(2, 2, 15, 16), sheet, workbook);
				RegionUtil.setBorderLeft(1, new CellRangeAddress(2, 2, 15, 16), sheet, workbook);
				RegionUtil.setBorderRight(1, new CellRangeAddress(2, 2, 15, 16), sheet, workbook);

			
				workbook.write(fileOut);
				fileOut.close();
				
			}
			
			 

		} catch (Exception e) {
			System.err.println("Exception : " + e);
		}
	}

}
