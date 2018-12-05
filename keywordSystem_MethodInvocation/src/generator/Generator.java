package generator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import basic.Expression;
import basic.PrimitiveType;
import basic.ScoreDef;
import basic.Type;
import vector.VectorOfElements;

public abstract class Generator {
	
	public abstract Vector<Generator> getSubGenerators();
	public abstract Vector<Generator> getSubGenerators(Type t);
	public abstract Vector<Type> getAllReceiverTypeName();

	public abstract Generator[] getParameterGenerators();
	public abstract Type[] getParameterTypes();
	

	public Vector<Expression> generateExpression(int depth, String keywords) {
		Vector<Expression> result = new Vector<Expression>();
//		// test whether there is another way to deal with it
//		Vector<Map<Type, Vector<Expression>>> maxExpressions_BW = new Vector<Map<Type, Vector<Expression>>>();
//		Vector<Map<Type, Vector<Expression>>> maxExpressionsExact_BW = new Vector<Map<Type, Vector<Expression>>>();

		fillTwoTables(depth, keywords);
		for (Vector<? extends Expression> expsOfEachType : getExpressionsUnderDepth(depth)) {
			result.addAll(expsOfEachType);
		}

		ScoreDef.selectMaxBWExpressions( result, keywords);
		return result;
	}
	private Collection<Vector<? extends Expression>> getExpressionsUnderDepth(int depth) {
		return getTableOne().get(depth).values();
	}
	public Vector<Map<Type, Vector<? extends Expression>>> getTableOne() {
		return Table.allTables.get(this.getClass())[0].table;
	}
	
	public Vector<Map<Type, Vector<? extends Expression>>> getTableTwo() {
		return Table.allTables.get(this.getClass())[1].table;
	}

	private void fillTwoTables(int depth, String keywords) {
		for (int d = 1; d <= depth; d++) {
			generateExpressionAuxi(d, keywords);
		}
	}

	private void generateExpressionAuxi(int d, String keywords) {
		Map<Type,Vector<? extends Expression>> maxExpsInDepth = new HashMap<Type,Vector<? extends Expression>>();
		Map<Type,Vector<? extends Expression>> maxExpsUnderDepth = new HashMap<Type,Vector<? extends Expression>>();	
		for(Type t : this.getAllReceiverTypeName()) {
			Vector<Expression> maxExpsInDepthWithTypeT = new Vector<Expression>();
			for(Generator g : this.getSubGenerators(t)) {
				Map<Type,Vector<? extends Expression>> mappings = new HashMap<Type,Vector<? extends Expression>>();
				g.generateExpressionExact(d,keywords,maxExpsInDepthWithTypeT);
				mappings.put(t, maxExpsInDepthWithTypeT);
				g.getTableOne().add(mappings);
				 need change the cmp function
				g.getTableTwo().add(mappings);
			}
			
			if(d == 1) {
				maxExpsUnderDepth.put(t, maxExpsInDepthWithTypeT);
			}else if(d > 1) {
				ScoreDef.selectMaxBWExpressions(maxExpsInDepthWithTypeT, keywords);
				
				Vector<Expression> maxExpsUnderDepthWithTypeT = new Vector<Expression>();
				maxExpsUnderDepthWithTypeT.addAll(maxExpsInDepthWithTypeT);
				maxExpsUnderDepthWithTypeT.addAll(this.getTableOne().get(d).get(t));
				ScoreDef.selectMaxBWExpressions(maxExpsUnderDepthWithTypeT, keywords);
				maxExpsUnderDepth.put(t, maxExpsUnderDepthWithTypeT);
			}
			maxExpsInDepth.put(t, maxExpsInDepthWithTypeT);
			this.getTableOne().add(maxExpsUnderDepth);
			this.getTableTwo().add(maxExpsInDepth);
		}
		
	}
	
	private void generateExpressionExact(int depth, String keywords,Vector<Expression> result) {
		System.out.println("class is :  in line 83 in Generator" + this.getClass().getName());
		int arity = this.getParameterGenerators().length;
		if(arity == 0 && depth == 1) {
			generateWithSubExps(new Expression[0],result);
		}else if(depth > 1) {
			generateWithArity(depth,arity,result);
		}
		
	}
	
	private void generateWithArity(int depth, int arity, Vector<Expression> result) {
		for(int exactFlags = 1 ;exactFlags <= (1 << arity) - 1; exactFlags++) {
			Expression[] subExps = new Expression[arity];
			generateWithArityAuxi(depth,arity,exactFlags,result,subExps);
		}
		
	}

	private void generateWithArityAuxi(int depth, int arity, int exactFlags, Vector<Expression> result,
			Expression[] subExps) {
		if(arity == 0) {
			generateWithSubExps(subExps,result);
		}else {
			Type[] types = this.getParameterTypes();
			Generator[] generators = this.getParameterGenerators();
			Vector<? extends Expression> candidates = isBitOn(exactFlags,arity-1)?
					generators[arity].getTableOne().get(depth-1).get(types[arity-1])
					:generators[arity].getTableTwo().get(depth-2).get(types[arity-1]);
					
			for(Expression e : candidates) {
				subExps[arity-1] = e;
				generateWithArityAuxi(depth,arity-1,exactFlags,result,subExps);
				
			}
		}
		
	}

	public abstract void generateWithSubExps(Expression[] subExps,Vector<Expression> result);

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


