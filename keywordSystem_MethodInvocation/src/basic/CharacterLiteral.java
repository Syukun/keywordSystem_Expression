package basic;

import java.math.BigDecimal;
import java.util.List;

public class CharacterLiteral extends Expression {
	char c;
	public CharacterLiteral(char c) {
		this.c = c;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(c);
	}
	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, String.valueOf(c), keywords);
		return score;
	}
	
	public Type getType() {
		return new PrimitiveType("char");
	}

}
