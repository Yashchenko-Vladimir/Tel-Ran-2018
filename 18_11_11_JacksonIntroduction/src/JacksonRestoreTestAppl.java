import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import telran.persons.Person;

public class JacksonRestoreTestAppl {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		File file = new File("person.data");
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		List<Person> persons = mapper.readValue(file, new TypeReference<List<Person>>() { });
		Person person =  persons.get(0);
		System.out.println(person.getName());
		System.out.println(persons);
	}

}
