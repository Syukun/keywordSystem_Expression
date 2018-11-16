package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 *  FieldAccess: Expression . Identifier 
 */
public class FieldAccess extends Expression {
	Expression expression;
	SimpleName simpleName;
	
	
	public FieldAccess(Expression expression, SimpleName simpleName) {
		super();
		this.expression = expression;
		this.simpleName = simpleName;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(expression.toString()+"."+simpleName.toString());
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(expression.getScore(keywords)).add(simpleName.getScore(keywords));
		return score;
	}

}
