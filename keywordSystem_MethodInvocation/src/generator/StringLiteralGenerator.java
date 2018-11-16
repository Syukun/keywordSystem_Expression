package generator;

import java.util.Vector;

import basic.StringLiteral;
import vector.VectorOfElements;

public class StringLiteralGenerator extends ExpressionGenerator{
	public static Vector<StringLiteral> generateStringLiteral(int depth,String keywords){
		return VectorOfElements.stringLiteralVector;
	}
}
