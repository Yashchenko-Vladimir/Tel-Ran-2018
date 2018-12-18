package telran.company;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.company.entity.Person;
import telran.company.entity.Product;
import telran.company.service.IPeopleProducts;

@SpringBootApplication
class PersonProductTest {
	IPeopleProducts peopleProd;
	ConfigurableApplicationContext ctx;
	Product [] products = { 
			new Product(1, "Salt"),
			new Product(2, "Bycicle"),
			new Product(3, "Cap"),
			new Product(4, "Table")
	};
	Person [] persons = {
		new Person(1, "Vasay"),
		new Person(2, "Moshe"),
		new Person(3, "Kolay")
	};
	
	@BeforeEach
	void setUp() throws Exception {
		ctx = SpringApplication.run(PersonProductTest.class);
		peopleProd = ctx.getBean(IPeopleProducts.class);
		for (int i = 0; i < persons.length; i++) {
			peopleProd.addPerson(persons[i].getId(), persons[i].getName());
		}
		for (int i = 0; i < products.length; i++) {
			peopleProd.addProduct(products[i].getBarCode(), products[i].getProductName());
		}
		peopleProd.ownProduct(1, 1);
		peopleProd.ownProduct(1, 2);
		peopleProd.ownProduct(1, 3);
		peopleProd.ownProduct(2, 1);
		peopleProd.ownProduct(3, 1);
		peopleProd.ownProduct(3, 4);
		
	}
	@AfterEach
	void tearDown() {
		ctx.close();
	}

	@Test
	void addPersonTest() {
		assertTrue(peopleProd.addPerson(10, "Test"));
		assertFalse(peopleProd.addPerson(10, "Ira"));
	}
	
	@Test 
	void addProductTest(){
		assertTrue(peopleProd.addProduct(10, "Test"));
		assertFalse(peopleProd.addProduct(10, "Test"));
		
	}
	
	@Test
	void ownProductTest() {
		assertTrue(peopleProd.ownProduct(1, 3));
		assertFalse(peopleProd.ownProduct(10, 3));
		assertFalse(peopleProd.ownProduct(1, 10));
	}
	
	@Test
	void getpersonOwnersTest() {
		assertEquals(3, peopleProd.getPersonOwnes(1).size());
		assertEquals(1, peopleProd.getPersonOwnes(2).size());
		assertEquals(2, peopleProd.getPersonOwnes(3).size());
	}
	
	@Test
	void getProductPersonsOwnTest() {
		assertEquals(3, peopleProd.getProductPersonsOwn(1).size());
	}

}
