package basic;

import java.math.BigDecimal;
import java.util.List;


/*
 * ArrayCreation_4 : new TypeName [<Type{,Type}>] '[']{'[']} ArrayInitializer 
 */

public class ArrayCreation_4 extends ArrayCreation {

	TypeName typeName;
	GenericTypes genericTypes;
	Dims dims;
	ArrayInitializer arrayInitializer;

	public ArrayCreation_4(TypeName typeName, GenericTypes genericTypes, Dims dims,
			basic.ArrayInitializer arrayInitializer) {
		super();
		this.typeName = typeName;
		this.genericTypes = genericTypes;
		this.dims = dims;
		this.arrayInitializer = arrayInitializer;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("new ").append(typeName.toString());
		if (genericTypes != null) {
			result.append(genericTypes.toString());
		}
		result.append(dims.toString()).append(arrayInitializer.toString());
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, "new", keywords);
		if (genericTypes != null) {
			score = score.add(genericTypes.getScore(keywords));
		}
		score = score.add(arrayInitializer.getScore(keywords));
		return score;
	}

}
