package generator;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import basic.Expression;
import basic.PrimitiveType;
import basic.ScoreDef;
import basic.Type;

public class ExpressionGenerator extends Generator {
	public Vector<Expression> generateExpression(int depth, String keywords) {
		Vector<Expression> result = new Vector<Expression>();
		Table.initializeAllTables(depth);
		for (int d = 1; d <= depth; d++) {
			this.fillTwoTables(depth,keywords);
		}
		for (Vector<Expression> expsLEDepOfEachType : this.getExpressionsLEDepth(depth)) {
			result.addAll(expsLEDepOfEachType);
		}
		ScoreDef.selectMaxBWExpressions(result, keywords);
		return result;
	}
	
	public void fillTwoTables(int depth,String keywords) {
		for(Type t : this.getAllReceiveTypes()) {
			for(Generator subG_t: this.getSubGeneratorsForEachType(t)) {
				int arity = subG_t.getArity();
				Vector<Expression> resultExps = new Vector<Expression>();
				subG_t.generateExpressionExact(depth,keywords,resultExps);
				
				
				if(arity == 0) {
					if(depth == 1) {
						subG_t.addToTableTwo(t,depth,resultExps);
					}
					Vector<Expression> resultInDepthOne = subG_t.getTableTwoInDepth(1).get(t);
					subG_t.addToTableOne(t,depth,resultInDepthOne);
				}else {
					if(depth < 2) {
						System.out.println("Error in fillTwoTables in Class ExpressionGenerator");
					}else if(depth == 2) {
						subG_t.addToTableTwo(t,depth,resultExps);
						subG_t.addToTableOne(t,depth,resultExps);
					}else {
						subG_t.addToTableTwo(t,depth,resultExps);
						resultExps.addAll(subG_t.getTableOneInDepth(depth-1).get(t));
						ScoreDef.selectMaxBWExpressions(resultExps, keywords);
						subG_t.addToTableOne(t,depth,resultExps);
					}
				}
			}
			
		}
	}


	public Vector<Generator> getSubGenerators() {
		Vector<Generator> subGenerators = new Vector<Generator>();
		subGenerators.add(new ArrayAccessGenerator());
		subGenerators.add(new StringLiteralGenerator());
		subGenerators.add(new NumberLiteralGenerator());
		return subGenerators;
	}
	
	public Vector<Generator> getSubGeneratorsForEachType(Type t){
		Vector<Generator> subGenerators_T = new Vector<Generator>();
		for(Generator subG : this.getSubGenerators()) {
			subG.changeProperties(t);
			subGenerators_T.add(subG);
		}
		return subGenerators_T;
	}

	public Generator[] getParameterGenerators() {
		return new Generator[] {};
	}
	
	public Set<Type> getAllReceiveTypes() {
		Set<Type> allReceiveTypes = new HashSet<Type>();
		allReceiveTypes.add(PrimitiveType.INT);
		allReceiveTypes.add(PrimitiveType.STRING);
		return allReceiveTypes;
	}

	@Override
	public Generator changeProperties(Type t) {
		// TODO Auto-generated method stub
		return new ExpressionGenerator() {
			@Override
			public Set<Type> getAllReceiveTypes() {
				Set<Type> allReceiveTypes = new HashSet<Type>();
				allReceiveTypes.add(t);
				return allReceiveTypes;
			}
		};
	}

	@Override
	public Vector<Set<Type>> getPossibleParameterTypes() {
		// TODO Auto-generated method stub
		return new Vector<Set<Type>>();
	}

	@Override
	public void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {}
	
	
}
