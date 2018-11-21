package telran.cars;

import telran.cars.model.*;
import telran.net.Protocol;
import telran.net.Server;
import telran.view.*;

import java.io.IOException;
public class RentCompanyAppl {
private static final String DEFAULT_FILE_NAME = "company.data";
private static final int PORT = 2000;
static IRentCompany company;
static String fileName;
static InputOutput io = new ConsoleInputOutput();
	public static void main(String[] args) throws IOException {
		 fileName=args.length==0? DEFAULT_FILE_NAME : args[0];
		company=RentCompanyEmbedded.restoreFromFile(fileName);
		Protocol protocol=new CarsProtocol(company);
		Server server=new Server(protocol, PORT);
		server.run();
		

	}
	

}
