package basic;

import java.math.BigDecimal;
import java.util.List;

public class NumberLiteral extends Expression {

	String number;

	public NumberLiteral(String number) {
		super();
		this.number = number;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return number;
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, number, keywords);
		return score;
	}

}
