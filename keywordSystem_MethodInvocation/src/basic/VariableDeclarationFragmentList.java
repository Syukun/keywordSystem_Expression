package basic;

import java.math.BigDecimal;
import java.util.List;

public class VariableDeclarationFragmentList {
	List<VariableDeclarationFragment> variableDeclarationFragments;

	public VariableDeclarationFragmentList(List<VariableDeclarationFragment> variableDeclarationFragments) {
		super();
		this.variableDeclarationFragments = variableDeclarationFragments;
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		String seperator = "";
		for(VariableDeclarationFragment variableDeclarationFragment : variableDeclarationFragments) {
			result.append(seperator + variableDeclarationFragment.toString());
			seperator = ",";
		}
		return result.toString();
	}
	
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		for(VariableDeclarationFragment variableDeclarationFragment:variableDeclarationFragments) {
			score = score.add(variableDeclarationFragment.getScore(keywords));
		}
		return score;
	}
}
