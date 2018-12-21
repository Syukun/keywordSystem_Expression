package generator;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import basic.Expression;
import basic.PrimitiveType;
import basic.Type;
import vector.VectorOfElements;

public class NumberLiteralGenerator extends ExpressionGenerator{

	public Vector<Generator> getSubGenerators() {
		return new Vector<Generator>();
	}
	
	public Vector<Generator> getSubGeneratorsForEachType(Type t){
		return new Vector<Generator>();
	}
	
	@Override	
	public Set<Type> getAllReceiveTypes(){
		Set<Type> allReceiveTypeName = new HashSet<Type>();
		allReceiveTypeName.add(PrimitiveType.INT);
		return allReceiveTypeName;
	}
	
	public Generator changeProperties(Type t) {
		return this;
	}
	@Override
	public void generateWithSubExps(Expression[] subExps,Vector<Expression> result) {
		result.addAll(VectorOfElements.numberLiterealVector);
	}


}
