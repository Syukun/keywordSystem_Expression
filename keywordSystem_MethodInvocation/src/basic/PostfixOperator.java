package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * PostfixOperator : ++ | --
 */
public class PostfixOperator {
	String operator;

	public PostfixOperator(String operator) {
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
