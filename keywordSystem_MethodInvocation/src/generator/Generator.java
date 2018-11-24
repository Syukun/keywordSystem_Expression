package generator;

import java.util.Map;
import java.util.Vector;

import basic.Expression;
import basic.ScoreDef;
import basic.Type;

public abstract class Generator {
	public abstract Generator[] getGenerators();

	public abstract int getArity();

	public abstract Type[] getParameterTypes();

	public abstract Vector<MapOfTypeToExps> getMaxExps();

	public abstract Vector<MapOfTypeToExps> getMaxExactExps();
	
	/*
	 * 
	 */
	public Vector<Expression> genericGenerate(int depth, String keywords) {
		Vector<Expression> result = new Vector<Expression>();
		Vector<MapOfTypeToExps> maxExpressionExact = getMaxExactExps();
		Vector<MapOfTypeToExps> maxExpression = getMaxExps();
		// test whether it is need or not
		if (depth < 1) {
		} else {
			for (Generator g : this.getGenerators()) {
				for (int d = 1; d < depth; d++) {
					g.genericGenerateExact(depth, keywords, maxExpressionExact, maxExpression);
				}
				for (Vector<Expression> exps : maxExpression.get(depth).mapping.values()) {
					result.addAll(exps);
				}
			}
			ScoreDef.selectMaxBWExpressions(result, keywords);
		}
		return result;
	}

	public void genericGenerateExact(int depth, String keywords, Vector<MapOfTypeToExps> maxExactExps,
			Vector<MapOfTypeToExps> maxExps) {
		int arity = this.getArity();
		if(arity == 0) {
			
		}
		
	}

	public static boolean isBitOn(int x, int i) {
		return (x & (1 << i)) != 0;
	}
}

class MapOfTypeToExps {
	Map<Type, Vector<Expression>> mapping;

	public MapOfTypeToExps(Map<Type, Vector<Expression>> mapping) {
		super();
		this.mapping = mapping;
	}

}
