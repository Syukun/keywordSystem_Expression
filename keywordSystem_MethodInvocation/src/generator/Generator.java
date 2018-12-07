package generator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import basic.Expression;
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
	public abstract void generateWithSubExps(Expression[] subExps,Vector<Expression> result);
	public abstract Vector<Vector<Type>> getPossibleParameterTypes();
	
	/*
	 * ExpressionGenerator : Int | String ArrayAccessGenerator : Int | String
	 * StringLiteralGenerator : String
	 */
	public abstract Set<Type> getAllReceiveTypeName();

	public abstract void changeProperties(Type t);

	public Vector<Expression> generateExpression(int depth, String keywords) {
		Vector<Expression> result = new Vector<Expression>();
		this.fillTwoTablesUnderDepth(depth, keywords);
		for (Vector<Expression> expsLEDepOfEachType : getAllExpressionsUnderDepth(depth)) {
			result.addAll(expsLEDepOfEachType);
		}
		ScoreDef.selectMaxBWExpressions(result, keywords);
		return result;
	}

	public Collection<Vector<Expression>> getAllExpressionsUnderDepth(int depth) {
		// TODO Auto-generated method stub
		return this.getTableOne().mappingFromTypeToExps.get(depth).values();
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
					g.changeProperties(t);
					Vector<Expression> result_sub = new Vector<Expression>();
					g.generateExpressionExact(depth, keywords, result_sub);
					g.addToTables(depth, keywords, t, result_sub);
					result.addAll(result_sub);
				}
				this.addToTables(depth, keywords, t, result);
			} else {
				this.changeProperties(t);
				this.generateExpressionExact(depth, keywords, result);
				this.addToTables(depth, keywords, t, result);
			}
		}

	}

	public void addToTables(int depth, String keywords, Type t, Vector<Expression> result_sub) {
		Table table1 = this.getTableOne();
		Table table2 = this.getTableTwo();

		Map<Type, Vector<Expression>> maxExpsInDepth = table2.mappingFromTypeToExps.get(depth);
		Map<Type, Vector<Expression>> maxExpsUnderDepth = table1.mappingFromTypeToExps.get(depth);

		if (depth == 1) {
			maxExpsUnderDepth.put(t, result_sub);
		}
		if (depth > 1) {
			ScoreDef.selectMaxBWExpressions(result_sub, keywords);

			Vector<Expression> maxExpsUnderDepth_T = new Vector<Expression>();
			maxExpsUnderDepth_T.addAll(table1.mappingFromTypeToExps.get(depth - 1).get(t));
			maxExpsUnderDepth_T.addAll(result_sub);
			ScoreDef.selectMaxBWExpressions(maxExpsUnderDepth_T, keywords);
			maxExpsUnderDepth.put(t, maxExpsUnderDepth_T);
		}
		maxExpsInDepth.put(t, result_sub);
	}

	public Table getTableTwo() {
		// TODO Auto-generated method stub
		return Table.allTables.get(this.getClass()).table2;
	}

	public Table getTableOne() {
		// TODO Auto-generated method stub
		return Table.allTables.get(this.getClass()).table1;
	}

	public void generateExpressionExact(int depth, String keywords, Vector<Expression> result) {
		int arity = this.getParameterGenerators().length;
		if(arity == 0 && depth == 1) {
			generateWithSubExps(new Expression[0],result);
		}
		if(depth>1) {
			generateWithArity(depth,arity,result);
		}
	}

	public void generateWithArity(int depth, int arity, Vector<Expression> result) {
		for(int exactFlags = 1 ;exactFlags <= (1 << arity) - 1; exactFlags++) {
			Expression[] subExps = new Expression[arity];
			generateWithArityAuxi(depth,arity,exactFlags,result,subExps);
		}
		
	}

	public void generateWithArityAuxi(int depth, int arity, int exactFlags, Vector<Expression> result,
			Expression[] subExps) {
		if(arity == 0) {
			generateWithSubExps(subExps,result);
		}else {
			Vector<Type> possibleParameterTypes = this.getPossibleParameterTypes().get(arity-1);
			Generator parameterGenerator = this.getParameterGenerators()[arity-1];
			Vector<Expression> candidates = isBitOn(exactFlags,arity-1)?
					parameterGenerator.getPossibleExpressionsUnderDepth(depth-1,possibleParameterTypes)
					:parameterGenerator.getPossibleExpressionsInDepth(depth-2,possibleParameterTypes);
					
					for(Expression e : candidates) {
						subExps[arity-1] = e;
						generateWithArityAuxi(depth,arity-1,exactFlags,result,subExps);
					}
		}
		
	}
	
	public Vector<Expression> getPossibleExpressionsInDepth(int i, Vector<Type> possibleParameterTypes) {
		Vector<Expression> result = new Vector<Expression>();
		for(Type t : possibleParameterTypes) {
			result.addAll(this.getTableTwo().mappingFromTypeToExps.get(i).get(t));
		}
		return result;
	}

	public Vector<Expression> getPossibleExpressionsUnderDepth(int i, Vector<Type> possibleParameterTypes) {
		Vector<Expression> result = new Vector<Expression>();
		for(Type t : possibleParameterTypes) {
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
				new ArrayAccessGenerator()
		};
	}
	
	


}
