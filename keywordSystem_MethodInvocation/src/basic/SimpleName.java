package basic;

import java.math.BigDecimal;
import java.util.List;

public class SimpleName extends Name{
	String name;
	public SimpleName(String name) {
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
