package common;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import domains.BatchLevelFields;
import domains.Document;
import domains.DocumentClassificationTypes;
import domains.Documents;
import domains.Page;
import domains.SourceFiles;
import util.AppPropertyReader;

@XmlRootElement(name = "Batch")
public class Batch {

	private String BatchInstanceIdentifier;
	private String BatchClassIdentifier;
	private String BatchClassName;
	private String BatchClassDescription;
	private String BatchClassVersion;
	private String BatchName;
	private String BatchDescription;
	private String BatchPriority;
	private String BatchStatus;
	private String BatchReviewedBy;
	private String BatchValidatedBy;
	private String BatchCreationDate;
	private String BatchLocalPath;
	private String BatchSource;
	private String UNCFolderPath;
	private String ETextMode;
	private List<DocumentClassificationTypes> documentClassificationTypes;

	private Documents documents;
	private BatchLevelFields batchLevelFields;
	private SourceFiles sourceFiles;

	public Batch() {

	}

	public Batch(String batchInstanceIdentifier) {
		super();
		BatchInstanceIdentifier = batchInstanceIdentifier;
	}

	@XmlElement(name = "BatchInstanceIdentifier")
	public String getBatchInstanceIdentifier() {
		return BatchInstanceIdentifier;
	}

	public void setBatchInstanceIdentifier(String batchInstanceIdentifier) {
		BatchInstanceIdentifier = batchInstanceIdentifier;
	}

	@XmlElement(name = "BatchClassIdentifier")
	public String getBatchClassIdentifier() {
		return BatchClassIdentifier;
	}

	public void setBatchClassIdentifier(String batchClassIdentifier) {
		BatchClassIdentifier = batchClassIdentifier;
	}

	@XmlElement(name = "BatchClassName")
	public String getBatchClassName() {
		return BatchClassName;
	}

	public void setBatchClassName(String batchClassName) {
		BatchClassName = batchClassName;
	}

	@XmlElement(name = "BatchClassDescription")
	public String getBatchClassDescription() {
		return BatchClassDescription;
	}

	public void setBatchClassDescription(String batchClassDescription) {
		BatchClassDescription = batchClassDescription;
	}

	@XmlElement(name = "BatchClassVersion")
	public String getBatchClassVersion() {
		return BatchClassVersion;
	}

	public void setBatchClassVersion(String batchClassVersion) {
		BatchClassVersion = batchClassVersion;
	}

	@XmlElement(name = "BatchName")
	public String getBatchName() {
		return BatchName;
	}

	public void setBatchName(String batchName) {
		BatchName = batchName;
	}

	@XmlElement(name = "BatchDescription")
	public String getBatchDescription() {
		return BatchDescription;
	}

	public void setBatchDescription(String batchDescription) {
		BatchDescription = batchDescription;
	}

	@XmlElement(name = "BatchPriority")
	public String getBatchPriority() {
		return BatchPriority;
	}

	public void setBatchPriority(String batchPriority) {
		BatchPriority = batchPriority;
	}

	@XmlElement(name = "BatchStatus")
	public String getBatchStatus() {
		return BatchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		BatchStatus = batchStatus;
	}

	@XmlElement(name = "BatchReviewedBy")
	public String getBatchReviewedBy() {
		return BatchReviewedBy;
	}

	public void setBatchReviewedBy(String batchReviewedBy) {
		BatchReviewedBy = batchReviewedBy;
	}

	@XmlElement(name = "BatchValidatedBy")
	public String getBatchValidatedBy() {
		return BatchValidatedBy;
	}

	public void setBatchValidatedBy(String batchValidatedBy) {
		BatchValidatedBy = batchValidatedBy;
	}

	@XmlElement(name = "BatchCreationDate")
	public String getBatchCreationDate() {
		return BatchCreationDate;
	}

	public void setBatchCreationDate(String batchCreationDate) {
		BatchCreationDate = batchCreationDate;
	}

	@XmlElement(name = "BatchLocalPath")
	public String getBatchLocalPath() {
		return BatchLocalPath;
	}

	public void setBatchLocalPath(String batchLocalPath) {
		BatchLocalPath = batchLocalPath;
	}

	@XmlElement(name = "BatchSource")
	public String getBatchSource() {
		return BatchSource;
	}

	public void setBatchSource(String batchSource) {
		BatchSource = batchSource;
	}

	@XmlElement(name = "UNCFolderPath")
	public String getUNCFolderPath() {
		return UNCFolderPath;
	}

	public void setUNCFolderPath(String uNCFolderPath) {
		UNCFolderPath = uNCFolderPath;
	}

	@XmlElement(name = "ETextMode")
	public String getETextMode() {
		return ETextMode;
	}

	public void setETextMode(String eTextMode) {
		ETextMode = eTextMode;
	}

