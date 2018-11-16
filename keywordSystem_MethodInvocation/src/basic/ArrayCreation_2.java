package basic;

import java.math.BigDecimal;
import java.util.List;


/*
 * new TypeName [ < Type { , Type } > ]'[ Expression '] { '[ Expression '] } {
 * '[ '] }
 * 
 * GenericTypes : < Type { , Type } >
 *
 */
public class ArrayCreation_2 extends ArrayCreation {

	Type typeName;
	GenericTypes genericTypes;
	List<Expression> exps;
	Dims dims;

	public ArrayCreation_2(Type typeName, GenericTypes genericTypes,
			List<basic.Expression> exps, Dims dims) {
		super();
		this.typeName = typeName;
		this.genericTypes = genericTypes;
		this.exps = exps;
		this.dims = dims;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("new " + typeName.toString());
		if (genericTypes != null) {
			result.append(genericTypes.toString());
		}
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
		if (genericTypes != null) {
			score = score.add(genericTypes.getScore(keywords));
		}
		for (Expression exp : exps) {
			score = score.add(exp.getScore(keywords));
		}
		return score;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return new ArrayType(typeName,dims);
	}

}
