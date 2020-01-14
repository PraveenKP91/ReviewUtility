package domains;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BatchLevelFields") 
public class BatchLevelField {
	private String Name;
	private String Value;
	private String Type;
	
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
	public void setName(String name) {
		Name = name;
	}
	public void setValue(String value) {
		Value = value;
	}
	public void setType(String type) {
		Type = type;
	}
	public void  equalsa(BatchLevelField toCompare)
	{
		int countNoOfMatches=0;String matchesFound="\n";
		int countNoOfMisMatches=0;String mismatchesFoundIn="\n",mismatchesValueFound="\n";
		
		try{
			if(this.Name.equals(toCompare.getName())) 
				{countNoOfMatches++;matchesFound+="Name"+",";}
			else {countNoOfMisMatches++;mismatchesValueFound+="Name : Old value ="+this.Name+", New Value = "+toCompare.getName()+"\n"+",";} 
			}
			catch(Exception e){countNoOfMisMatches++;mismatchesFoundIn+="Name"+",";}
			
		try{
			if(this.Value.equals(toCompare.getValue())) 
				{countNoOfMatches++;matchesFound+="Value"+",";}
			else {countNoOfMisMatches++;mismatchesValueFound+="Value : Old value ="+this.Value+", New Value = "+toCompare.getValue()+"\n"+",";} 
			}
			catch(Exception e){countNoOfMisMatches++;mismatchesFoundIn+="Value"+",";}
			
		try{
			if(this.Type.equals(toCompare.getType())) 
				{countNoOfMatches++;matchesFound+="Type"+",";}
			else {countNoOfMisMatches++;mismatchesValueFound+="Type : Old value ="+this.Type+", New Value = "+toCompare.getType()+"\n"+",";}
			}
			catch(Exception e){countNoOfMisMatches++;mismatchesFoundIn+="Type"+",";}
			
	}
}
