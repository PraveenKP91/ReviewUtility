package domains;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PageLevelFields")
public class AlternateValue {
	private String Name;
	private String Value;
	private String Type;
	private String Confidence;
	private String LearnedFileName;
	private String ForceReview;
	
	@XmlElement(name="Name")
	public String getName() {
		return Name;
	}
	
	@XmlElement(name="Value")
	public String getValue() {
		return Value;
	}
	
	@XmlElement(name="Type")
	public String getType() {
		return Type;
	}
	
	@XmlElement(name="Confidence")
	public String getConfidence() {
		return Confidence;
	}
	
	@XmlElement(name="LearnedFileName")
	public String getLearnedFileName() {
		return LearnedFileName;
	}
	
	@XmlElement(name="ForceReview")
	public String getForceReview() {
		return ForceReview;
	}
	
	public void setName(String name) {
		Name = name;
	}
	public void setValue(String value) {
		Value = value;
	}
	public void setType(String type) {
		Type = type;
	}
	public void setConfidence(String confidence) {
		Confidence = confidence;
	}
	public void setLearnedFileName(String learnedFileName) {
		LearnedFileName = learnedFileName;
	}
	public void setForceReview(String forceReview) {
		ForceReview = forceReview;
	}
	
}
