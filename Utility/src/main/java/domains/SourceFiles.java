package domains;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SourceFiles") 
public class SourceFiles {
private List<SourceFile> SourceFile;
 
@XmlElement(name="SourceFile")
public List<SourceFile> getSourceFile() {
	return SourceFile;
}

public void setSourceFile(List<SourceFile> sourceFile) {
	SourceFile = sourceFile;
}

}
