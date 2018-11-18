package basic;

import java.math.BigDecimal;
import java.util.List;

public class NullLiteral extends Expression {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "null";
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, "null", keywords);
		return score;
	}
	
	public Type getType() {
		return Type.NULL;
	}

}
