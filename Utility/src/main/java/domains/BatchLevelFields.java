package domains;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BatchLevelFields") 
public class BatchLevelFields {
private List<BatchLevelField> BatchLevelField;

@XmlElement(name="BatchLevelField")
public List<BatchLevelField> getBatchLevelField() {
	return BatchLevelField;
}

public void setBatchLevelField(List<BatchLevelField> batchLevelField) {
	BatchLevelField = batchLevelField;
}

public void  equals(List<BatchLevelField> toCompare)
{
	int countNoOfMatches=0;String matchesFound="\n";
	int countNoOfMisMatches=0;String mismatchesFoundIn="\n",mismatchesValueFound="\n";
	
	
	int count=0;
	for(BatchLevelField batchLevelField :this.getBatchLevelField())
	{
		BatchLevelField batchLevelFieldToCompare =toCompare.get(count);
		count++;
		
	}
}
}
