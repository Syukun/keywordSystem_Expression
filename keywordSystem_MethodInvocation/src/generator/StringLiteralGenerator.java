package generator;

import java.util.Vector;

import basic.Expression;
import basic.PrimitiveType;
import basic.Type;
import vector.VectorOfElements;

public class StringLiteralGenerator extends ExpressionGenerator{

	@Override
	public Generator[] getSubGenerators(Type t) {
		// TODO Auto-generated method stub
		return new Generator[] { new StringLiteralGenerator()};
	}

	@Override
	public Vector<Type> getAllReceiverTypeName() {
		Vector<Type> result = new Vector<Type>();
		result.add(new PrimitiveType("String"));
		return result;
	}

	@Override
	public Type[] getParameterTypes(Type t) {
		// TODO Auto-generated method stub
		return new Type[] {new PrimitiveType("String")};
	}

	@Override
	public void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {
		result.addAll(VectorOfElements.stringLiteralVector);
	}
}
