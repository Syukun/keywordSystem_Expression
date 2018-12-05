package generator;

import java.util.Vector;

import basic.Expression;
import basic.Type;
import vector.VectorOfElements;

public class ExpressionGenerator extends Generator {
	
	@Override
	public Vector<Generator> getSubGenerators() {
		Vector<Generator> result = new Vector<Generator>();
		result.add(new StringLiteralGenerator());
		result.add(new ArrayAccessGenerator());
		return result;
	}
	
	@Override
	public Vector<Generator> getSubGenerators(Type t) {
		Vector<Generator> result = new Vector<Generator>();
		for(Generator g : this.getSubGenerators()) {
			result.addAll(g.getSubGenerators(t));
		}
		return result;
	}

	@Override
	public Vector<Type> getAllReceiverTypeName() {
		// TODO Auto-generated method stub
		return VectorOfElements.allTypes;
	}

	@Override
	public Generator[] getParameterGenerators() {
		System.out.println("error in getParameterGenerators in class ExpressionGenerator");
		return null;
	}

	@Override
	public Type[] getParameterTypes() {
		System.out.println("error in getParameterTypes in class ExpressionGenerator");
		return null;
	}

	@Override
	public void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {
		// TODO Auto-generated method stub
	}
	


}
