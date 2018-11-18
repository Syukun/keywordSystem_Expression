package basic;

import java.math.BigDecimal;
import java.util.List;

public abstract class Type {
	public abstract String toString();
	public abstract BigDecimal getScore(List<String> keywords);
	public static final TypeName OBJECT = new TypeName ("Object");
	public static final TypeName NULL = new TypeName("null");
}


