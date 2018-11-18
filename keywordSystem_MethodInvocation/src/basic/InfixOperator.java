package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * InfixOperator: * / % + - << >> >>> < > <= >= == !=  ^ & | && || 
 */
public class InfixOperator {
	public static final String ONE = "*";
	public static final String TWO = "/";
	public static final String THREE = "%";
	public static final String FOUR = "+";
	public static final String FIVE = "-";
	public static final String SIX = "<<";
	public static final String SEVEN = ">>";
	public static final String EIGHT = ">>>";
	public static final String NINE = "<";
	public static final String TEN = ">";
	public static final String ELEVEN = "<=";
	public static final String TWELWE = ">=";
	public static final String THIRTEEN = "!=";
	public static final String FOURTEEN = "^";
	public static final String FIFTEEN = "&";
	public static final String SIXTEEN = "|";
	public static final String SEVENTEEN = "&&";
	public static final String EIGHTEEN = "||";
	
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
