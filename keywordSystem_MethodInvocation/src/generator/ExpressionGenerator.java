package generator;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import basic.Expression;
import basic.Type;
import vector.VectorOfElements;

public class ExpressionGenerator extends Generator {

	@Override
	public Vector<Generator> getSubGenerators() {
		Vector<Generator> expressionGenerator = new Vector<Generator>();
		expressionGenerator.add(new ArrayAccessGenerator());
		expressionGenerator.add(new StringLiteralGenerator());
		return expressionGenerator;
	}

	public Generator[] getParameterGenerators() throws NullPointerException{
		return null;
	}

	public void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {}

	public Vector<Vector<Type>> getPossibleParameterTypes() throws NullPointerException{
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

	public void changeProperties(Type t) {}
	


}
