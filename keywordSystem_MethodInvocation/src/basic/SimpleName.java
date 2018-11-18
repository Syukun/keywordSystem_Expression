package basic;

import java.math.BigDecimal;
import java.util.List;

public class SimpleName extends Name{
	String name;
	Type receiveType;
	Type[] parameterTypes;
	
	
	public SimpleName(String name, Type receiveType) {
		super();
		this.name = name;
		this.receiveType = receiveType;
	}
	
	
	public SimpleName(String name, Type receiveType, Type[] parameterTypes) {
		super();
		this.name = name;
		this.receiveType = receiveType;
		this.parameterTypes = parameterTypes;
	}


	public String toString() {
		return name;
	}
	public BigDecimal getScore(List<String> keywords) {
		BigDecimal score = ScoreDef.DEFSCORE;
		ScoreDef.checkInKeyword(score, name, keywords);
		return score;
	}
	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return receiveType;
	}
	
	
}
