package domains;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Documents") 
public class Documents {
List<Document> Document;

@XmlElement(name="Document")
public List<Document> getDocument() {
	return Document;
}

public void setDocument(List<Document> document) {
	Document = document;
}

public void checkForInDoc1ButNotInDoc21(Documents a1,Documents a2)
{
	List<Document> d1=a1.getDocument();
	List<Document> d2=a2.getDocument();
	
//	d1.containsAll(d2); 
	int i=0,j=0;
	int countNoOfMatches=0;String matchesFound="";
	int countNoOfMisMatches=0;String mismatchesFoundIn="",mismatchesValueFound="";
	
	for(Document d:d1)
	{
		i++;
		String identifier=d.getIdentifier();
		/*
		private String Type;
		private String Description;
		private String Confidence;
		private String ConfidenceThreshold;
		private String Valid;
		private String Reviewed;
		private String ReviewedBy;
		private String ValidatedBy;
		private String ErrorMessage;
		private String DocumentDisplayInfo;
		private Pages Pages;
		private String UltiPagePdfFile;
		*/
		
		for(Document dToCompare : d2)
		{
			j++;
			String identifier1=dToCompare.getIdentifier();
			if(identifier.equals(identifier1))
			{
					System.out.println("----identifer---"+identifier);
					//System.out.println("  i : "+countNoOfMatches);
					countNoOfMatches++;matchesFound+="Identifier"+",";
				
				if(d.getType().equals(dToCompare.getType())){countNoOfMatches++;matchesFound+="Type"+",";}
				else {countNoOfMisMatches++;mismatchesValueFound+="Type : Old value ="+d.getType()+", New Value = "+dToCompare.getType()+"\n"+",";}
				 
				
				if(d.getDescription().equals(dToCompare.getDescription()))
				{
					//System.out.println("  i : "+countNoOfMatches);
					countNoOfMatches++;matchesFound+="Description"+",";
				} 
				else {countNoOfMisMatches++;mismatchesValueFound+="Description : Old value ="+d.getDescription()+", New Value = "+dToCompare.getDescription()+"\n"+",";}
				
				if(d.getConfidence().equals(dToCompare.getConfidence()))
				{
					//System.out.println("  i : "+countNoOfMatches);
					countNoOfMatches++;matchesFound+="Confidence"+",";
				}
				else {countNoOfMisMatches++;mismatchesValueFound+="Confidence : Old value ="+d.getConfidence()+", New Value = "+dToCompare.getConfidence()+"\n"+",";}
				
				if(d.getConfidenceThreshold().equals(dToCompare.getConfidenceThreshold()))
				{
					//System.out.println("  i : "+countNoOfMatches);
					countNoOfMatches++;matchesFound+="ConfidenceThreshold"+",";
				}
				else {countNoOfMisMatches++;mismatchesValueFound+="ConfidenceThreshold : Old value ="+d.getConfidenceThreshold()+", New Value = "+dToCompare.getConfidenceThreshold()+"\n"+",";}
				
				if(d.getValid().equals(dToCompare.getValid()))
				{
					//System.out.println("  i : "+countNoOfMatches);
					countNoOfMatches++;matchesFound+="Valid"+","; 
				}
				else {countNoOfMisMatches++;mismatchesValueFound+="Valid : Old value ="+d.getValid()+", New Value = "+dToCompare.getValid()+"\n"+",";}
				
				if(d.getReviewed().equals(dToCompare.getReviewed()))
				{
					//System.out.println("  i : "+countNoOfMatches);
					countNoOfMatches++;matchesFound+="Reviewed"+","; 
				}
				else {countNoOfMisMatches++;mismatchesValueFound+="Reviewed : Old value ="+d.getReviewed()+", New Value = "+dToCompare.getReviewed()+"\n"+",";}
				
				
				if(d.getReviewedBy().equals(dToCompare.getReviewedBy()))
				{
					//System.out.println("  i : "+countNoOfMatches);
					countNoOfMatches++;matchesFound+="ReviewedBy"+","; 
				}
				else {countNoOfMisMatches++;mismatchesValueFound+="ReviewedBy : Old value ="+d.getReviewedBy()+", New Value = "+dToCompare.getReviewedBy()+"\n"+",";}
				
				
				if(d.getValidatedBy().equals(dToCompare.getValidatedBy()))
				{
					//System.out.println("  i : "+countNoOfMatches);
					countNoOfMatches++;matchesFound+="ValidatedBy"+","; 
				}
				else {countNoOfMisMatches++;mismatchesValueFound+="ValidatedBy : Old value ="+d.getValidatedBy()+", New Value = "+dToCompare.getValidatedBy()+"\n"+",";}
				
				if(d.getErrorMessage().equals(dToCompare.getErrorMessage()))
				{
					//System.out.println("  i : "+countNoOfMatches);
					countNoOfMatches++;matchesFound+="ErrorMessage"+","; 
				}
				else {countNoOfMisMatches++;mismatchesValueFound+="ErrorMessage : Old value ="+d.getErrorMessage()+", New Value = "+dToCompare.getErrorMessage()+"\n"+",";}
				
				if(d.getDocumentDisplayInfo().equals(dToCompare.getDocumentDisplayInfo()))
				{
					//System.out.println("  i : "+countNoOfMatches);
					countNoOfMatches++;matchesFound+="DocumentDisplayInfo"+","; 
				}
				else {countNoOfMisMatches++;mismatchesValueFound+="DocumentDisplayInfo : Old value ="+d.getDocumentDisplayInfo()+", New Value = "+dToCompare.getDocumentDisplayInfo()+"\n"+",";}
				
				if(d.getMultiPagePdfFile().equals(dToCompare.getMultiPagePdfFile()))
				{
					//System.out.println("  i : "+countNoOfMatches);
					countNoOfMatches++;matchesFound+="MultiPagePdfFile"+","; 
				} 
				else {countNoOfMisMatches++;mismatchesValueFound+="MultiPagePdfFile : Old value ="+d.getMultiPagePdfFile()+", New Value = "+dToCompare.getMultiPagePdfFile()+"\n"+",";}
			
			
				
			Pages pages=d.getPages();
			Pages pagesToCompare=dToCompare.getPages();
			
			pages.equals(pages, pagesToCompare);
			
			
			}
		}
	}
	
	System.out.println("countNoOfMatches : "+countNoOfMatches);
//	System.out.println("matchesFound : "+matchesFound);
	System.out.println("--");
	System.out.println("countNoOfMisMatches : "+countNoOfMisMatches);
//	System.out.println("mismatchesFoundIn : "+mismatchesFoundIn);
	System.out.println("mismatchesFoundIn in values: "+mismatchesValueFound);
	
}
public void checkForInDoc1ButNotInDoc22(Documents a1,Documents a2)
{
	List<Document> d1=a1.getDocument();
	List<Document> d2=a2.getDocument();
	
	d1.retainAll(d2); 
	
	for(Document d:d1)
	{
		String identifier=d.getIdentifier();
		System.err.println("checkForInDoc1ButNotInDoc22 identifier : "+identifier);
		/*for(Document dToCompare : d2)
		{
			String identifier1=dToCompare.getIdentifier();
			
		}*/
	}
	
}
public void checkForInDoc1ButNotInDoc23(Documents a1,Documents a2)
{
	List<Document> d1=a1.getDocument();
	List<Document> d2=a2.getDocument();
	
	d1.removeAll(d2); 
	
	for(Document d:d1)
	{
		String identifier=d.getIdentifier();
		System.err.println("checkForInDoc1ButNotInDoc23 identifier : "+identifier);
		/*for(Document dToCompare : d2)
		{
			String identifier1=dToCompare.getIdentifier();
			
		}*/
	}
	
}
}
