package net.javaguides.maven_web_project.ComapreCsvFileAndGenerateExcel;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import domains.Batch;

public class Classification {
	public static void main(String[] args)  {
	     try  
	     {
	            File goldFile = new File("files/BI268_post_batch.xml");
	 			File processedfile = new File("files/BI268_pre_batch.xml");
	 			compareFiles(goldFile, processedfile);
	     } 
	     catch (JAXBException e) 
	     {
	    	 e.printStackTrace(); 
	     }
	}  
	public static void  compareFiles(File goldFile,File processedfile) throws JAXBException
	{
		JAXBContext jaxbContext = JAXBContext.newInstance(Batch.class);  
	 	JAXBContext jaxbContext2 = JAXBContext.newInstance(Batch.class);  
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller(); 
        Unmarshaller jaxbUnmarshaller2 = jaxbContext2.createUnmarshaller(); 
        Batch batchGold= (Batch) jaxbUnmarshaller.unmarshal(goldFile);
              
        Batch batchProcessed= (Batch) jaxbUnmarshaller2.unmarshal(processedfile);
        
        String batchInstanceIdentifierGold=batchGold.getBatchInstanceIdentifier();
        String batchInstanceIdentifierProcessed=batchProcessed.getBatchInstanceIdentifier();
       
        if(batchInstanceIdentifierGold.equals(batchInstanceIdentifierProcessed))
        {
        	batchGold.equals(batchProcessed);
        } 
        else
        {
        	  
        }
	}
	
}
