package generator;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import basic.Expression;
import basic.Type;

public class ExpressionGenerator extends Generator {

	@Override
	public Vector<Generator> getSubGenerators() {
		Vector<Generator> expressionGenerator = new Vector<Generator>();
//		expressionGenerator.add(new ArrayAccessGenerator());
		expressionGenerator.add(new StringLiteralGenerator());
		expressionGenerator.add(new NumberLiteralGenerator());
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
		for(Generator g : this.getSubGenerators()) {
			allReceiveTypeName.addAll(g.getAllReceiveTypeName());
		}
		return allReceiveTypeName;
	}

	public Generator changeProperties(Type t) {
		return this;
	}
	
	
	
	


}
