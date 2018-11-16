package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * InstanceOfExpression: Expression instanceof Type
 */
public class InstanceOfExpression extends Expression {
	Expression expression;
	Type type;
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(expression.toString()).append(" instanceof ").append(type.toString());
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(expression.getScore(keywords));
		ScoreDef.checkInKeyword(score, "instance", keywords);
		ScoreDef.checkInKeyword(score, "in", keywords);
		score = score.add(type.getScore(keywords));
		return score;
	}

}
