package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * ArrayCreation_3 : new PrimitiveType '['] {'[']} ArrayInitializer
 */
public class ArrayCreation_3 extends ArrayCreation {

	PrimitiveType primitiveType;
	Dims dims;
	ArrayInitializer arrayInitializer;

	public ArrayCreation_3(PrimitiveType primitiveType, Dims dims, ArrayInitializer arrayInitializer) {
		super();
		this.primitiveType = primitiveType;
		this.dims = dims;
		this.arrayInitializer = arrayInitializer;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("new ").append(primitiveType.toString()).append(dims.toString())
				.append(arrayInitializer.toString());
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, "new", keywords);
		score = score.add(primitiveType.getScore(keywords)).add(arrayInitializer.getScore(keywords));
		return score;
	}
	

}
