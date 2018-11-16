package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * PrefixExpression: PrefixOperator Expression
 */
public class PrefixExpression extends Expression {

	PrefixOperator prefixOperator;
	Expression expression;
	
	
	public PrefixExpression(PrefixOperator prefixOperator, Expression expression) {
		super();
		this.prefixOperator = prefixOperator;
		this.expression = expression;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return prefixOperator.toString() + expression.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(prefixOperator.getScore(keywords)).add(expression.getScore(keywords));
		return score;
	}

}
