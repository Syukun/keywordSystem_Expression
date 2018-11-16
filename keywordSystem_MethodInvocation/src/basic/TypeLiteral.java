package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * TypeLiteral : (Type | void) .class
 */
public abstract class TypeLiteral extends Expression {

	public abstract String toString();
	public abstract BigDecimal getScore(List<String> keywords);

}
