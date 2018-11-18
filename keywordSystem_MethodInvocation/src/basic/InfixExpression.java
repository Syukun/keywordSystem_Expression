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

	@Override
	public Type getType() {		
		switch(infixOperator.toString()) {
			case InfixOperator.ONE:
			case InfixOperator.TWO:
			case InfixOperator.THREE:
			case InfixOperator.FOUR:
			case InfixOperator.FIVE:
			case InfixOperator.SIX:
			case InfixOperator.SEVEN:
			case InfixOperator.EIGHT:
				if(leftExp.getType().equals(rightExp.getType())) {
					return leftExp.getType();
				}else {
					System.out.println("Not matching types of leftExpression and rightExpression in class InfixExpression");
				}
				break;
			case InfixOperator.NINE:
			case InfixOperator.TEN:
			case InfixOperator.ELEVEN:
			case InfixOperator.TWELWE:
			case InfixOperator.THIRTEEN:
			case InfixOperator.FOURTEEN:
			case InfixOperator.FIFTEEN:
			case InfixOperator.SIXTEEN:
			case InfixOperator.SEVENTEEN:
			case InfixOperator.EIGHTEEN:
				return new PrimitiveType("boolean");
			default : System.out.println("Not matching of infix operator");
		}
		return null;
	}

	
	
}
