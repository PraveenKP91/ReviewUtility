package domains;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Document") 
public class Document {
	
	private String Identifier;
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
	private String MultiPagePdfFile;
	private List<DocumentLevelFields> documentLevelFields;
	
	@XmlElement(name="Identifier")
	public String getIdentifier() {
		return Identifier;
	}
	public void setIdentifier(String identifier) {
		Identifier = identifier;
	}
	
	@XmlElement(name="Type")
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	@XmlElement(name="Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	@XmlElement(name="Confidence")
	public String getConfidence() {
		return Confidence;
	}
	public void setConfidence(String confidence) {
		Confidence = confidence;
	}
	
	@XmlElement(name="ConfidenceThreshold")
	public String getConfidenceThreshold() {
		return ConfidenceThreshold;
	}
	public void setConfidenceThreshold(String confidenceThreshold) {
		ConfidenceThreshold = confidenceThreshold;
	}
	
	@XmlElement(name="Valid")
	public String getValid() {
		return Valid;
	}
	public void setValid(String valid) {
		Valid = valid;
	}
	
	@XmlElement(name="Reviewed")
	public String getReviewed() {
		return Reviewed;
	}
	public void setReviewed(String reviewed) {
		Reviewed = reviewed;
	}
	
	@XmlElement(name="ReviewedBy")
	public String getReviewedBy() {
		return ReviewedBy;
	}
	public void setReviewedBy(String reviewedBy) {
		ReviewedBy = reviewedBy;
	}
	
	@XmlElement(name="ValidatedBy")
	public String getValidatedBy() {
		return ValidatedBy;
	}
	public void setValidatedBy(String validatedBy) {
		ValidatedBy = validatedBy;
	}
	
	@XmlElement(name="ErrorMessage")
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	@XmlElement(name="DocumentDisplayInfo")
	public String getDocumentDisplayInfo() {
		return DocumentDisplayInfo;
	}
	public void setDocumentDisplayInfo(String documentDisplayInfo) {
		DocumentDisplayInfo = documentDisplayInfo;
	}
	
	@XmlElement(name="Pages")
	public Pages getPages() {
		return Pages;
	}
	public void setPages(Pages pages) {
		Pages = pages;
	}
	
	@XmlElement(name="MultiPagePdfFile")
	public String getMultiPagePdfFile() {
		return MultiPagePdfFile;
	}
	public void setMultiPagePdfFile(String MultiPagePdfFile) {
		this.MultiPagePdfFile = MultiPagePdfFile;
	}
	
	@XmlElement (name = "DocumentLevelFields")
	public List<DocumentLevelFields> getDocumentLevelFields() {
		return documentLevelFields;
	}
	public void setDocumentLevelFields(List<DocumentLevelFields> documentLevelFields) {
		this.documentLevelFields = documentLevelFields;
	}
	public void  equalsD(Document toCompare)
	{
		int countNoOfMatches=0;String matchesFound="\n";
		int countNoOfMisMatches=0;String mismatchesFoundIn="\n",mismatchesValueFound="\n";
		
		/*private String Identifier;
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
		
		System.out.println("this.Identifier :   : "+this.Identifier);
		System.out.println("toCompare.Identifier :   : "+toCompare.Identifier) ; 
		 
		/*try{
			if(this.Identifier.equals(toCompare.Identifier)) 
				{countNoOfMatches++;matchesFound+="Name"+",";}
			else {countNoOfMisMatches++;mismatchesValueFound+="Name : Old value ="+this.Name+", New Value = "+toCompare.getName()+"\n"+",";} 
			}
			catch(Exception e){countNoOfMisMatches++;mismatchesFoundIn+="Name"+",";}
			*/
	}
}
