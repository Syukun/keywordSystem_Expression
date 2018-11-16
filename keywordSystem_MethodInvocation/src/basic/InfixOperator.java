package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * InfixOperator: * / % + - << >> >>> < > <= >= == !=  ^ & | && || 
 */
public class InfixOperator {
	String operator;
	public InfixOperator(String operator) {
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
