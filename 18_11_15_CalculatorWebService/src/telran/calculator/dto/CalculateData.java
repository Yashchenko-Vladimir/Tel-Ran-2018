package telran.calculator.dto;

public class CalculateData {
	public int op1;
	public int op2;
	public String operation;
	public CalculateData(int op1, int op2, String operation) {
		this.op1 = op1;
		this.op2 = op2;
		this.operation = operation;
	}
	public CalculateData() {
		
	}
	public int getOp1() {
		return op1;
	}
	public int getOp2() {
		return op2;
	}
	public String getOperation() {
		return operation;
	}
	
	
}
