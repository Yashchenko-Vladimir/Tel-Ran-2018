package telran.company.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.company.entity.Person;
import telran.company.entity.Product;
import telran.company.repo.PersonRepository;
import telran.company.repo.ProductRepository;

@Service
public class PeopleProductJpa implements IPeopleProducts {
	
	@Autowired
	PersonRepository personRepo;
	@Autowired
	ProductRepository productRepo;
	
	
	@Override
	@Transactional
	public List<Product> getPersonOwnes(int id) {
		Person person = personRepo.findById(id).orElse(null);
		if(person == null) return new ArrayList<>();
		return new ArrayList<>(person.getOwn());
	}

	@Override
	public List<Product> getPersonRent(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getProductPersonsOwn(long barCode) {
		Product product = productRepo.findById(barCode).orElse(null);
		if(product == null) return new ArrayList<>();
		return product.getProductOwner();
	}

	@Override
	public List<Person> getProductPersonsRent(long barCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getPersonsWithMostOwns() {
//		long max = personRepo.selectGetMaxPersonOwns();
//		return personRepo.selectPersonWithMostOwns(max);
		return null;
	}

	@Override
	public List<Product> getProductsWithMostRents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean addPerson(int id, String name) {
		if(personRepo.existsById(id))
			return false;
		Person person = new Person(id, name);
		personRepo.save(person);
		return true;
	}

	@Override
	@Transactional
	public boolean addProduct(long barCode, String productName) {
		if(productRepo.existsById(barCode))
			return false;
		Product product = new Product(barCode, productName);
		productRepo.save(product);
		return true;
	}

	@Override
	@Transactional
	public boolean ownProduct(int id, long barCode) {
		
		Person person = personRepo.findById(id).orElse(null);
		if(person == null) return false;
		Product product = productRepo.findById(barCode).orElse(null);
		if(product == null) return false;
		person.getOwn().add(product);
		return true;
	}

	@Override
	public boolean rentProduct(int id, long barCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean returnProduct(int id, long barCode) {
		// TODO Auto-generated method stub
		return false;
	}

}
