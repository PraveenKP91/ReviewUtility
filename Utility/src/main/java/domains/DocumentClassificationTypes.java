package domains;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name="DocumentClassificationTypes")
public class DocumentClassificationTypes {
private String DocumentClassificationType;

@XmlElement(name="DocumentClassificationType") 
public String getDocumentClassificationType() {
	return DocumentClassificationType;
} 

public void setDocumentClassificationType(String documentClassificationType) {
	DocumentClassificationType = documentClassificationType;
}


public void  equals(DocumentClassificationTypes toCompare)
{
	int countNoOfMatches=0;String matchesFound="";
	int countNoOfMisMatches=0;String mismatchesFoundIn="",mismatchesValueFound="";
	
	try{
		if(this.DocumentClassificationType.equals(toCompare.getDocumentClassificationType())) 
			{countNoOfMatches++;matchesFound+="DocumentClassificationType"+",";}
		else {countNoOfMisMatches++;mismatchesValueFound+="DocumentClassificationType : Old value ="+this.DocumentClassificationType+", New Value = "+toCompare.getDocumentClassificationType()+"\n"+",";} 
		}
		catch(Exception e){countNoOfMisMatches++;mismatchesFoundIn+="DocumentClassificationType"+",";}
		
}

}
