package basic;

import java.math.BigDecimal;
import java.util.List;

public class Dims {
	int dims;
	public Dims(int dims) {
		this.dims = dims;
	}
	public String toString() {
		StringBuffer result = new StringBuffer();
		for(int i = 0 ; i < dims ; i++) {
			result.append("[]");
		}
		return result.toString();
	}
	public BigDecimal getScore(List<String> keywords) {
		return ScoreDef.ZERO;
	}
}
