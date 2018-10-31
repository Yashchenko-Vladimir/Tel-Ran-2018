import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class ViewTestAppl {

	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
//		Integer op1 = io.inputInteger("Enter first number");
//		if(op1 == null)
//			op1 = 0;
//		Integer op2 = io.inputInteger("Enter second number");
//		if (op2 == null)
//			op2=0;
//		io.output(op1 + op2);
//		String str = io.inputString("enter name", Arrays.asList("Vasya", "Masha", "Sara"));
//		io.output(str + " our selection");
//		
//		LocalDate birthDate = io.inputDate("Enter birthdate", "MM/dd/yyyy");
//		if(birthDate != null)
//			io.output(birthDate);
		
//		Person person = io.inputObject("Enter person date in the format:<id>#<name>#<birthYear>", ViewTestAppl :: getPersonFromString);
//		if(person != null) {
//			io.output(person);
//		}
		
//		Person person = inputPerson(io);
//		if(person != null) {
//			io.output(person);
//		}
		
		calculator(io);
	}
	
	private static void calculator(InputOutput io) {
		Item [] items = {
				new AddItem(),
				new SubtractItem(),
				new MultiplyItem(),
				new DivItem()
		};
		Menu menu = new MenuWithExit(io, items);
		menu.runMenu();
		
	}

	static Person getPersonFromString(String strPerson) {
		try {
		String [] tokens = strPerson.split("#");
		int id = Integer.parseInt(tokens[0]);
		String name = tokens[1];
		int birthYear = Integer.parseInt(tokens[2]);
		
		return new Person(id, name, birthYear);
		} catch (Exception e) {
			return null;
		}
	}
	
	private static Person inputPerson(InputOutput io) {
		int id = io.inputInteger("Enter id", 1000000, 9999999);
		String name = io.inputString("Enter name", Arrays.asList("Vasya", "Masha", "Sara"));
		int birthYear = io.inputInteger("Enter birth year", 1970, 2018);
		return new Person(id, name, birthYear);
	}

}
