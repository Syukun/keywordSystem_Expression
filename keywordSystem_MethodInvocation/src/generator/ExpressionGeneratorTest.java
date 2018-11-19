package generator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.Test;

import basic.ArrayAccess;
import basic.Expression;
import vector.VectorOfElements;

class ExpressionGeneratorTest {

	int depth = 2;
	String keywords = "some";

	@Test
	void testGenerator() {
		VectorOfElements.initByParsing();
		Vector<Expression> exps = new Vector<Expression>();
		// 1. ArrayAccess
		Vector<ArrayAccess> arrayAccess = ArrayAccessGenerator.generateArrayAccessExact(2, keywords);
		assertEquals(arrayAccess.size(),4);
		assertEquals(arrayAccess.get(0).toString(),"a[a]");
		
	}

}
