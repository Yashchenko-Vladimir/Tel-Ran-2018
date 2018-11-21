
public class SubtractItem extends CalculatorItem {

	@Override
	public String displayedName() {
		
		return "Subtract two numbers";
	}

	@Override
	public void perform() {
		int []numbers=getNumbers();
		long res=calculate(numbers[0],numbers[1],"-");
		io.outputLine(res);
		
	}
}
