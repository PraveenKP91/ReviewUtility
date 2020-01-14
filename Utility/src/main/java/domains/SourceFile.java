package domains;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SourceFiles") 
public class SourceFile {
	private int Id;
	private String SourceFileName;
	private String SourceFileSize;
	
	@XmlElement(name="Id")
	public int getId() {
		return Id;
	}
	
	@XmlElement(name="SourceFileName")
	public String getSourceFileName() {
		return SourceFileName;
	}
	 
	@XmlElement(name="SourceFileSize")
	public String getSourceFileSize() {
		return SourceFileSize;
	}
	public void setId(int id) {
		Id = id;
	}
	public void setSourceFileName(String sourceFileName) {
		SourceFileName = sourceFileName;
	}
	public void setSourceFileSize(String sourceFileSize) {
		SourceFileSize = sourceFileSize;
	}
	
	
	
}
