package telran.calculator.service;

public class Calculator implements IC{
	private static HashMap<String, BinaryOperator<Integer>> mapOperations;
	static {
		mapOperations = new HashMap<>();
		mapOperations.put("+", (x, y) -> x+y);
		mapOperations.put("-", (x, y) -> x-y);
		mapOperations.put("*", (x, y) -> x*y);
		mapOperations.put("/", (x, y) -> x/y);
	}
	public int calculate(int op1, int op2) {
		return 0;
	}
}
