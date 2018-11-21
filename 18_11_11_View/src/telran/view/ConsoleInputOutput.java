package telran.view;

import java.util.Scanner;

public class ConsoleInputOutput implements
InputOutput {
Scanner scanner=new Scanner(System.in);
	@Override
	public String inputString(String prompt) {
		outputLine(prompt+" or "+CANCEL);
		 String res=scanner.nextLine();
		 if(res.toLowerCase().equals(CANCEL))
			 return null;
		 return res;
	}

	@Override
	public void output(Object object) {
		System.out.print(object);
		
	}

}
