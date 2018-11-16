package basic;

import java.math.BigDecimal;
import java.util.List;

public abstract class Type {
	public abstract String toString();
	public abstract BigDecimal getScore(List<String> keywords);
}
