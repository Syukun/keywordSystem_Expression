package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * SuperMethodInvocation: [ClassName.] super. [<Type {,Type}>] Identifier([Expression {,Expression}])
 */
public class SuperMethodInvocation extends Expression {
	Name className;
	GenericTypes genericTypes;
	SimpleName name;
	List<Expression> arguments;
	
	
	
	public SuperMethodInvocation(Name className, GenericTypes genericTypes, SimpleName name,
			List<Expression> arguments) {
		super();
		this.className = className;
		this.genericTypes = genericTypes;
		this.name = name;
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		if(className != null) {
			result.append(className.toString()+".");
		}
		result.append("super.");
		if(genericTypes != null) {
			result.append(genericTypes.toString());
		}
		result.append(name.toString() + "(");
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
		if(className != null) {
			score = score.add(className.getScore(keywords));
		}
		ScoreDef.checkInKeyword(score, "super", keywords);
		if(genericTypes != null) {
			score = score.add(genericTypes.getScore(keywords));
		}
		score = score.add(name.getScore(keywords));
		if(arguments != null) {
			for(Expression argument : arguments) {
				score = score.add(argument.getScore(keywords));
			}
		}
		return score;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return name.getType();
	}

}
