import java.time.LocalDate;
import java.util.Arrays;

import telran.view.*;

public class ViewTestAppl {

	public static void main(String[] args) {
		InputOutput io=new ConsoleInputOutput();
//		Integer op1=io.inputInteger
//				("Enter first number",100,200);
//		if(op1==null)
//			return;
//		Integer op2=io.inputInteger
//				("Enter second number",10,15);
//		
//		if(op2==null)
//			op2=0;
//		io.outputLine(op1+op2);
//		String str=io.inputString
//				("enetr name",
//			Arrays.asList("Vasya","Moshe","Sara"));
//		io.outputLine(str+" our selection");
//
//	}
//		LocalDate birthDate=
//				io.inputDate("Enter birthdate",
//						"l-g-h");
//		if(birthDate!=null)
//			io.outputLine(birthDate);
//		Person person=io.inputObject
//		("Enter person data in the format:<id>#<name>#<birthYear>"
//		, ViewTestAppl::getPersonFromString);
//		if(person!=null)
//			io.outputLine(person);
//		Person person=inputPerson(io);
//		if(person!=null)
//			io.outputLine(person);
		calculator(io);
		
	}
	private static void calculator(InputOutput io) {
		Item[]items= {
				new AddItem(),
				new SubtractItem(),
				new MultiplyItem(),
				new DivItem()
		};
		Menu menu=new MenuWithExit(io, items);
		menu.runMenu();
		
	}
	private static Person inputPerson(InputOutput io) {
		
		int id=io.inputInteger
				("Enter id",1000000,9999999);
		String name=io.inputString
				("enetr name",
			Arrays.asList("Vasya","Moshe","Sara"));
		int birthYear=
				io.inputInteger("Enter birth year",1970,2018);
		return new Person(id, name, birthYear);
	}
	static Person getPersonFromString(String strPerson) {
		try {
			String[]tokens=strPerson.split("#");
			int id=Integer.parseInt(tokens[0]);
			String name=tokens[1];
			int birthYear=Integer.parseInt(tokens[2]);
			return new Person(id, name, birthYear);
		} catch (Exception e) {
			return null;
		}
	}
}
