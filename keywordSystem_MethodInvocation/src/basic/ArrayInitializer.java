package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * ASTNode : 
 * ArrayInitializer:
 * 	'{[Expression{,Expression} [,]]'}
 *
 * in this generate system, we omit the comma cause it won't have any change.
 */
public class ArrayInitializer extends Expression{
	List<Expression> expressions;
	
	public ArrayInitializer(List<Expression> expressions) {
		this.expressions = expressions;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer("{");
		if(expressions != null) {
			String seperator ="";
			for(Expression exp : expressions) {
				result.append(seperator + exp.toString());
				seperator = ",";
			}
		}
		result.append("}");
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		if(expressions != null) {
			for(Expression expression : expressions) {
				score = score.add(expression.getScore(keywords));
			}
		}
		return score;
	}

	// need to be test whether it is true
	@Override
	public Type getType() {
		Type t;
		if(expressions == null) {
			// use ASTParser to distinguish
			t = Type.OBJECT;
		}else {
			t = expressions.get(0).getType();
		}
		return t;
	}
	
	
}
