package vector;
import java.util.Vector;

import basic.NumberLiteral;
import basic.PrimitiveType;
import basic.StringLiteral;
import basic.Type;
import basic.TypeName;

public class VectorOfElements {
	public static Vector<StringLiteral> stringLiteralVector = new Vector<StringLiteral>();
	public static Vector<NumberLiteral> numberLiterealVector = new Vector<NumberLiteral>();
	
	public static Vector<Type> allTypes = new Vector<Type>();
	public static void initByParsing() {
		initStringLiteralVector();
		initNumberLiteralVector();
		initType();
	}
	private static void initNumberLiteralVector() {
		numberLiterealVector.add(new NumberLiteral("1"));
		numberLiterealVector.add(new NumberLiteral("2"));
	}
	private static void initType() {
		// TODO Auto-generated method stub
		allTypes.add(PrimitiveType.STRING);
		allTypes.add(PrimitiveType.INT);
	}
	// solve the visibility later
	private static void initStringLiteralVector() {
		stringLiteralVector.add(new StringLiteral("a"));
		stringLiteralVector.add(new StringLiteral("b"));
//		stringLiteralVector.add(new StringLiteral("c"));
	}
	
}
