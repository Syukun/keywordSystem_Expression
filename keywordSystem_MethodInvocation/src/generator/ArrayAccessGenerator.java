package generator;

import java.util.Map;
import java.util.Vector;

import basic.ArrayAccess;
import basic.Expression;
import basic.Type;

public class ArrayAccessGenerator extends ExpressionGenerator {
	// generate all ArrayAccess under depth.
	int bw = MethodsWithBeamWidth.BEAMWIDTH;
	Vector<Map<Type,Vector<ArrayAccess>>> maxBWArrayAccessExactDepth = new Vector<Map<Type,Vector<ArrayAccess>>>();
	Vector<Map<Type,Vector<ArrayAccess>>> maxBWArrayAccessLEQDepth = new Vector<Map<Type,Vector<ArrayAccess>>>();
	
	public static Vector<ArrayAccess> generateArrayAccess(int depth, String keywords) {
		Vector<ArrayAccess> arrayAccessVector = new Vector<ArrayAccess>();
		if (depth < 2) {
		} else {
			for (int d = 2; d <= depth; d++) {
				arrayAccessVector.addAll(ArrayAccessGenerator.generateArrayAccessExact(d, keywords));
			}
		}
		return arrayAccessVector;
	}

	public static Vector<ArrayAccess> generateArrayAccessExact(int depth, String keywords) {
		Vector<ArrayAccess> arrayAccessVector_Exact = new Vector<ArrayAccess>();
		generateWithArity(depth, 2, keywords, arrayAccessVector_Exact);
		return arrayAccessVector_Exact;
	}

	public static boolean isBitOn(int x, int i) {
		return (x & (1 << i)) != 0;
	}

	public static void generateWithArity(int depth, int arity, String keywords, Vector<ArrayAccess> result) {
		if (depth < arity) {
			System.out.println("class ArrayAccessGenerator gets wrong with depth less than arity");
		} else {
			for (int exactFlags = 1; exactFlags <= (1 << arity) - 1; exactFlags++) {
				Expression[] subExps = new Expression[arity];
				generateWithArityAuxi(arity, depth, exactFlags, keywords, subExps, result);
			}
		}
	}

	private static void generateWithArityAuxi(int arity, int depth, int exactFlags, String keywords,
			Expression[] subExps, Vector<ArrayAccess> result) {
		if (arity == 0) {
			generateArrayAccessWithArity(subExps, result);
		} else {
			Vector<Expression> expExacts = new ExpressionGenerator().generateExpressionExact(depth - 1, keywords);
			Vector<Expression> exps = new ExpressionGenerator().generateExpression(depth - 2, keywords);
			switch (arity) {
			case 2:
			case 1:
				if (isBitOn(exactFlags, arity - 1)) {
					if (expExacts.size() > 0) {
						for (Expression expExact : expExacts) {
							subExps[arity - 1] = expExact;
							generateWithArityAuxi(arity - 1, depth, exactFlags, keywords, subExps, result);
						}
					}
				} else {
					if (exps.size() > 0) {
						for (Expression exp : exps) {
							subExps[arity - 1] = exp;
							generateWithArityAuxi(arity - 1, depth, exactFlags, keywords, subExps, result);
						}
					}
				}
				break;
			default:
				System.out.println("default");
			}
		}
	}

	private static void generateArrayAccessWithArity(Expression[] subExps, Vector<ArrayAccess> result) {
		ArrayAccess arrayAccess = new ArrayAccess(subExps[0], subExps[1]);
		result.add(arrayAccess);

	}

}
