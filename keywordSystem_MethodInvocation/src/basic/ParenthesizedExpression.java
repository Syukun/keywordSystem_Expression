package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * ParenthesizedExpression : (Expression)
 */
public class ParenthesizedExpression extends Expression {

	Expression expression;
	
	public ParenthesizedExpression(Expression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + expression.toString() + ")";
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(expression.getScore(keywords));
		return score;
	}

}
