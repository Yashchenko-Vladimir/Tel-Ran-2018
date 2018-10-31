
public class MultiplyItem extends CalculatorItem {

	@Override
	public String displayedName() {
		
		return "Multiplay two numbers";
	}

	@Override
	public void perform() {
		int[] numbers = getNumbers();
		long res = calculate(numbers[0], numbers[1], "*");
		io.outputLine(res);
	}

}
