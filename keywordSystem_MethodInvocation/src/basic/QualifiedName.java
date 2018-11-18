package basic;

import java.math.BigDecimal;
import java.util.List;
/*
 * QualifiedName : Name . SimpleName
 */
public class QualifiedName extends Name {
	Name name;
	SimpleName simpleName;
	
	
	public QualifiedName(Name name, SimpleName simpleName) {
		super();
		this.name = name;
		this.simpleName = simpleName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name.toString() + "." + simpleName.toString();
	}

	@Override
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		score = score.add(name.getScore(keywords)).add(simpleName.getScore(keywords));
		return score;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return simpleName.getType();
	}
	
	

}
