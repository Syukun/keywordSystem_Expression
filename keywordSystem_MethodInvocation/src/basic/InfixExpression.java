package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * InfixExpression: Expression InfixOperator Expression { InfixOperator Expression }  
 */
public class InfixExpression extends Expression {
	Expression leftExp;
	InfixOperator infixOperator;
	Expression rightExp;

	public InfixExpression(Expression leftExp, InfixOperator infixOperator, Expression rightExp) {
		super();
		this.leftExp = leftExp;
		this.infixOperator = infixOperator;
		this.rightExp = rightExp;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(leftExp.toString()).append(infixOperator.toString()).append(rightExp.toString());
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(leftExp.getScore(keywords)).add(infixOperator.getScore(keywords))
				.add(rightExp.getScore(keywords));
		return score;
	}

}