	@XmlElement(name = "DocumentClassificationTypes")
	public List<DocumentClassificationTypes> getDocumentClassificationTypes() {
		return documentClassificationTypes;
	}

	public void setDocumentClassificationTypes(List<DocumentClassificationTypes> documentClassificationTypes) {
		this.documentClassificationTypes = documentClassificationTypes;
	}

	@XmlElement(name = "Documents")
	public Documents getDocuments() {
		return documents;
	}

	public void setDocuments(Documents documents) {
		this.documents = documents;
	}

	@XmlElement(name = "BatchLevelFields")
	public BatchLevelFields getBatchLevelFields() {
		return batchLevelFields;
	}

	public void setBatchLevelFields(BatchLevelFields batchLevelFields) {
		this.batchLevelFields = batchLevelFields;
	}

	@XmlElement(name = "SourceFiles")
	public SourceFiles getSourceFiles() {
		return sourceFiles;
	}

	public void setSourceFiles(SourceFiles sourceFiles) {
		this.sourceFiles = sourceFiles;
	}

	public void equals(Batch batchFileToCompare) {
		HashMap<Integer, String> one = new LinkedHashMap<Integer, String>();
		HashMap<Integer, String> two = new LinkedHashMap<Integer, String>();
		HashMap<Integer, String> three = new LinkedHashMap<Integer, String>();
		HashMap<Integer, String> four = new LinkedHashMap<Integer, String>();
		HashMap<Integer, String> five = new LinkedHashMap<Integer, String>();

		String outputFile = AppPropertyReader.getProperty("OUTPUT_FILE_LOCATION_EXTRACTION");

		DateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		Date dateobj = new Date();

		List<Document> documentLi = documents.getDocument();
		List<Document> documentLiToCompare = batchFileToCompare.getDocuments().getDocument();

		try {
			Workbook workbook = new XSSFWorkbook();

			OutputStream fileOut = new FileOutputStream(
					outputFile + "/" + BatchInstanceIdentifier + "_Extraction_" + df.format(dateobj) + ".xlsx");

			Sheet sheet = workbook.createSheet("TemplateSheet");

			String[] columnss = { "Batch Name", "Doc Type", "Doc No", "Field Name", "Expected Value", "Extracted Value","Found", };

			Row headerRow = sheet.createRow(5);

			for (int i = 0; i < columnss.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columnss[i]);
				sheet.autoSizeColumn(i);
			}
			int rowNum = 6;
			int totalRows = 1;
			Row row = sheet.createRow(rowNum);

			for (Document doc : documentLi) {
				List<Page> pageLi = doc.getPages().getPage();
				for (Page page : pageLi) {
					row = sheet.createRow(rowNum++);
					totalRows++;
				}
			}
			rowNum = 6;
			int key = 1;
			for (Document doc : documentLi) {
				String documentName = doc.getType();
				List<Page> pageLi = doc.getPages().getPage();

				for (Page page : pageLi) {
					String pageNo = page.getIdentifier().replace("PG", "");
					one.put(key, documentName);
					two.put(key, pageNo);

					row = sheet.getRow(rowNum++);
					row.createCell(0).setCellValue(documentName);
					row.createCell(1).setCellValue(pageNo);
					System.out.println("---" + pageNo + "," + documentName);
					key++;
				}
			}
			rowNum = 6;
			key = 1;
			for (Document doc : documentLiToCompare) {
				String documentName = doc.getType();
				List<Page> pageLi = doc.getPages().getPage();

				for (Page page : pageLi) {
					String pageNo = page.getIdentifier().replace("PG", "");
					three.put(key, documentName);
					four.put(key, pageNo);
					row = sheet.getRow(rowNum++);
					row.createCell(2).setCellValue(documentName);
					row.createCell(3).setCellValue(pageNo);
					key++;
				}
			}
			rowNum = 6;
			String found = "";
			int totalTruePositive = 0, totalTrueNegative = 0, totalFalsePositive = 0, totalFalseNegative = 0;
			for (Map.Entry<Integer, String> entry1 : one.entrySet()) {
				int hkey = entry1.getKey();
				String value1 = entry1.getValue();
				String value2 = two.get(hkey);
				String value3 = three.get(hkey);
				String value4 = four.get(hkey);

				row = sheet.getRow(rowNum++);
				if (value1.equalsIgnoreCase(value3) && value2.equalsIgnoreCase(value4)) {
					found = "TRUE POSITIVE";
					totalTruePositive++;
				} else {
					found = "FALSE POSITIVE";
					totalFalsePositive++;
				}
				row.createCell(4).setCellValue(found);
			}

			int j = 0;
			row = sheet.createRow(2);
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

			Row row2 = workbook.getSheetAt(0).getRow(5);
			for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
				workbook.getSheetAt(0).autoSizeColumn(colNum);
			}
			workbook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
