package generator;

import java.util.Map;
import java.util.Vector;

import basic.ArrayAccess;
import basic.Expression;
import basic.PrimitiveType;
import basic.Type;
import vector.VectorOfElements;

public class ArrayAccessGenerator extends ExpressionGenerator {
	static Vector<Map<Type, Vector<Expression>>> maxExpressions = new Vector<Map<Type, Vector<Expression>>>();
	static Vector<Map<Type, Vector<Expression>>> maxExpressionsExact = new Vector<Map<Type, Vector<Expression>>>();

	@Override
	public Generator[] getParameterGenerators() {
		// TODO Auto-generated method stub
		return new Generator[] { new ExpressionGenerator(), new ExpressionGenerator() };
	}

	@Override
	public Type[] getParameterTypes() {
		// TODO Auto-generated method stub
		return new Type[] { Type.ALL, new PrimitiveType("Integer") };
	}

	@Override
	public Vector<Type> getAllReceiverTypeName() {
		return VectorOfElements.allTypes;
	}

	@Override
	public void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {
		result.add(new ArrayAccess(subExps[0], subExps[1]));

	}

	@Override
	public Vector<Generator> getSubGenerators() {
		Vector<Generator> result = new Vector<Generator>();
		result.add(this);
		return result;
	}

	@Override
	public Vector<Generator> getSubGenerators(Type t) {
		Vector<Generator> result = new Vector<Generator>();
		result.add(new ArrayAccessGenerator() {
			@Override
			public Type[] getParameterTypes() {
				// TODO Auto-generated method stub
				return new Type[] { t, new PrimitiveType("Integer") };
			}

		});
		return result;
	}

}
