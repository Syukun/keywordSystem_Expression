package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * ArrayCreation_1 : new PrimitiveType '[ Expression '] { '[ Expression '] } {'[']}
 * 
 * 
 * 
 * right now don't consider the Annotation cause it might be rough.
 */

public class ArrayCreation_1 extends ArrayCreation {

	PrimitiveType primitiveType;
	List<Expression> exps;
	Dims dims;

	public ArrayCreation_1(PrimitiveType primitiveType, List<Expression> exps, Dims dims) {
		super();
		this.primitiveType = primitiveType;
		this.exps = exps;
		this.dims = dims;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("new " + primitiveType.toString());
		for (Expression exp : exps) {
			result.append("[" + exp.toString() + "]");
		}
		if (dims != null) {
			result.append(dims.toString());
		}
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, "new", keywords);
		score = score.add(primitiveType.getScore(keywords));
		for (Expression exp : exps) {
			score = score.add(exp.getScore(keywords));
		}

		return score;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return new ArrayType(primitiveType,dims);
	}	

}
