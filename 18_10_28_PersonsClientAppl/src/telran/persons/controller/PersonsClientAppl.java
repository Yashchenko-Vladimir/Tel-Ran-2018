package telran.persons.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import telran.persons.controller.items.AddPersonItem;
import telran.persons.controller.items.DisplayPersonsItem;
import telran.persons.controller.items.RemovePersonItem;
import telran.persons.service.IPersons;
import telran.persons.service.PersonsTcpProxy;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;


public class PersonsClientAppl {

	private static final String DEFAULT_HOSTNAME = "localhost";
	private static final int DEFAULT_PORT = 2000;

	public static void main(String[] args) throws UnknownHostException, IOException {
		String hostname = args.length > 0 ? args[0] : DEFAULT_HOSTNAME; 
		int port = args.length > 1 ? Integer.parseInt(args[1]):DEFAULT_PORT;
		InputOutput io = new ConsoleInputOutput();
		IPersons persons = new PersonsTcpProxy(hostname, port);
		Item[] items = {
				new AddPersonItem(io, persons),
				new RemovePersonItem(io,persons),
				new DisplayPersonsItem(io,persons)};
		Menu menu = new MenuWithExit(io, items);
		menu.runMenu();

	}

}
