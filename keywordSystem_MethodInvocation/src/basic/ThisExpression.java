package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * ThisExpression: [ ClassName . ] this
 */
public class ThisExpression extends Expression {

	Name className;
	
	public ThisExpression(Name className) {
		super();
		this.className = className;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		if(className != null) {
			result.append(className.toString() + ".");
		}
		result.append("this");
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		if(className != null) {
			score = score.add(className.getScore(keywords));
		}
		ScoreDef.checkInKeyword(score, "this", keywords);
		return score;
	}

}
