package basic;
/*
 *  ConditionalExpression:
    Expression ? Expression : Expression
 */

import java.math.BigDecimal;
import java.util.List;

public class ConditionalExpression extends Expression {
	Expression conditionExpression;
	Expression thenExpression;
	Expression elseExpression;

	public ConditionalExpression(Expression exp1, Expression exp2, Expression exp3) {
		this.conditionExpression = exp1;
		this.thenExpression = exp2;
		this.elseExpression = exp3;
	}

	public String toString() {
		return conditionExpression.toString() + "?" + thenExpression.toString() + ":" + elseExpression.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(conditionExpression.getScore(keywords)).add(thenExpression.getScore(keywords))
				.add(elseExpression.getScore(keywords));
		return score;
	}

}
