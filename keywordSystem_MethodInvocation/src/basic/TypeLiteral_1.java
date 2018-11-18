package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * TypeLiteral_1 : Type . class
 */
public class TypeLiteral_1 extends TypeLiteral {

	Type type;
	
	public TypeLiteral_1(Type type) {
		super();
		this.type = type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return type.toString() + ".class";
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(type.getScore(keywords));
		ScoreDef.checkInKeyword(score, "class", keywords);
		return score;
	}

	// I don't know what it is right now
	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return new TypeName("ClassType");
	}

}
