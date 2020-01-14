package net.javaguides.maven_web_project.ComapreCsvFileAndGenerateExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.comparator.NameFileComparator;

import domains.Batch;
import util.AppPropertyReader;

public class Classification3 {
	public static void main(String[] args) throws IOException {
		try {
			// folder locations read from property file
			String goldFileLocation = AppPropertyReader.getProperty("GOLD_FILE_LOCATION");
			String testFileLocation = AppPropertyReader.getProperty("TEST_FILE_LOCATION");
			
			String unzipDestDirGold = goldFileLocation;
			String unzipDestDirTest = testFileLocation;

			// list of goldFiles
			File goldFileFolder = new File(goldFileLocation);
			File[] listOfGoldFiles = goldFileFolder.listFiles((dir, name) -> name.toLowerCase().contains("post_review"));
			Arrays.sort(listOfGoldFiles, NameFileComparator.NAME_COMPARATOR.reversed());

			// list of testFiles
			File testFileFolder = new File(testFileLocation);
			File[] listOfTestFiles = testFileFolder.listFiles((dir, name) -> name.toLowerCase().contains("pre_review"));
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

	public static void compareFiles(File goldFile, File processedfile) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Batch.class);
		JAXBContext jaxbContext2 = JAXBContext.newInstance(Batch.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Unmarshaller jaxbUnmarshaller2 = jaxbContext2.createUnmarshaller();
		Batch batchGold = (Batch) jaxbUnmarshaller.unmarshal(goldFile);
		Batch batchProcessed = (Batch) jaxbUnmarshaller2.unmarshal(processedfile);
        String batchNameGold=batchGold.getBatchName();
        String batchNameProcessed=batchProcessed.getBatchName();
        
		if (batchNameGold.equalsIgnoreCase(batchNameProcessed)) {
			batchGold.equals(batchProcessed);
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

}
