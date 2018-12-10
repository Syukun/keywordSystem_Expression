package generator;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import basic.Expression;
import basic.PrimitiveType;
import basic.Type;
import vector.VectorOfElements;

public class NumberLiteralGenerator extends ExpressionGenerator{
	@Override
	public Vector<Generator> getSubGenerators(){
//		System.out.println("Line 16 in Class StringLiteralGenerator");
		return null;
	}
	@Override
	public Generator[] getParameterGenerators() {
		return new Generator[] {};
	}
	
	@Override
	public void generateWithSubExps(Expression[] subExps,Vector<Expression> result) {
		result.addAll(VectorOfElements.numberLiterealVector);
	}
	@Override
	public Set<Type> getAllReceiveTypeName(){
		Set<Type> allReceiveTypeName = new HashSet<Type>();
		allReceiveTypeName.add(PrimitiveType.INT);
		return allReceiveTypeName;
	}

}
