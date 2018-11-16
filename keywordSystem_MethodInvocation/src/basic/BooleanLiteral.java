package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * true | false
 */
public class BooleanLiteral extends Expression{
	boolean b;
	public BooleanLiteral(boolean b) {
		this.b = b;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(b);
	}
	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, String.valueOf(b), keywords);
		return score;
	}
	
	
	
}
