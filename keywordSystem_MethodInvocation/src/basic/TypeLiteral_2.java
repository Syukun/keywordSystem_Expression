package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * TypeLiteral_2: void.class
 */
public class TypeLiteral_2 extends TypeLiteral {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "void.class";
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, "void", keywords);
		ScoreDef.checkInKeyword(score, "class", keywords);
		return score;
	}

}
