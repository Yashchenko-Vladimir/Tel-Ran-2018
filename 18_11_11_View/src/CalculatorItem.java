import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;

public abstract class CalculatorItem implements Item {
InputOutput io=new ConsoleInputOutput();
protected static HashMap<String,
BiFunction<Integer,Integer,Long>>
operations;
static {
	operations=new HashMap<>();
	operations.put("+", (op1,op2)->(long)op1+op2);
	operations.put("-", (op1,op2)->(long)op1-op2);
	operations.put("*", (op1,op2)->(long)op1*op2);
	operations.put("/", (op1,op2)->(long)op1/op2);
}
protected long calculate
(int op1,int op2,String operation) {
	return 
  operations.get(operation).apply(op1, op2);
}
protected int[]getNumbers(){
	Integer op1=io.inputInteger("Enter first number");
	Integer op2=io.inputInteger("Enter second number");
	if(op1==null) op1=0;
	if(op2==null) op2=0;
	return new int[] {op1,op2};
}

}
