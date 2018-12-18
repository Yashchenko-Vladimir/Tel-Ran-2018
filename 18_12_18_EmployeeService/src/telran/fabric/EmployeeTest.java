package telran.fabric;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.fabric.model.IEmployee;
@SpringBootApplication
class EmployeeTest {
	
	IEmployee employees;
	ConfigurableApplicationContext ctx;

	@BeforeEach
	void setUp() throws Exception {
		ctx = SpringApplication.run(EmployeeTest.class);
		employees = ctx.getBean(IEmployee.class);
	}

	@Test
	void testHashCode() {
		
	}

//	@Test
//	void testEmployee() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testEmployeeIntIntStringString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetManager() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetManager() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetSalary() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetSalary() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetDepartment() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetDepartment() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testEqualsObject() {
//		fail("Not yet implemented");
//	}

}
