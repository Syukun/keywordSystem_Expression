package basic;

import java.math.BigDecimal;
import java.util.List;

public class Modifier {
	String modifier;
	public Modifier(String modifier) {
		this.modifier = modifier;
	}
	public String toString() {
		return modifier;
	}
	public BigDecimal getScore(List<String> keywords) {
		// unfinished with condition of public static
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, modifier, keywords);
		return score;
	}
}
