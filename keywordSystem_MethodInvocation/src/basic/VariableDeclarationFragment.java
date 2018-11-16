package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 * VariableDeclarationFragment:
    Identifier { Dimension } [ = Expression ]
 */
public class VariableDeclarationFragment {
	SimpleName name;
	Dims dims;
	Expression expression;
	public VariableDeclarationFragment(SimpleName name, Dims dims, Expression expression) {
		super();
		this.name = name;
		this.dims = dims;
		this.expression = expression;
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(name.name);
		if(dims != null) {
			result.append(dims.toString());
		}
		if(expression != null) {
			result.append("="+expression.toString());
		}
		return result.toString();
	}
	
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(name.getScore(keywords));
		if(expression != null) {
			score = score.add(expression.getScore(keywords));
		}
		return score;
	}
}
