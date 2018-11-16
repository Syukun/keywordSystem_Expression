package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * Primitive Type: int float short long boolean byte char string double void
 * Has Set
 */
public class PrimitiveType extends Type{
	String name;
	public PrimitiveType(String name) {
		this.name = name;
	}
	public String toString() {
		return name;
	}
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, name, keywords);
		return score;
	}
}
