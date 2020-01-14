package domains;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Page") 
public class Page {
	
	private String Identifier;
	private String OldFileName;
	private String NewFileName;
	private String SourceFileID;
	private String HocrFileName;
	private String ThumbnailFileName;
	private String DisplayFileName;
	private String OCRInputFileName;
	private String Direction;
	private String IsRotated;
	private PageLevelFields PageLevelFields;
	
	@XmlElement(name="Identifier")
	public String getIdentifier() {
		return Identifier;
	}
	public void setIdentifier(String identifier) {
		Identifier = identifier;
	}
	
	@XmlElement(name="OldFileName")
	public String getOldFileName() {
		return OldFileName;
	}
	public void setOldFileName(String oldFileName) {
		OldFileName = oldFileName;
	}
	
	@XmlElement(name="NewFileName")
	public String getNewFileName() {
		return NewFileName;
	}
	public void setNewFileName(String newFileName) {
		NewFileName = newFileName;
	}
	
	@XmlElement(name="SourceFileID")
	public String getSourceFileID() {
		return SourceFileID;
	}
	public void setSourceFileID(String sourceFileID) {
		SourceFileID = sourceFileID;
	}
	
	@XmlElement(name="HocrFileName")
	public String getHocrFileName() {
		return HocrFileName;
	}
	public void setHocrFileName(String hocrFileName) {
		HocrFileName = hocrFileName;
	}
	
	@XmlElement(name="ThumbnailFileName")
	public String getThumbnailFileName() {
		return ThumbnailFileName;
	}
	public void setThumbnailFileName(String thumbnailFileName) {
		ThumbnailFileName = thumbnailFileName;
	}
	
	@XmlElement(name="DisplayFileName")
	public String getDisplayFileName() {
		return DisplayFileName;
	}
	public void setDisplayFileName(String displayFileName) {
		DisplayFileName = displayFileName;
	}
	
	@XmlElement(name="OCRInputFileName")
	public String getOCRInputFileName() {
		return OCRInputFileName;
	}
	public void setOCRInputFileName(String oCRInputFileName) {
		OCRInputFileName = oCRInputFileName;
	}
	
	@XmlElement(name="Direction")
	public String getDirection() {
		return Direction;
	}
	public void setDirection(String direction) {
		Direction = direction;
	}
	
	@XmlElement(name="IsRotated")
	public String getIsRotated() {
		return IsRotated;
	}
	public void setIsRotated(String isRotated) {
		IsRotated = isRotated;
	}
	
	@XmlElement(name="PageLevelFields")
	public PageLevelFields getPageLevelFields() {
		return PageLevelFields;
	}
	public void setPageLevelFields(PageLevelFields pageLevelFields) {
		PageLevelFields = pageLevelFields;
	}
	
}
