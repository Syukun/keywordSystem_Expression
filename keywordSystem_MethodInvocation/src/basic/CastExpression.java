package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * CastExpression: (Type) Expression
 */
public class CastExpression extends Expression {
	Type type;
	Expression expression;

	public CastExpression(Type type, Expression expression) {
		super();
		this.type = type;
		this.expression = expression;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("(").append(type.toString()).append(")").append(expression.toString());
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(type.getScore(keywords)).add(expression.getScore(keywords));
		return score;
	}

}
