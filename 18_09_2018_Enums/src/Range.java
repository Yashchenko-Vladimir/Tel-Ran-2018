
public class Range {
//	public static final int OUT_LEFT = -1;
//	public static final int OUT_RIGHT = 1;
//	public static final int OK = 0;
	int min;
	int max;
	
	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
	
	RangeCode checkNumberInRange(int number) {
		
		if(number < min) {
			return RangeCode.OUT_LEFT;
		}
		if (number > max) {
			return RangeCode.OUT_RIGHT;
		}
		return RangeCode.OK;
	}

}
