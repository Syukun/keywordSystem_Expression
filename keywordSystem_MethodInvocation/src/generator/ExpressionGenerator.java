package generator;

import java.util.Vector;

import basic.Expression;

public class ExpressionGenerator {
	public Vector<Expression> generateExpression(int depth, String keywords){
		Vector<Expression> expressionVector = new Vector<Expression>();
		if(depth < 1) {
		}else {
			for(int d=1;d<=depth;d++) {
				expressionVector.addAll(generateExpressionExact(d,keywords));
			}
		}
		return expressionVector;
	}

	public Vector<Expression> generateExpressionExact(int depth, String keywords) {
		Vector<Expression> expressionVector_Exact = new Vector<Expression>();
		if(depth < 1) {
		}
		if(depth == 1) {
			expressionVector_Exact.addAll(StringLiteralGenerator.generateStringLiteral(depth, keywords));
		}
		if(depth > 1) {
			expressionVector_Exact.addAll(ArrayAccessGenerator.generateArrayAccessExact(depth, keywords));
		}
		return expressionVector_Exact;
	}
}
