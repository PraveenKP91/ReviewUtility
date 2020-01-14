package net.javaguides.maven_web_project.ComapreCsvFileAndGenerateExcel;

import java.io.File;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.comparator.NameFileComparator;

import domains.Batch;
import util.AppPropertyReader;

public class Classification2 {
	public static void main(String[] args) {
		try {
			// folder locations read from property file
			String goldFileLocation = AppPropertyReader.getProperty("GOLD_FILE_LOCATION");
			String testFileLocation = AppPropertyReader.getProperty("TEST_FILE_LOCATION");

			// list of goldFiles
			File goldFileFolder = new File(goldFileLocation);
			File[] listOfGoldFiles = goldFileFolder.listFiles();
			Arrays.sort(listOfGoldFiles, NameFileComparator.NAME_COMPARATOR);

			// list of testFiles
			File testFileFolder = new File(testFileLocation);
			File[] listOfTestFiles = testFileFolder.listFiles();
			Arrays.sort(listOfTestFiles, NameFileComparator.NAME_COMPARATOR);

			for (File goldFileList : listOfGoldFiles) {
				String goldFileName = goldFileList.getName();
				for (File testFileList : listOfTestFiles) {
					String testFileName = testFileList.getName();
					File goldFile = new File(goldFileLocation + "/" + goldFileName);
					File processedfile = new File(testFileLocation + "/" + testFileName);
					compareFiles(goldFile, processedfile);
				}
			}

	 		 /*for(File goldFileList:listOfGoldFiles)
	 		 {
	 			 String goldFileName = goldFileList.getName();
	 			 // identifier should be loan file name
	 			String identifier = goldFileName.split("_")[0];
	 			 for(File testFileList:listOfTestFiles)
	 			 {
	 				 String testFileName = testFileList.getName();
	 				 if(testFileName.contains(identifier))
	 				 {
	 					 // folder where gold copy available
	 					File goldFile = new File(goldFileLocation+goldFileName);
	 					// folder where test file available
	 		 			File processedfile = new File(testFileLocation+testFileName);
	 		 			compareFiles(goldFile, processedfile);
	 				 }
	 			 }
	 		 }*/

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

		batchGold.equals(batchProcessed);

	}

}
