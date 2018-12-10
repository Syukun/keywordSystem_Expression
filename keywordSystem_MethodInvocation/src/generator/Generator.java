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
	public abstract Vector<Generator> getSubGenerators();

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
		this.fillTwoTablesUnderDepth(depth, keywords);
		for (Vector<Expression> expsLEDepOfEachType : getAllExpressionsUnderDepth(depth)) {
			result.addAll(expsLEDepOfEachType);
		}
//		result.stream().forEach(System.out::println);
		ScoreDef.selectMaxBWExpressions(result, keywords);
		return result;
	}

	public Collection<Vector<Expression>> getAllExpressionsUnderDepth(int depth) {
		// TODO Auto-generated method stub
//		System.out.println(this.getClass().getName());
//		System.out.println(this.getTableOne().mappingFromTypeToExps.get(depth).size());
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
		return this.getTableOne().mappingFromTypeToExps.get(depth-1);
	}
	private Map<Type, Vector<Expression>> getMappingInDepth(int depth) {
		return this.getTableTwo().mappingFromTypeToExps.get(depth-1);
	}
	public void fillTwoTablesUnderDepth(int depth, String keywords) {
		for (int d = 1; d <= depth; d++) {
			fillTwoTablesInDepth(d, keywords);
		}
	}

	public void fillTwoTablesInDepth(int depth, String keywords) {
		Vector<Generator> subGenerators = this.getSubGenerators();
		for (Type t : this.getAllReceiveTypeName()) {
			Vector<Expression> result = new Vector<Expression>();
			
			if (subGenerators != null) {
				for (Generator g : subGenerators) {
					if (g.getAllReceiveTypeName().contains(t)) {
						Generator g_t = g.changeProperties(t);
						Vector<Expression> result_sub = new Vector<Expression>();
						g_t.generateExpressionExact(depth, keywords, result_sub);
						g_t.addToTables(depth, keywords, t, result_sub);
						result.addAll(result_sub);
					}
				}

				this.addToTables(depth, keywords, t, result);
			} else {
				if (this.getAllReceiveTypeName().contains(t)) {
					Generator this_t = this.changeProperties(t);
					this_t.generateExpressionExact(depth, keywords, result);
					this_t.addToTables(depth, keywords, t, result);
				}
			}
			
			
		}

	}

	public void addToTables(int depth, String keywords, Type t, Vector<Expression> result_sub) {
		Map<Type, Vector<Expression>> maxExpsInDepth = this.getMappingUnderDepth(depth);
		Map<Type, Vector<Expression>> maxExpsUnderDepth = this.getMappingInDepth(depth);

		if (depth == 1) {
			maxExpsUnderDepth.put(t, result_sub);
		}
		if (depth > 1) {
			Vector<Expression> maxExpsUnderDepth_T = new Vector<Expression>();
			maxExpsUnderDepth_T.addAll(this.getMappingUnderDepth(depth-1).get(t));
			maxExpsUnderDepth_T.addAll(result_sub);
			ScoreDef.selectMaxBWExpressions(maxExpsUnderDepth_T, keywords);
			maxExpsUnderDepth.put(t, maxExpsUnderDepth_T);
		}
		maxExpsInDepth.put(t, result_sub);
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
		return new Generator[] { 
				new ExpressionGenerator(),
				new StringLiteralGenerator(), 
				new ArrayAccessGenerator() ,
				new NumberLiteralGenerator()};
	}

}
