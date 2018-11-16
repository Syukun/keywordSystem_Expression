package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * ArrayType : Type Dimension {Dimension}
 */
public class ArrayType extends Type {

	Type type;
	Dims dims;
	
	public ArrayType(Type type, Dims dims) {
		super();
		this.type = type;
		this.dims = dims;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(type).append(dims.toString());
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(type.getScore(keywords));
		return score;
	}

}
