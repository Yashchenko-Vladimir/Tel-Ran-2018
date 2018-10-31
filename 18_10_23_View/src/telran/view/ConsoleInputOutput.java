package telran.view;

import java.util.Scanner;

public class ConsoleInputOutput implements InputOutput {
	private static final String CANSEL = "cancel";
	Scanner scanner = new Scanner(System.in);

	@Override
	public String inputString(String prompt) {
		outputLine(prompt + " or " + CANSEL);
		String res = scanner.nextLine();
			if(res.toLowerCase().equals(CANSEL))
				return null;
		return res;
	}

	@Override
	public void output(Object object) {
		System.out.print(object);

	}

}
