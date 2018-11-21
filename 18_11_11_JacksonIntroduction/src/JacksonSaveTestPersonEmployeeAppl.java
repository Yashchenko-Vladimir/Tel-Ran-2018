import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import telran.persons.Child;
import telran.persons.Employee;
import telran.persons.Person;
import telran.util.Address;

public class JacksonSaveTestPersonEmployeeAppl {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		Person person1 = new Employee(1234, "1234567", "Moshe", new Address("Lod", "Sokolov", 10, 2),
				"Motorola", 1000, LocalDate.of(1990, 10, 10));
		Person person2 = new Child(1235, "7654321", "David", new Address("Rehovot", "Sokolov", 10, 2), 
				"Shalom", LocalDate.of(1980, 7, 7));
		Person person3 = new Person(1236, "123444", "Zina", new Address("TA", "Sokolov", 10, 2),
				LocalDate.of(1950, 6, 6));
		Person [] persons = {person1, person2, person3};
		File file = new File("empCh.data");
		mapper.writeValue(file, persons);

	}

}
