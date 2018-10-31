package telran.cars.controller;

import telran.net.Protocol;
import telran.net.Server;

import java.io.IOException;

import telran.cars.model.RentCompanyEmbedded;
import telran.cars.servise.*;

public class CarsServerAppl {

	public static void main(String[] args) throws IOException {
		Protocol protocol = new CarsProtocol(new RentCompanyEmbedded());
		int port =2000;
		System.out.println("server is listening port " + port);
		Server server = new Server(protocol, port);
		server.run();	

	}

}
