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

	/*
	 * ExpressionGenerator : {new StringLiteralGenerator(),new
	 * ArrayAccessGenerator()} ArrayAccessGenerator : null StringLiteralGenerator :
	 * null
	 */
	public abstract Vector<Generator> getSubGenerators(int depth);

	public abstract Generator[] getParameterGenerators();

	public abstract void generateWithSubExps(Expression[] subExps, Vector<Expression> result);

	public abstract Vector<Set<Type>> getPossibleParameterTypes();

	/*
	 * ExpressionGenerator : Int | String ArrayAccessGenerator : Int | String
	 * StringLiteralGenerator : String
	 */
	public abstract Set<Type> getAllReceiveTypeName();

	public abstract Generator changeProperties(Type t);

	public Vector<Expression> generateExpression(int depth, String keywords) {
		Vector<Expression> result = new Vector<Expression>();
		Table.initializeAllTables(depth);
		this.fillTableOneInDepth(depth, keywords);
		for (Vector<Expression> expsLEDepOfEachType : getAllExpressionsUnderDepth(depth)) {
			result.addAll(expsLEDepOfEachType);
		}
		// result.stream().forEach(System.out::println);
		ScoreDef.selectMaxBWExpressions(result, keywords);
		return result;
	}

	public Collection<Vector<Expression>> getAllExpressionsUnderDepth(int depth) {
		// TODO Auto-generated method stub
		// System.out.println(this.getClass().getName());
		// System.out.println(this.getTableOne().mappingFromTypeToExps.get(depth).size());
		return getMappingUnderDepth(depth).values();
	}

	public Table getTableOne() {
		// TODO Auto-generated method stub

		return Table.allTables.get(this.getClass()).table1;
	}

	public Table getTableTwo() {
		// TODO Auto-generated method stub
		return Table.allTables.get(this.getClass()).table2;
	}

	private Map<Type, Vector<Expression>> getMappingUnderDepth(int depth) {
		return this.getTableOne().mappingFromTypeToExps.get(depth - 1);
	}

	private Map<Type, Vector<Expression>> getMappingInDepth(int depth) {
		return this.getTableTwo().mappingFromTypeToExps.get(depth - 1);
	}

//	= depth
	public void fillTableTwoInDepth(int depth, String keywords) {
		for(Type t : this.getAllReceiveTypeName()) {
			Generator g_changed = this.changeProperties(t);
			Vector<Generator> subGenerators = g_changed.getSubGenerators(depth);
			Map<Type,Vector<Expression>> mappingForTableTwo = g_changed.getMappingInDepth(depth);
			Vector<Expression> result = new Vector<Expression>();
			if(subGenerators == null) {
				g_changed.generateExpressionExact(depth, keywords, result);
				mappingForTableTwo.put(t, result);
				
			}else {
				for(Generator sub_g : subGenerators) {
					if(sub_g.getAllReceiveTypeName().contains(t)) {
						Vector<Expression> sub_result = new Vector<Expression>();
						Generator sub_g_changed = sub_g.changeProperties(t);
						sub_g_changed.generateExpressionExact(depth, keywords, sub_result);
					}
				}
			}
		}
	}
//	≤ depth
	public void fillTableOneInDepth(int depth, String keywords) {
		
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

	public Vector<Expression> getPossibleExpressionsInDepth(int i, Set<Type> possibleParameterTypes) {
		Vector<Expression> result = new Vector<Expression>();
		for (Type t : possibleParameterTypes) {
			result.addAll(this.getTableTwo().mappingFromTypeToExps.get(i).get(t));
		}
		return result;
	}

	public Vector<Expression> getPossibleExpressionsUnderDepth(int i, Set<Type> possibleParameterTypes) {
		Vector<Expression> result = new Vector<Expression>();
		for (Type t : possibleParameterTypes) {
			result.addAll(this.getTableOne().mappingFromTypeToExps.get(i).get(t));
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
