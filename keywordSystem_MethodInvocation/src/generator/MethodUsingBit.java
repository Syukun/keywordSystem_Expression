package generator;

import java.util.Vector;

public class MethodUsingBit {
	public boolean isBitOn(int x,int i) {
		return (x & (1 << i)) != 0;
	}
	public Vector generateWithArity(int depth, int arity) {
		if(depth < arity) {
			System.out.println("class MethodUsingBit get wrong with depth less than arity");
		}else {
			for(int exactFlags = 1; exactFlags <= (1 << arity) - 1; exactFlags++) {
				
			}
		}
		return null;
	}
}
