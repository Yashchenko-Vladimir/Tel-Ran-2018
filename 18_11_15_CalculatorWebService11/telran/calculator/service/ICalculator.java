package telran.calculator.service;

public interface ICalculator {
	int calculate(int op1, int op2);
	String numberPresintation(int number, int base);
	Set<String> getSupportedOperations();
}
