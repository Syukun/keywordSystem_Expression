package generator;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import basic.ArrayAccess;
import basic.Expression;
import basic.PrimitiveType;
import basic.Type;

public class ArrayAccessGenerator extends ExpressionGenerator {

	public Vector<Generator> getSubGenerators() {
		return new Vector<Generator>();
	}
	
	public Vector<Generator> getSubGeneratorsForEachType(Type t){
		return new Vector<Generator>();
	}

	@Override
	public Generator[] getParameterGenerators() {
		return new Generator[] { new ExpressionGenerator(), new ExpressionGenerator() };
	}
	
	@Override
	public Set<Type> getAllReceiveTypes() {
		Set<Type> allReceiveTypeName = new HashSet<Type>();
		allReceiveTypeName.add(PrimitiveType.STRING);
		allReceiveTypeName.add(PrimitiveType.INT);
		return allReceiveTypeName;
	}
	
	@Override
	public Generator changeProperties(Type t) {
		return new ArrayAccessGenerator(){
			@Override
			public Set<Type> getAllReceiveTypes(){
				Set<Type> allReceiveTypeName = new HashSet<Type>();
				allReceiveTypeName.add(t);
				return allReceiveTypeName;
			}
			
			@Override 
			public Vector<Set<Type>> getPossibleParameterTypes(){
				Vector<Set<Type>> result = new Vector<Set<Type>>();
				Set<Type> typeSet = new HashSet<Type>();
				typeSet.add(t);
				result.add(typeSet);
				result.add(typeSet);
				Set<Type> intType = new HashSet<Type>();
				intType.add(PrimitiveType.INT);
				result.add(intType);
				return result;
			}
		};
	}
	
	@Override
	public Vector<Set<Type>> getPossibleParameterTypes(){
		Vector<Set<Type>> result = new Vector<Set<Type>>();
		result.add(new ExpressionGenerator().getAllReceiveTypes());
		Set<Type> intType = new HashSet<Type>();
		intType.add(PrimitiveType.INT);
		result.add(intType);
		return result;
	}
	
	@Override
	public void generateWithSubExps(Expression[] subExps, Vector<Expression> result) {
		ArrayAccess arrayAccess = new ArrayAccess(subExps[0], subExps[1]);
		result.add(arrayAccess);
	}




}
