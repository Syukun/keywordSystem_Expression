package generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import basic.Expression;
import basic.PrimitiveType;
import basic.Type;
import vector.VectorOfElements;

public class StringLiteralGenerator extends ExpressionGenerator {
	static Vector<Map<Type, Vector<Expression>>> maxExpressions = new Vector<Map<Type, Vector<Expression>>>();
	static Vector<Map<Type, Vector<Expression>>> maxExpressionsExact = new Vector<Map<Type, Vector<Expression>>>();

	@Override
	public Generator[] getParameterGenerators() {
		// TODO Auto-generated method stub
		return new Generator[] {};
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
		if (t.equals(this.getAllReceiverTypeName().get(0))) {
			result.add(this);
		}
		return result;
	}

	@Override
	public Vector<Type> getAllReceiverTypeName() {
		Vector<Type> result = new Vector<Type>();
		result.add(PrimitiveType.STRING);
		return result;
	}

	@Override
	public Type[] getParameterTypes() {
		// TODO Auto-generated method stub
		return new Type[] {};
	}

	@Override
	public void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {
		Map<Type,Vector<? extends Expression>> mapStringToStringLiteral = new HashMap<Type,Vector<? extends Expression>>();
		mapStringToStringLiteral.put(PrimitiveType.STRING, VectorOfElements.stringLiteralVector);
		getTableOne().add(mapStringToStringLiteral);
		getTableTwo().add(mapStringToStringLiteral);
		result.addAll(VectorOfElements.stringLiteralVector);
	}
}
