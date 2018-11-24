package generator;

import java.util.Map;
import java.util.Vector;

import basic.Expression;
import basic.ScoreDef;
import basic.Type;

public class ExpressionGenerator extends Generator{
	Vector<Map<Type, Vector<Expression>>> maxBWExpressionExactDepth = new Vector<Map<Type, Vector<Expression>>>();
	Vector<Map<Type, Vector<Expression>>> maxBWExpressionLEQDepth = new Vector<Map<Type, Vector<Expression>>>();

	public Vector<Expression> generateExpression(int depth, String keywords,
			Vector<Map<Type, Vector<Expression>>> maxExpsExact, Vector<Map<Type, Vector<Expression>>> maxExps) {
		Vector<Expression> expressions = new Vector<Expression>();
		new ExpressionGenerator().genericGenerate(depth, keywords, maxExpsExact, maxExps, expressions);
		return expressions;
	}

	public Vector<Expression> generateExpressionExact(int depth, String keywords) {
		Vector<Expression> expressionVector_Exact = new Vector<Expression>();
		if (depth < 1) {
		}
		if (depth == 1) {
			expressionVector_Exact.addAll(StringLiteralGenerator.generateStringLiteral(depth, keywords));
		}
		if (depth > 1) {
			expressionVector_Exact.addAll(ArrayAccessGenerator.generateArrayAccessExact(depth, keywords));
		}
		return expressionVector_Exact;
	}
}
