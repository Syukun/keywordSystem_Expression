package generator;

import java.util.Vector;

import basic.ArrayAccess;
import basic.Expression;
import basic.PrimitiveType;
import basic.Type;
import vector.VectorOfElements;

public class ArrayAccessGenerator extends ExpressionGenerator {

	@Override
	public Generator[] getParameterGenerators() {
		// TODO Auto-generated method stub
		return new Generator[] { new ExpressionGenerator(), new ExpressionGenerator() };
	}

	@Override
	public Type[] getParameterTypes(Type t) {
		// TODO Auto-generated method stub
		return new Type[] { t, new PrimitiveType("Integer") };
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
	public Generator[] getSubGenerators(Type t) {
		return new Generator[] { 
				new ArrayAccessGenerator() {
					@Override
					public Vector<Type> getAllReceiverTypeName(){
						Vector<Type> types = new Vector<Type>();
						types.add(t);
						return types;
					}
								
		} };

	}

}
