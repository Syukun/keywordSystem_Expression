package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * PrefixOperator: ++ -- + - ~ !
 */
public class PrefixOperator {
	String operator;

	public PrefixOperator(String operator) {
		super();
		this.operator = operator;
	}
	
	public String toString() {
		return operator;
	}
	
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, operator, keywords);
		return score;
	}
}
