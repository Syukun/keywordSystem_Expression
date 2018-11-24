package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 *  ArrayAccess:
    Expression [ Expression ]
 */
public class ArrayAccess extends Expression{
	Expression arrayExp;
	Expression indexExp;
	
	public ArrayAccess(Expression exp1,Expression exp2) {
		this.arrayExp = exp1;
		this.indexExp = exp2;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return arrayExp.toString() + "[" + indexExp.toString() + "]";
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(arrayExp.getScore(keywords)).add(indexExp.getScore(keywords));
		return score;
	}
	public BigDecimal getScore(String keywords) {
		return this.getScore(new ScoreDef().splitKeyword(keywords));
	}
	
	public Type getType() {
		return arrayExp.getType();
	}

	
}
