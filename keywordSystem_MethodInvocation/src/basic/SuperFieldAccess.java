package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * SuperFieldAccess : [ClassName.] super.Identifier
 */
public class SuperFieldAccess extends Expression {

	Name className;
	SimpleName identifier;
	
	
	public SuperFieldAccess(Name className, SimpleName identifier) {
		super();
		this.className = className;
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		if(className != null) {
			result.append(className.toString()+".");
		}
		result.append("super."+identifier.toString());
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		if(className != null) {
			score = score.add(className.getScore(keywords));
		}
		ScoreDef.checkInKeyword(score, "super", keywords);
		score = score.add(identifier.getScore(keywords));
		return score;
	}
	
	public Type getType() {
		return identifier.getType();
	}

}
