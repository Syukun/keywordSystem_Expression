package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * PostfixExpression: Expression PostfixOperator
 */
public class PostfixExpression extends Expression {

	Expression expression;
	PostfixOperator postfixOperator;

	public PostfixExpression(Expression expression, PostfixOperator postfixOperator) {
		super();
		this.expression = expression;
		this.postfixOperator = postfixOperator;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return expression.toString() + postfixOperator.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(expression.getScore(keywords)).add(postfixOperator.getScore(keywords));
		return score;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return expression.getType();
	}

}
