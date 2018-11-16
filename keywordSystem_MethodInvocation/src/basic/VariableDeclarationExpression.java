package basic;

import java.math.BigDecimal;
import java.util.List;

/*
 *   VariableDeclarationExpression:
    { ExtendedModifier } Type VariableDeclarationFragment
         { , VariableDeclarationFragment }
 */
public class VariableDeclarationExpression extends Expression {

	Modifier modifier;
	Type type;
	VariableDeclarationFragmentList variableDeclarationFragments;

	public VariableDeclarationExpression(Modifier modifier, Type type,
			VariableDeclarationFragmentList variableDeclarationFragments) {
		super();
		this.modifier = modifier;
		this.type = type;
		this.variableDeclarationFragments = variableDeclarationFragments;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		if (modifier != null) {
			result.append(modifier.toString() + " ");
		}
		result.append(type.toString() + " ").append(variableDeclarationFragments.toString());
		return result.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		if (modifier != null) {
			score = score.add(modifier.getScore(keywords));
		}
		score = score.add(type.getScore(keywords)).add(variableDeclarationFragments.getScore(keywords));
		return score;
	}

}
