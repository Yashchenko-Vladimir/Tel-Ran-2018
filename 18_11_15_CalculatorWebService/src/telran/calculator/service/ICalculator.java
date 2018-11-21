package telran.calculator.service;

import java.util.Set;

public interface ICalculator {
	int calculate(int op1, int op2, String operation);
	String numberPresentation(int number, int base);
	Set<String> getSupportedOperations();
}
