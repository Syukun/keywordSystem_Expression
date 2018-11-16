package basic;

import java.math.BigDecimal;
import java.util.List;

public class StringLiteral extends Expression{

	String str;
	
	public StringLiteral(String str) {
		super();
		this.str = str;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return str;
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, str, keywords);
		return score;
	}

}
