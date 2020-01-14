package domains;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Pages") 
public class Pages 
{
	List<Page> Page;
	
	@XmlElement(name="Page")
	public List<Page> getPage() {
		return Page;
	}
	
	public void setPage(List<Page> page) {
		Page = page;
	}
	
	public void equals(Pages p1,Pages p2)
	{
		List<Page> p1Li =p1.getPage();
		List<Page> p2Li =p2.getPage();
		int countNoOfMatches=0;String matchesFound="";
		int countNoOfMisMatches=0;String mismatchesFoundIn="",mismatchesValueFound="";
		
		for(Page p : p1Li)
		{
			String identifier =p.getIdentifier();
			
			for(Page pToCompare :p2Li)
			{
				if(identifier.equals(pToCompare.getIdentifier()))
				{
					
					if(p.getOldFileName().equals(pToCompare.getOldFileName())){countNoOfMatches++;matchesFound+="OldFileName"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="OldFileName : Old value ="+p.getOldFileName()+", New Value = "+pToCompare.getOldFileName()+"\n"+",";}
					 
					if(p.getNewFileName().equals(pToCompare.getNewFileName())){countNoOfMatches++;matchesFound+="NewFileName"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="NewFileName : Old value ="+p.getNewFileName()+", New Value = "+pToCompare.getNewFileName()+"\n"+",";}
					 
					if(p.getSourceFileID().equals(pToCompare.getSourceFileID())){countNoOfMatches++;matchesFound+="SourceFileID"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="SourceFileID : Old value ="+p.getSourceFileID()+", New Value = "+pToCompare.getSourceFileID()+"\n"+",";}
					 
					if(p.getHocrFileName().equals(pToCompare.getHocrFileName())){countNoOfMatches++;matchesFound+="HocrFileName"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="HocrFileName : Old value ="+p.getHocrFileName()+", New Value = "+pToCompare.getHocrFileName()+"\n"+",";}
					 
					if(p.getThumbnailFileName().equals(pToCompare.getThumbnailFileName())){countNoOfMatches++;matchesFound+="ThumbnailFileName"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="ThumbnailFileName : Old value ="+p.getThumbnailFileName()+", New Value = "+pToCompare.getThumbnailFileName()+"\n"+",";}
					 
					if(p.getDisplayFileName().equals(pToCompare.getDisplayFileName())){countNoOfMatches++;matchesFound+="DisplayFileName"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="DisplayFileName : Old value ="+p.getDisplayFileName()+", New Value = "+pToCompare.getDisplayFileName()+"\n"+",";}
					 
					if(p.getOCRInputFileName().equals(pToCompare.getOCRInputFileName())){countNoOfMatches++;matchesFound+="OCRInputFileName"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="OCRInputFileName : Old value ="+p.getOCRInputFileName()+", New Value = "+pToCompare.getOCRInputFileName()+"\n"+",";}
					 
					if(p.getDirection().equals(pToCompare.getDirection())){countNoOfMatches++;matchesFound+="Direction"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="Direction : Old value ="+p.getDirection()+", New Value = "+pToCompare.getDirection()+"\n"+",";}
					 
					if(p.getIsRotated().equals(pToCompare.getIsRotated())){countNoOfMatches++;matchesFound+="IsRotated"+",";}
					else {countNoOfMisMatches++;mismatchesValueFound+="IsRotated : Old value ="+p.getIsRotated()+", New Value = "+pToCompare.getIsRotated()+"\n"+",";}
					
					
					
					 
					
				}
			}
		}
		
	}
}
