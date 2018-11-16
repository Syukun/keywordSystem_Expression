package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * GenericTypes : < Type { , Type } >
 */
public class GenericTypes {
	List<Type> genericTypes;

	public GenericTypes(List<Type> genericTypes) {
		super();
		this.genericTypes = genericTypes;
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("<");
		String seperator = "";
		for(Type genericType : genericTypes) {
			result.append(seperator + genericType.toString());
			seperator = ",";
		}
		return result.toString();
	}
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		for(Type genericType : genericTypes) {
			score = score.add(genericType.getScore(keywords));
		}
		return score;
	}
}
