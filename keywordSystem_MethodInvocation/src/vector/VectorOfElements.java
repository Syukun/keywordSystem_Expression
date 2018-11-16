package vector;
import java.util.Vector;

import basic.StringLiteral;

public class VectorOfElements {
	public static Vector<StringLiteral> stringLiteralVector = new Vector<StringLiteral>();
	public void initByParsing() {
		initStringLiteralVector();
	}
	// solve the visibility later
	public static void initStringLiteralVector() {
		stringLiteralVector.add(new StringLiteral("a"));
		stringLiteralVector.add(new StringLiteral("b"));
//		stringLiteralVector.add(new StringLiteral("c"));
	}
	
}
