package domains;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PageLevelFields")
public class PageLevelField {
	
	private String Name;
	private String Value;
	private String Type;
	private String Confidence;
	private String LearnedFileName;
	private String OcrConfidenceThreshold;
	private String OcrConfidence;
	private String FieldOrderNumber;
	private String ForceReview;
	private AlternateValues AlternateValues;
	private String Category;
	private String WidgetType;
	
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
	
	@XmlElement(name="OcrConfidenceThreshold")
	public String getOcrConfidenceThreshold() {
		return OcrConfidenceThreshold;
	}
	
	@XmlElement(name="OcrConfidence")
	public String getOcrConfidence() {
		return OcrConfidence;
	}
	
	@XmlElement(name="FieldOrderNumber")
	public String getFieldOrderNumber() {
		return FieldOrderNumber;
	}
	
	@XmlElement(name="ForceReview")
	public String getForceReview() {
		return ForceReview;
	}
	
	@XmlElement(name="AlternateValues")
	public AlternateValues getAlternateValues() {
		return AlternateValues;
	}
	
	@XmlElement(name="Category")
	public String getCategory() {
		return Category;
	}
	
	@XmlElement(name="WidgetType")
	public String getWidgetType() {
		return WidgetType;
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
	public void setOcrConfidenceThreshold(String ocrConfidenceThreshold) {
		OcrConfidenceThreshold = ocrConfidenceThreshold;
	}
	public void setOcrConfidence(String ocrConfidence) {
		OcrConfidence = ocrConfidence;
	}
	public void setFieldOrderNumber(String fieldOrderNumber) {
		FieldOrderNumber = fieldOrderNumber;
	}
	public void setForceReview(String forceReview) {
		ForceReview = forceReview;
	}
	public void setAlternateValues(AlternateValues alternateValues) {
		AlternateValues = alternateValues;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public void setWidgetType(String widgetType) {
		WidgetType = widgetType;
	}
	
	
}
