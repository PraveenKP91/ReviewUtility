package domains;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PageLevelFields")
public class PageLevelFields {

	private List<PageLevelField> PageLevelFields;

	@XmlElement(name="PageLevelFields")
	public List<PageLevelField> getPageLevelFields() {
		return PageLevelFields;
	}

	public void setPageLevelFields(List<PageLevelField> pageLevelFields) {
		PageLevelFields = pageLevelFields;
	}
	
	public void equal(PageLevelFields plf1,PageLevelFields plf2)
	{ 
		int countNoOfMatches=0;String matchesFound="";
		int countNoOfMisMatches=0;String mismatchesFoundIn="",mismatchesValueFound="";
		
		List<PageLevelField> plfLi1=plf1.getPageLevelFields();
		List<PageLevelField> plfLi2=plf2.getPageLevelFields();
		for(PageLevelField plf3 :plfLi1)
		{
		
			String learnedFileName =plf3.getLearnedFileName();
			for(PageLevelField plfToCompare :plfLi2)
			{
			
				if(learnedFileName.equals(plfToCompare.getLearnedFileName()))
						{
					countNoOfMatches++;matchesFound+="Name"+",";
					
					if(plf3.getName().equals(plfToCompare.getName())){countNoOfMatches++;matchesFound+="Name"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="Name : Old value ="+plf3.getName()+", New Value = "+plfToCompare.getName()+"\n"+",";}
					
					if(plf3.getValue().equals(plfToCompare.getValue())){countNoOfMatches++;matchesFound+="Value"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="Value : Old value ="+plf3.getValue()+", New Value = "+plfToCompare.getValue()+"\n"+",";}
					
					if(plf3.getType().equals(plfToCompare.getType())){countNoOfMatches++;matchesFound+="Type"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="Type : Old value ="+plf3.getType()+", New Value = "+plfToCompare.getType()+"\n"+",";}
					
					if(plf3.getConfidence().equals(plfToCompare.getConfidence())){countNoOfMatches++;matchesFound+="Confidence"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="Confidence : Old value ="+plf3.getConfidence()+", New Value = "+plfToCompare.getConfidence()+"\n"+",";}
					
					if(plf3.getLearnedFileName().equals(plfToCompare.getLearnedFileName())){countNoOfMatches++;matchesFound+="LearnedFileName"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="LearnedFileName : Old value ="+plf3.getLearnedFileName()+", New Value = "+plfToCompare.getLearnedFileName()+"\n"+",";}
					
					if(plf3.getOcrConfidenceThreshold().equals(plfToCompare.getOcrConfidenceThreshold())){countNoOfMatches++;matchesFound+="OcrConfidenceThreshold"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="OcrConfidenceThreshold : Old value ="+plf3.getOcrConfidenceThreshold()+", New Value = "+plfToCompare.getOcrConfidenceThreshold()+"\n"+",";}
					
					if(plf3.getOcrConfidence().equals(plfToCompare.getOcrConfidence())){countNoOfMatches++;matchesFound+="OcrConfidence"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="OcrConfidence : Old value ="+plf3.getOcrConfidence()+", New Value = "+plfToCompare.getOcrConfidence()+"\n"+",";}
					
					if(plf3.getFieldOrderNumber().equals(plfToCompare.getFieldOrderNumber())){countNoOfMatches++;matchesFound+="FieldOrderNumber"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="FieldOrderNumber : Old value ="+plf3.getFieldOrderNumber()+", New Value = "+plfToCompare.getFieldOrderNumber()+"\n"+",";}
					
					if(plf3.getForceReview().equals(plfToCompare.getForceReview())){countNoOfMatches++;matchesFound+="ForceReview"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="ForceReview : Old value ="+plf3.getForceReview()+", New Value = "+plfToCompare.getForceReview()+"\n"+",";}
					
					if(plf3.getCategory().equals(plfToCompare.getCategory())){countNoOfMatches++;matchesFound+="Category"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="Category : Old value ="+plf3.getCategory()+", New Value = "+plfToCompare.getCategory()+"\n"+",";}
					
					if(plf3.getWidgetType().equals(plfToCompare.getWidgetType())){countNoOfMatches++;matchesFound+="WidgetType"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="WidgetType : Old value ="+plf3.getWidgetType()+", New Value = "+plfToCompare.getWidgetType()+"\n"+",";}
					
					
						}
				
			}
			
		}
		
	}
}
