package generator;

import java.util.Vector;

import basic.Expression;
import basic.Type;
import vector.VectorOfElements;

public class ExpressionGenerator extends Generator {

	@Override
	public Generator[] getSubGenerators(Type t) {
		// TODO Auto-generated method stub
		return new Generator[] { new ExpressionGenerator(), new StringLiteralGenerator(), new ArrayAccessGenerator() };
	}

	@Override
	public Vector<Type> getAllReceiverTypeName() {
		// TODO Auto-generated method stub
		return VectorOfElements.allTypes;
	}

	@Override
	public Generator[] getParameterGenerators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type[] getParameterTypes(Type t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {
		// TODO Auto-generated method stub
	}

}
