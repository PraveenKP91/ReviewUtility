package domains;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PageLevelFields")
public class AlternateValues {

	private List<AlternateValue> AlternateValue;

	@XmlElement(name="AlternateValue")
	public List<AlternateValue> getAlternateValue() {
		return AlternateValue;
	}

	public void setAlternateValue(List<AlternateValue> alternateValue) {
		AlternateValue = alternateValue;
	}

	public void equals(AlternateValues av1,AlternateValues av2)
	{
		List<AlternateValue> avLi1=av1.getAlternateValue();
		List<AlternateValue> avLi2=av2.getAlternateValue();
	/*
		private String Name;
		private String Value;
		private String Type;
		private String Confidence;
		private String LearnedFileName;
		private String ForceReview;
		
		*/
		for(AlternateValue av3 :avLi1)
		{
			String learnedFileName =av3.getLearnedFileName();
			for(AlternateValue av4 :avLi2)
			{
			if(av4.getLearnedFileName().equals(learnedFileName))	
			{
				int countNoOfMatches=0;String matchesFound="";
				int countNoOfMisMatches=0;String mismatchesFoundIn="",mismatchesValueFound="";
				
			
			}
			}
		}
		
	}
}
