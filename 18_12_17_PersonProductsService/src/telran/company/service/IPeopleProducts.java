package telran.company.service;
import java.util.List;

import telran.company.entity.Person;
import telran.company.entity.Product;

public interface IPeopleProducts {
	
	List<Product> getPersonOwnes(int id);
	List<Product> getPersonRent(int id);
	List<Person> getProductPersonsOwn(long barCode);
	List<Person> getProductPersonsRent(long barCode);
	List<Person> getPersonsWithMostOwns();
	List<Product> getProductsWithMostRents();
	boolean addPerson(int id, String name);
	boolean addProduct(long barCode, String productName);
	boolean ownProduct(int id, long barCode);
	boolean rentProduct(int id, long barCode);
	boolean returnProduct(int id, long barCode);
	
}
