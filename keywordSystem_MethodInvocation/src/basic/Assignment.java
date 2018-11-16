package basic;

import java.math.BigDecimal;
import java.util.List;

public class Assignment extends Expression {
	Expression leftExp;
	AssignmentOperator assignmentOperator;
	Expression rightExp;

	public Assignment(Expression leftExp, AssignmentOperator assignmentOperator, Expression rightExp) {
		super();
		this.leftExp = leftExp;
		this.assignmentOperator = assignmentOperator;
		this.rightExp = rightExp;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(leftExp.toString()).append(assignmentOperator.toString()).append(rightExp.toString());
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(leftExp.getScore(keywords)).add(assignmentOperator.getScore(keywords))
				.add(rightExp.getScore(keywords));
		return score;
	}

}
