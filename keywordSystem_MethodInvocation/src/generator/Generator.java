package generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import basic.Expression;
import basic.ScoreDef;
import basic.Type;

public abstract class Generator {

	public abstract Generator[] getSubGenerators(Type t);

	public abstract Vector<Type> getAllReceiverTypeName();

	public abstract Generator[] getParameterGenerators();

	public abstract Type[] getParameterTypes(Type t);

	public Vector<Expression> generateExpression(int depth, String keywords) {
		Vector<Expression> result = new Vector<Expression>();
		// test whether there is another way to deal with it
		Vector<Map<Type, Vector<Expression>>> maxExpressions_BW = new Vector<Map<Type, Vector<Expression>>>();
		Vector<Map<Type, Vector<Expression>>> maxExpressionsExact_BW = new Vector<Map<Type, Vector<Expression>>>();

		fillTwoTables(depth, keywords, maxExpressions_BW, maxExpressionsExact_BW);

		for (Vector<Expression> expsOfEachType : maxExpressions_BW.get(depth).values()) {
			result.addAll(expsOfEachType);
		}

		ScoreDef.selectMaxBWExpressions(result, keywords);
		return result;
	}

	private void fillTwoTables(int depth, String keywords, Vector<Map<Type, Vector<Expression>>> maxExpressions_BW,
			Vector<Map<Type, Vector<Expression>>> maxExpressionsExact_BW) {
		initializeTwoTables(maxExpressions_BW, maxExpressionsExact_BW);
		for (int d = 1; d <= depth; d++) {
			generateExpressionAuxi(d, keywords, maxExpressions_BW, maxExpressionsExact_BW);
		}
	}

	private void initializeTwoTables(Vector<Map<Type, Vector<Expression>>> maxExpressions_BW,
			Vector<Map<Type, Vector<Expression>>> maxExpressionsExact_BW) {
		initializationTable(maxExpressions_BW);
		initializationTable(maxExpressionsExact_BW);
	}

	private void initializationTable(Vector<Map<Type, Vector<Expression>>> mappings) {
		Map<Type, Vector<Expression>> initElement = new HashMap<Type, Vector<Expression>>();
		for (Type t : this.getAllReceiverTypeName()) {
			initElement.put(t, new Vector<Expression>());
		}
		mappings.add(initElement);

	}

	private void generateExpressionAuxi(int d, String keywords, Vector<Map<Type, Vector<Expression>>> maxExpressions_BW,
			Vector<Map<Type, Vector<Expression>>> maxExpressionsExact_BW) {
		Map<Type,Vector<Expression>> maxExpsInDepth = new HashMap<Type,Vector<Expression>>();
		Map<Type,Vector<Expression>> maxExpsUnderDepth = new HashMap<Type,Vector<Expression>>();
		for(Type t : this.getAllReceiverTypeName()) {
			Vector<Expression> maxExpsInDepthWithTypeT = new Vector<Expression>();
			for(Generator g : this.getSubGenerators(t)) {
				g.generateExpressionExact(d,keywords,t,maxExpsInDepthWithTypeT,maxExpressions_BW,maxExpressionsExact_BW);
			}
			
			if(d == 1) {
				maxExpsUnderDepth.put(t, maxExpsInDepthWithTypeT);
			}else if(d > 1) {
				ScoreDef.selectMaxBWExpressions(maxExpsInDepthWithTypeT, keywords);
				
				Vector<Expression> maxExpsUnderDepthWithTypeT = new Vector<Expression>();
				maxExpsUnderDepthWithTypeT.addAll(maxExpsInDepthWithTypeT);
				maxExpsUnderDepthWithTypeT.addAll(maxExpressions_BW.get(d).get(t));
				ScoreDef.selectMaxBWExpressions(maxExpsUnderDepthWithTypeT, keywords);
				maxExpsUnderDepth.put(t, maxExpsUnderDepthWithTypeT);
			}
			maxExpsInDepth.put(t, maxExpsInDepthWithTypeT);
			maxExpressions_BW.add(maxExpsUnderDepth);
			maxExpressionsExact_BW.add(maxExpsInDepth);
		}
		
	}

	private void generateExpressionExact(int depth, String keywords, Type t,Vector<Expression> result,
			Vector<Map<Type, Vector<Expression>>> maxExpressions_BW,
			Vector<Map<Type, Vector<Expression>>> maxExpressionsExact_BW) {
		int arity = this.getParameterGenerators().length;
		if(arity == 0 && depth == 1) {
			generateWithSubExps(new Expression[0],result);
		}else if(depth > 1) {
			generateWithArity(depth,arity,t,result,maxExpressions_BW,maxExpressionsExact_BW);
		}
		
	}
	
	private void generateWithArity(int depth, int arity,Type t, Vector<Expression> result,
			Vector<Map<Type, Vector<Expression>>> maxExpressions_BW,
			Vector<Map<Type, Vector<Expression>>> maxExpressionsExact_BW) {
		for(int exactFlags = 1 ;exactFlags <= (1 << arity) - 1; exactFlags++) {
			Expression[] subExps = new Expression[arity];
			generateWithArityAuxi(depth,arity,exactFlags,t,result,subExps,maxExpressions_BW,maxExpressionsExact_BW);
		}
		
	}

	private void generateWithArityAuxi(int depth, int arity, int exactFlags,Type t, Vector<Expression> result,
			Expression[] subExps, Vector<Map<Type, Vector<Expression>>> maxExpressions_BW,
			Vector<Map<Type, Vector<Expression>>> maxExpressionsExact_BW) {
		if(arity == 0) {
			generateWithSubExps(subExps,result);
		}else {
			Type[] types = this.getParameterTypes(t);
			Vector<Expression> candidates = isBitOn(exactFlags,arity-1)?
					maxExpressionsExact_BW.get(depth-1).get(types[arity-1])
					:maxExpressions_BW.get(depth-2).get(types[arity-1]);
					
			for(Expression e : candidates) {
				subExps[arity-1] = e;
				generateWithArityAuxi(depth,arity-1,exactFlags,t,result,subExps,maxExpressions_BW,maxExpressionsExact_BW);
				
			}
		}
		
	}

	public abstract void generateWithSubExps(Expression[] subExps,Vector<Expression> result);

	public static boolean isBitOn(int x, int i) {
		return (x & (1 << i)) != 0;
	}
	
}
