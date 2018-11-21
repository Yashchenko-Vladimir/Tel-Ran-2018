package telran.calculator.service;

import java.util.HashMap;
import java.util.Set;
import java.util.function.BinaryOperator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorMap implements ICalculator {
	private static HashMap<String, BinaryOperator<Integer>> mapOperations;
	static {
		mapOperations = new HashMap<>();
		mapOperations.put("+", (x, y) -> x+y);
		mapOperations.put("-", (x, y) -> x-y);
		mapOperations.put("*", (x, y) -> x*y);
		mapOperations.put("/", (x, y) -> x/y);
	}

	@Override
	public int calculate(int op1, int op2, String operation) {
		BinaryOperator<Integer> operator = mapOperations.get(operation);
		if(operator == null) {
			throw new RuntimeException(operation + " Unsupported operation");
		}
		return operator.apply(op1, op2);
	}

	@Override
	public String numberPresentation(int number, int base) {
		return Integer.toString(number, base);
	}

	@Override
	public Set<String> getSupportedOperations() {
		
		return mapOperations.keySet();
	}

}
