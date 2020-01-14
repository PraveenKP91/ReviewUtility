package domains;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DocumentLevelField")
public class DocumentLevelField {

	String name;
	String value;
	String type;

	@XmlElement (name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement (name = "Value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@XmlElement (name = "Type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DocumentLevelField() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocumentLevelField(String name, String value, String type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}

}
