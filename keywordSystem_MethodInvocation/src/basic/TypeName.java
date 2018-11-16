package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * has set
 */
public class TypeName {
	String typeName;
	public TypeName(String typeName) {
		this.typeName = typeName;
	}
	public String toString() {
		return typeName;
	}
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, typeName, keywords);
		return score;
	}
}
