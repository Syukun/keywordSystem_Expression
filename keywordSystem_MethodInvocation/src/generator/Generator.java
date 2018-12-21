package generator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import basic.Expression;
import basic.PrimitiveType;
import basic.ScoreDef;
import basic.Type;

public abstract class Generator {
	public abstract Vector<Generator> getSubGenerators();
	public abstract Vector<Generator> getSubGeneratorsForEachType(Type t);
	public abstract Generator[] getParameterGenerators();
	public abstract Set<Type> getAllReceiveTypes();
	
	public abstract Generator changeProperties(Type t);
	public abstract Vector<Set<Type>> getPossibleParameterTypes();
	public abstract void generateWithSubExps(Expression[] subExps, Vector<Expression> result);
	public abstract Vector<Expression> generateExpression(int depth,String keywords);
	
	
	public void addToTableOne(Type t, int depth, Vector<Expression> resultExps) {
		this.getTableOneInDepth(depth).put(t, resultExps);
	}
	
	public void addToTableTwo(Type t, int depth, Vector<Expression> resultExps) {
		this.getTableTwoInDepth(depth).put(t, resultExps);
	}
	
	public Collection<Vector<Expression>> getExpressionsLEDepth(int depth) {
		return this.getTableOneInDepth(depth).values();
	}
	
	public int getArity() {
		return this.getParameterGenerators().length;
	}

	public Map<Type, Vector<Expression>> getTableOneInDepth(int depth) {
		return Table.allTables.get(this.getClass()).table1.mappingFromTypeToExps.get(depth);
	}
	
	public Map<Type, Vector<Expression>> getTableTwoInDepth(int depth) {
		return Table.allTables.get(this.getClass()).table2.mappingFromTypeToExps.get(depth);
	}
	
	public void generateExpressionExact(int depth, String keywords, Vector<Expression> result) {
		int arity = this.getParameterGenerators().length;
		if (arity == 0 && depth == 1) {
			generateWithSubExps(new Expression[0], result);
		}
		if (depth > 1) {
			generateWithArity(depth, arity, result);
		}
	}
	
	public void generateWithArity(int depth, int arity, Vector<Expression> result) {
		for (int exactFlags = 1; exactFlags <= (1 << arity) - 1; exactFlags++) {
			Expression[] subExps = new Expression[arity];
			generateWithArityAuxi(depth, arity, exactFlags, result, subExps);
		}
	}
	
	public void generateWithArityAuxi(int depth, int arity, int exactFlags, Vector<Expression> result,
			Expression[] subExps) {
		if (arity == 0) {
			generateWithSubExps(subExps, result);
		} else {
			Set<Type> possibleParameterTypes = this.getPossibleParameterTypes().get(arity - 1);
			Generator parameterGenerator = this.getParameterGenerators()[arity - 1];
			Vector<Expression> candidates = isBitOn(exactFlags, arity - 1)
					? parameterGenerator.getPossibleExpressionsUnderDepth(depth - 1, possibleParameterTypes)
					: parameterGenerator.getPossibleExpressionsInDepth(depth - 2, possibleParameterTypes);

			for (Expression e : candidates) {
				subExps[arity - 1] = e;
				generateWithArityAuxi(depth, arity - 1, exactFlags, result, subExps);
			}
		}

	}
	
	public Vector<Expression> getPossibleExpressionsInDepth(int d, Set<Type> possibleParameterTypes) {
		Vector<Expression> result = new Vector<Expression>();
		for (Type t : possibleParameterTypes) {
			result.addAll(this.getTableTwoInDepth(d).get(t));
		}
		return result;
	}
	
	public Vector<Expression> getPossibleExpressionsUnderDepth(int d, Set<Type> possibleParameterTypes) {
		Vector<Expression> result = new Vector<Expression>();
		for (Type t : possibleParameterTypes) {
			result.addAll(this.getTableOneInDepth(d).get(t));
		}
		return result;
	}
	
	public static boolean isBitOn(int x, int i) {
		return (x & (1 << i)) != 0;
	}
	
	public static Generator[] getAllGenetators() {
		return new Generator[] { new ExpressionGenerator(), new StringLiteralGenerator(), new ArrayAccessGenerator(),
				new NumberLiteralGenerator() };
	}
}
