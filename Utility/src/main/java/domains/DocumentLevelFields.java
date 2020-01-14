package domains;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DocumentLevelFields") 
public class DocumentLevelFields {
List<DocumentLevelField> documentLevelField;

@XmlElement(name = "DocumentLevelField")
public List<DocumentLevelField> getDocumentLevelField() {
	return documentLevelField;
}

public void setDocumentLevelField(List<DocumentLevelField> documentLevelField) {
	this.documentLevelField = documentLevelField;
}

public DocumentLevelFields(List<DocumentLevelField> documentLevelField) {
	super();
	this.documentLevelField = documentLevelField;
}

public DocumentLevelFields() {
	super();
	// TODO Auto-generated constructor stub
}


}
