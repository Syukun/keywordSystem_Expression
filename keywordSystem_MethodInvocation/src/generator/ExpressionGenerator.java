package generator;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import basic.Expression;
import basic.Type;

public class ExpressionGenerator extends Generator {
	@Override
	public Vector<Generator> getSubGenerators(int depth) {
		Vector<Generator> expressionGenerator = new Vector<Generator>();
		if(depth == 1) {
			expressionGenerator.add(new StringLiteralGenerator());
			expressionGenerator.add(new NumberLiteralGenerator());
		}else {
			expressionGenerator.add(new ArrayAccessGenerator());
		}
		return expressionGenerator;
	}

	@Override
	public Vector<Generator> getSubGeneratorsByType(int depth,Type t) {
		Vector<Generator> expressionGenerator = new Vector<Generator>();
		if(depth == 1) {
			expressionGenerator.add(new StringLiteralGenerator().changeProperties(t));
			expressionGenerator.add(new NumberLiteralGenerator().changeProperties(t));
		}else {
			expressionGenerator.add(new ArrayAccessGenerator().changeProperties(t));
		}
		return expressionGenerator;
	}

	public Generator[] getParameterGenerators(){
//		System.out.println("Line 22 in Class Expression Generator");
		return new Generator[] {};
	}

	public void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {}

	public Vector<Set<Type>> getPossibleParameterTypes(){
		System.out.println("Line 29 in Class Expression Generator");
		return null;
	}

	@Override
	public Set<Type> getAllReceiveTypeName() {
		Set<Type> allReceiveTypeName = new HashSet<Type>();
		Vector<Generator> allGenerator = this.getSubGenerators(1);
		allGenerator.addAll(this.getSubGenerators(2));
		for(Generator g : allGenerator) {
			allReceiveTypeName.addAll(g.getAllReceiveTypeName());
		}
		return allReceiveTypeName;
	}

	public Generator changeProperties(Type t) {
		return new ExpressionGenerator() {
			@Override
			public Set<Type> getAllReceiveTypeName() {
				Set<Type> allReceiveTypeName = new HashSet<Type>();
				allReceiveTypeName.add(t);
				return allReceiveTypeName;
			}
			
		};
	}

	
	
	
	


}
