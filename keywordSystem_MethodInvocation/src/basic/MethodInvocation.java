package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * MethodInvocation: [ Expression . ] [ < Type { , Type } > ] Identifier ( [ Expression { , Expression } ] )
 */
public class MethodInvocation extends Expression{

	Expression expression;
	GenericTypes genericTypes;
	SimpleName identifier;
	List<Expression> arguments;
	
	
	public MethodInvocation(Expression expression, GenericTypes genericTypes, SimpleName identifier,
			List<Expression> arguments) {
		super();
		this.expression = expression;
		this.genericTypes = genericTypes;
		this.identifier = identifier;
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		if(expression != null) {
			result.append(expression.toString()+".");
		}
		if(genericTypes != null) {
			result.append(genericTypes.toString());
		}
		result.append(identifier.toString()+"(");
		if(arguments != null) {
			String seperator = "";
			for(Expression argument : arguments) {
				result.append(seperator + argument.toString());
				seperator = ",";
			}
		}
		result.append(")");
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		if(expression != null) {
			score = score.add(expression.getScore(keywords));
		}
		if(genericTypes != null) {
			score = score.add(genericTypes.getScore(keywords));
		}
		score = score.add(identifier.getScore(keywords));
		if(arguments != null) {
			for(Expression argument : arguments) {
				score = score.add(argument.getScore(keywords));
			}
		}
		return score;
	}

}
