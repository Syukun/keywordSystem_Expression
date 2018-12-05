package generator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.Test;

import basic.ArrayAccess;
import basic.Expression;
import vector.VectorOfElements;

class ExpressionGeneratorTest {

	int depth = 1;
	String keywords = "some";

	@Test
	void testGenerator() {
		VectorOfElements.initByParsing();
		// 1. Expression
		Vector<Expression> exps = new ExpressionGenerator().generateExpression(depth, keywords);
		assertEquals(exps.size(),2);
		// 2. StringLiteral
		Vector<Expression> strLiterals = new StringLiteralGenerator().generateExpression(depth, keywords);
		assertEquals(strLiterals.size(),2);
		depth = 2;
		// 3. ArrayAccess
		Vector<Expression> arrayAccesses = new ArrayAccessGenerator().generateExpression(depth, keywords);
	}

}
